package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Emp;
import com.liujin.entity.Income;
import com.liujin.service.IIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liujin
 * @since 2019-11-05
 */
@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    private IIncomeService incomeService;
    @GetMapping("/getAllIncomeInfo")
    public Map getAllIncomeInfo(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",incomeService.getAllIncomeInfo());
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/addIncomeInfo")
    public Map addIncomeInfo(@RequestBody Income income, HttpServletRequest request){
        Emp emp = (Emp) request.getSession().getAttribute("user");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        int year = date.getYear()+1900;
        int month=date.getMonth()+1;
        int day=date.getDate();
        String format = simpleDateFormat.format(date);
        income.setEid(emp.getEid());
        income.setStime(format);
        Map<String,Object> map=new HashMap<>(16);
        map.put("flag",income.insert());
        return map;
    }
    @GetMapping("/getOneIncomeInfo")
    public Map getOneIncomeInfo(@RequestParam("sid")Integer sid){
        Map<String,Object> map=new HashMap<>(16);
        map.put("income",incomeService.getById(sid));
        return map;
    }
    @PostMapping("/updateIncomeInfo")
    public Map updateIncomeInfo(@RequestBody Income income){
        Map<String,Object> map=new HashMap<>(16);
        map.put("flag",income.updateById());
        return map;
    }
}

