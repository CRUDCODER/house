package com.liujin.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Emp;
import com.liujin.entity.Housedata;
import com.liujin.entity.Report;
import com.liujin.entity.Stayin;
import com.liujin.service.IReportService;
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
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IReportService reportService;
    @GetMapping("/getAllReport")
    public Map getAllReport(@RequestParam("mid") Integer mid,@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Stayin stayin = new Stayin().selectById(mid);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",reportService.getReport(stayin.getHid()));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @GetMapping("/getOneHouseByMid")
    public Map getOneHouseByMid(@RequestParam("mid")Integer mid){
        Map<String,Object> map=new HashMap<>(16);
        Stayin stayin = new Stayin().selectById(mid);
        Housedata housedata = new Housedata().selectById(stayin.getHid());
        map.put("house",housedata);
        return map;
    }
    @PostMapping("/addReport")
    public Map addReport(@RequestBody Report report, HttpServletRequest request){

        Stayin stayin = new Stayin().selectById(report.getMid());
        Housedata housedata = new Housedata().selectById(stayin.getHid());
        Emp emp = (Emp) request.getSession().getAttribute("user");
        report.setEid(emp.getEid());
        report.setHid(housedata.getHid());
        boolean insert = report.insert();
        if (insert==true){
            new Housedata().update(new UpdateWrapper<Housedata>().set("dkd",report.getDkd()).set("skd",report.getSkd()).set("mkd",report.getMkd()).eq("hid",report.getHid()));
        }
        Map<String,Object> map=new HashMap<>(16);
        map.put("flag",insert);
        return map;
    }
    @GetMapping("/getOneReport")
    public Map getOneReport(@RequestParam("bid")Integer bid){
        Map<String,Object> map=new HashMap<>(16);
        map.put("report", reportService.getById(bid));
        return map;
    }
}

