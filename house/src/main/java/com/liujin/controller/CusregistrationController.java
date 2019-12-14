package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Cusregistration;
import com.liujin.entity.Emp;
import com.liujin.service.ICusregistrationService;
import com.liujin.service.IEmpService;
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
 * @since 2019-10-19
 */
@RestController
@RequestMapping("/cusregistration")
public class CusregistrationController {
    @Autowired
    private ICusregistrationService cusregistrationService;
    @Autowired
    private IEmpService empService;

    /**
     * 获取所有客户登记信息
     * @param
     * @return
     */
    @GetMapping("/cusregistrations")
    public Map getAllCusregistrationInfo(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Page<Object> page1 = PageHelper.startPage(page, limit);
        Map<String,Object> map=new HashMap<>();
        List<Cusregistration> allCusregistrationInfo = cusregistrationService.getAllCusregistrationInfo();
        map.put("data",allCusregistrationInfo);
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 验证手机号码是否重复
     * @param ctel
     * @return
     */
    @GetMapping("/checkCtel")
    public Map checkCtel(@RequestParam("ctel") String ctel){
        Map<String,Object> map=new HashMap<>();
        BaseMapper<Cusregistration> baseMapper = cusregistrationService.getBaseMapper();
        QueryWrapper<Cusregistration> wrapper = new QueryWrapper<>();
        wrapper.eq("ctel",ctel).or().eq("ctel1",ctel);
        Integer integer = baseMapper.selectCount(wrapper);
        map.put("count",integer);
        return map;
    }

    /**
     * 增加客户
     * @param cusregistration
     * @return
     */
    @PostMapping("/addCus")
    public Map addCusregistrationInfo(@RequestBody Cusregistration cusregistration, HttpServletRequest request){
        Emp emp = (Emp) request.getSession().getAttribute("user");
        Map<String,Object> map=new HashMap<>();
        System.out.println("----------------------------------"+emp);
        cusregistration.setEid(emp.getEid());
        boolean insert = cusregistration.insert();
        map.put("flag",insert);
        return map;
    }

    /**
     * 根据id查询客户信息
     * @param cid
     * @return
     */
    @GetMapping("/getOneCusInfo")
    public Map getOneCusInfo(@RequestParam("cid") Integer cid){
        Map<String,Object> map=new HashMap<>();
        Cusregistration cus = cusregistrationService.getById(cid);
        map.put("cus",cus);
        return map;
    }

    /**
     * 修改客户信息
     * @param cusregistration
     * @return
     */
    @PutMapping("/updateCusInfo")
    public Map updateCusInfo(@RequestBody Cusregistration cusregistration){
        Map<String,Object> map=new HashMap<>();
        boolean update = cusregistration.updateById();
        map.put("flag",update);
        return map;
    }

    /**
     * 删除客户信息
     * @param cid
     * @return
     */
    @DeleteMapping("/deleteCusInfo")
    public Map deleteCusInfo(@RequestParam("cid") Integer cid){
        Map<String,Object>map=new HashMap<>();
        boolean remove = cusregistrationService.removeById(cid);
        map.put("flag",remove);
        return map;
    }
    @GetMapping("/checkCname")
    public Map checkCnameIsTrue(@RequestParam("cname") String cname){
        Map<String,Object> map=new HashMap<>();
        BaseMapper<Cusregistration> baseMapper = cusregistrationService.getBaseMapper();
        QueryWrapper<Cusregistration> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",cname);
        Integer integer = baseMapper.selectCount(wrapper);
        map.put("count",integer);
        return map;
    }
}

