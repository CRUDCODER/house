package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Emp;
import com.liujin.entity.Spending;
import com.liujin.service.ISpendingService;
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
@RequestMapping("/spending")
public class SpendingController {
    @Autowired
    private ISpendingService spendingService;
    @GetMapping("/getAllSpending")
    public Map getAllSpending(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",spendingService.getAllSpending());
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/addSpending")
    public Map addSpending(@RequestBody Spending spending, HttpServletRequest request){
        Emp emp = (Emp) request.getSession().getAttribute("user");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        int year = date.getYear()+1900;
        int month=date.getMonth()+1;
        int day=date.getDate();
        String format = simpleDateFormat.format(date);
        spending.setEid(emp.getEid());
        spending.setZtime(format);
        Map<String,Object> map=new HashMap<>(16);
        map.put("flag",spending.insert());
        return map;
    }
    @GetMapping("/getOneSpending")
    public Map getOneSpending(@RequestParam("zid")Integer zid){
        Map<String,Object> map=new HashMap<>(16);
        map.put("spend",spendingService.getById(zid));
        return map;
    }
    @PostMapping("/updateSpending")
    public Map updateSpending(@RequestBody Spending spending){
        Map<String,Object> map=new HashMap<>(16);
        map.put("flag",spending.updateById());
        return map;
    }
}

