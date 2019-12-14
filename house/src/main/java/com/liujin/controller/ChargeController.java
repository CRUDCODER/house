package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Charge;
import com.liujin.entity.Emp;
import com.liujin.entity.Stayin;
import com.liujin.service.IChargeService;
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
 * @since 2019-10-25
 */
@RestController
@RequestMapping("/charge")
public class ChargeController {

    @Autowired
    private IChargeService chargeService;
    /**
     * 增加登记收费记录 经办人从session中获取
     * @param charge
     * @param
     * @return
     */
    @PostMapping("/addChargeInfo")
                                 public Map addChargeInfo(@RequestBody Charge charge, HttpServletRequest request){
        Emp emp = (Emp)request.getSession().getAttribute("user");
        charge.setEid(emp.getEid());
        boolean insert = charge.insert();
        if (insert==true){
            Stayin stayin=new Stayin();
            stayin.update(new UpdateWrapper<Stayin>().set("mbegintime",charge.getMbegintime()).eq("mid",charge.getMid()));
        }
        Map<String,Object> map=new HashMap<>(16);
        map.put("flag",insert);
        return map;
    }

    /**
     * 查询续费明细
     * @return
     */
    @GetMapping("/getChargeDetail")
    public Map getChargeDetail(@RequestParam("mid") Integer mid , @RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Page<Object> page1 = PageHelper.startPage(page, limit);
        Map<String,Object> map=new HashMap<>(16);
        List<Charge> chargeDetail = chargeService.getChargeDetail(mid);
        map.put("data",chargeDetail);
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
}

