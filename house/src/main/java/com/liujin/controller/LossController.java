package com.liujin.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Emp;
import com.liujin.entity.Housedata;
import com.liujin.entity.Loss;
import com.liujin.service.ILossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/loss")
public class LossController {
    @Autowired
    private ILossService lossService;

    @GetMapping("/getAllLossInfo")
    public Map getAllLossInfo(@RequestParam("page") Integer page, @RequestParam("limit")Integer limit, Loss loss){
        Map<String,Object> map=new HashMap<>();
        Page<Object> page1 = PageHelper.startPage(page, limit);
        System.out.println(loss);
        List<Loss> allLostInfo = lossService.getAllLostInfo(loss);
        map.put("data",allLostInfo);
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/addLossInfo")
    public Map addLossInfo(@RequestBody Loss loss, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>(16);
        Emp emp = (Emp) request.getSession().getAttribute("user");
        loss.setEid(emp.getEid()).setBflag(0);
        boolean insert = loss.insert();
        if (insert==true){
            //状态变为3 等待维修  以免重复报损
            new Housedata().update(new UpdateWrapper<Housedata>().set("hflag",3).eq("hid",loss.getHid()));
        }
        map.put("flag",insert);
        return map;
    }
    @GetMapping("/getOneLossInfo")
    public Map getOneLossInfo(@RequestParam("bid") Integer bid){
        Map<String,Object> map=new HashMap<>(16);
        map.put("loss",lossService.getAllLostInfo(new Loss().setBid(bid)));
        return map;
    }
    @RequestMapping("/updateLossInfo")
    public Map updateLossInfo(@RequestBody Loss loss){
        Map<String,Object> map=new HashMap<>(16);
        map.put("flag",loss.updateById());
        return map;
    }
}

