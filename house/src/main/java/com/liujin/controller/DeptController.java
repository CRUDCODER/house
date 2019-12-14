package com.liujin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Dept;
import com.liujin.entity.Emp;
import com.liujin.service.IDeptService;
import com.liujin.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liujin
 * @since 2019-10-29
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IEmpService empService;
    /**
     * 部门全查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/depts")
    public Map getAllDeptInfo(@RequestParam("page")Integer page,@RequestParam("limit") Integer limit){
        Map<String, Object> map = new HashMap<>();
        Page<Object> page1 = PageHelper.startPage(page, limit);
        List<Dept> depts = deptService.getBaseMapper().selectList(new QueryWrapper<Dept>().eq("pflag",1));
        map.put("code",0);
        map.put("count",page1.getTotal());
        map.put("data",depts);
        return map;
    }

    /**
     * 验证部门名称是否重复
     * @param pname
     * @return
     */
    @GetMapping("/checkPname")
    public Map checkPname(@RequestParam("pname")String pname){
        Map<String, Object> map = new HashMap<>();
        Integer count = deptService.getBaseMapper().selectCount(new QueryWrapper<Dept>().eq("pname", pname));
        map.put("count",count);
        return map;
    }

    /**
     * 增加部门信息  首先设置部门状态为1 然后再增加
     * @param dept
     * @return
     */
    @PostMapping("/addDept")
    public Map addDeptInfo(@RequestBody Dept dept){
        Map<String, Object> map = new HashMap<>();
        boolean insert = dept.setPflag(1).insert();
        map.put("flag", insert);
        return map;
    }

    /**
     * 根据部门编号查询部门
     * @param pid
     * @return
     */
    @GetMapping("/getOneDept")
    public Map getOneDept(@RequestParam("pid")Integer pid){
        Map<String,Object>map=new HashMap<>();
        map.put("one",deptService.getById(pid));
        return  map;
    }

    /**
     * 根据部门编号修改部门
     * @param dept
     * @return
     */
    @PostMapping("updateDept")
    public Map updateDept(@RequestBody Dept dept){
        Map<String,Object>map=new HashMap<>();
        map.put("flag",dept.updateById());
        return  map;
    }

    /**
     * 删除部门 把部门编号改为0
     * @param pid
     * @return
     */
    @DeleteMapping("/deleteDept")
    public Map deleteDept(@RequestParam("pid") Integer pid){
        empService.update(new UpdateWrapper<Emp>().set("pid",1).eq("pid",pid));
        Map<String,Object>map=new HashMap<>();
        map.put("flag",deptService.update(new UpdateWrapper<Dept>().set("pflag",0).eq("pid",pid)));
        return map;
    }

    /**
     * 查询被删除的部门
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getDeleteDept")
    public Map getDeleteDept(@RequestParam("page")Integer page,@RequestParam("limit") Integer limit){
        Map<String,Object>map=new HashMap<>();
        Page<Dept> page1 = PageHelper.startPage(page, limit);
        map.put("data",deptService.getBaseMapper().selectList(new QueryWrapper<Dept>().eq("pflag",0)));
        System.out.println(page1);
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 恢复部门
     * @param pid
     * @return
     */
    @PostMapping("/restoreDept")
    public Map restoreDept(@RequestParam("pid") Integer pid){
        Map<String,Object>map=new HashMap<>();
        map.put("flag",deptService.update(new UpdateWrapper<Dept>().set("pflag",1).eq("pid",pid)));
        return map;
    }
}

