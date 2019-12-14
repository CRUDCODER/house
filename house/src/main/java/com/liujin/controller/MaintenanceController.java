package com.liujin.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Emp;
import com.liujin.entity.Housedata;
import com.liujin.entity.Loss;
import com.liujin.entity.Maintenance;
import com.liujin.service.IMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Autowired
    private IMaintenanceService maintenanceService;
    @PostMapping("/addMaintenanceInfo")
    public Map addMaintenanceInfo(@RequestBody Maintenance maintenance, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>(16);
        Loss loss= new Loss().selectById(maintenance.getBid());
        maintenance.setHid(loss.getHid());
        Emp emp = (Emp) request.getSession().getAttribute("user");
        maintenance.setEid(emp.getEid());
        boolean insert = maintenance.insert();
        if (insert==true){
            new Loss().update(new UpdateWrapper<Loss>().set("bflag",1).eq("bid",maintenance.getBid()));
            new Housedata().update(new UpdateWrapper<Housedata>().set("hflag",1).eq("hid",loss.getHid()));
        }
        map.put("flag",insert);
        return map;
    }
    @GetMapping("/getAllMaintenance")
    public Map getAllMaintenance(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",maintenanceService.getAllMaintenance());
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @GetMapping("/getOneInfo")
    public Map getOneMaintenance(@RequestParam("wid")Integer wid){
        Map<String,Object> map=new HashMap<>(16);
        map.put("data",maintenanceService.getById(wid));
        return map;
    }
    @RequestMapping("/updateMaintenance")
    public Map updateMaintenance(@RequestBody Maintenance maintenance){
        Map<String,Object> map=new HashMap<>(16);
        map.put("flag",maintenance.updateById());
        return map;
    }
}

