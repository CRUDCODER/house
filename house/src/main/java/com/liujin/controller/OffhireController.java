package com.liujin.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.liujin.entity.Emp;
import com.liujin.entity.Housedata;
import com.liujin.entity.Offhire;
import com.liujin.entity.Stayin;
import com.liujin.service.IOffhireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liujin
 * @since 2019-11-03
 */
@RestController
@RequestMapping("/offhire")
public class OffhireController {
    @Autowired
    private IOffhireService offhireService;
    @PostMapping("/addOffireInfo")
    public Map addOffireInfo(@RequestBody Offhire offhire, HttpServletRequest request){
        Emp emp = (Emp) request.getSession().getAttribute("user");
        offhire.setEid(emp.getEid());
        Map<String,Object> map=new HashMap<>(16);
        boolean insert = offhire.insert();
        if (insert==true){
            Housedata housedata=new Housedata();
            //把登记入住的状态改为0 表示该房子已经停租
            boolean stayin1=new Stayin().update(new UpdateWrapper<Stayin>().set("mflag",0).eq("mid",offhire.getMid()));
            Stayin stayin=new Stayin().selectById(offhire.getMid());
            // 判断房子是否损坏 flag 1表示未损坏  0表示损坏  未损坏则把房屋状态改为1 表示待出租   损坏则把房屋状态改为2 表示维修中
            if (offhire.getFlag()==0){
                housedata.update(new UpdateWrapper<Housedata>().set("hflag",2).eq("hid",stayin.getHid()));
            }else if (offhire.getFlag()==1){
                housedata.update(new UpdateWrapper<Housedata>().set("hflag",1).eq("hid",stayin.getHid()));
            }
        }
        map.put("flag",insert);
        return map;
    }
}

