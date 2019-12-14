package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Dept;
import com.liujin.entity.Emp;
import com.liujin.entity.Jobs;
import com.liujin.entity.Visitors;
import com.liujin.service.IDeptService;
import com.liujin.service.IEmpService;
import com.liujin.service.IJobsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liujin
 * @since 2019-10-30
 */
@RestController
@RequestMapping("/emp")
public class EmpController {
    private final Logger logger= LoggerFactory.getLogger(Logger.class);
    @Autowired
    private IEmpService empService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IJobsService jobsService;
    /**
     * 登陆方法
     * @param emp
     * @param
     * @return
     */
    
    @GetMapping("/ajaxLogin")
    public Integer ajaxLogin(Emp emp, HttpServletRequest request, HttpServletResponse response){
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("ename",emp.getEname());
        wrapper.eq("epsw",emp.getEpsw());
        BaseMapper<Emp> mapper = empService.getBaseMapper();
        Emp emp1 = mapper.selectOne(new QueryWrapper<Emp>().eq("ename", emp.getEname()).eq("epsw", emp.getEpsw()));
        Integer integer = mapper.selectCount(wrapper);
        if (integer>0){
            request.getSession().setAttribute("user",emp1);
            Visitors visitors=new Visitors();
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            String format = simpleDateFormat.format(date);
            visitors.setEid(emp1.getEid());
            visitors.setVtime(format);
            visitors.insert();
        }
        return integer;
    }

    /**
     * 获取所有员工
     * @param
     * @return
     */
    
    @GetMapping("/getAllEmps")
    public Map getAllEmp(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Page<Dept> page1 = PageHelper.startPage(page, limit);
        Map<String,Object> map=new HashMap<>();
        List<Emp> allEmpInfo = empService.getAllEmpInfo(new Emp().setEflag(1));
        map.put("data",allEmpInfo);
        map.put("count",page1.getTotal());
        map.put("code",0);
        logger.info("获取员工信息成功");
        return map;
    }

    /**
     * 获取部门和岗位信息
     * @return
     */
    
    @GetMapping("/getDeptAndJobs")
    public Map getDeptAndJobs(){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.eq("pflag",1);
        BaseMapper<Dept> baseMapper = deptService.getBaseMapper();
        List<Dept> depts = baseMapper.selectList(wrapper);
        List<Jobs> jobs = new Jobs().selectAll();
        map.put("depts",depts);
        map.put("jobs",jobs);
        return map;
    }

    /**
     * 验证电话号码是否重复
     * @param etel
     * @return
     */
    
    @GetMapping("/checkEtel")
    public Map checkEtel(@RequestParam("etel") String etel){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("etel",etel);
        BaseMapper<Emp> baseMapper = empService.getBaseMapper();
        Integer integer = baseMapper.selectCount(wrapper);
        map.put("count",integer);
        return map;
    }

    /**
     * 增加员工
     * @param emp
     * @return
     */
    
    @PostMapping("/addEmpInfo")
    public Map addEmp(@RequestBody Emp emp){
        emp.setEflag(1);
        boolean insert=false;
        if (emp.getJid()==2){
            UpdateWrapper<Emp> wrapper = new UpdateWrapper<>();
            wrapper.set("jid",3);
            wrapper.eq("jid",2);
            wrapper.eq("pid",emp.getPid());
            empService.update(wrapper);
            insert = emp.insert();
        }else {
            insert = emp.insert();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("flag",insert);
        return map;
    }

    /**
     * 根据id获取员工信息
     * @param eid
     * @return
     */
    
    @GetMapping("/getOneEmp")
    public Map getOneEmp(@RequestParam("eid") Integer eid){
        Map<String,Object> map=new HashMap<>();
        Emp emp = empService.getById(eid);
        map.put("emp",emp);
        return map;
    }

    /**
     * 修改员工
     * @param emp
     * @return
     */
    
    @PostMapping("/updateEmp")
    public Map updateEmpInfo2(@RequestBody Emp emp){
        boolean insert=false;
        if (emp.getJid()==2){
            UpdateWrapper<Emp> wrapper = new UpdateWrapper<>();
            wrapper.set("jid",3);
            wrapper.eq("jid",2);
            wrapper.eq("pid",emp.getPid());
            empService.update(wrapper);
            insert=  emp.updateById();
        }else {
            insert=  emp.updateById();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("flag",insert);
        return map;
    }

    /**
     * 删除员工
     * @param eid
     * @return
     */
    
    @DeleteMapping("/delEmp")
    public Map deleteEmp(@RequestParam("eid") Integer eid){
        Map<String,Object> map=new HashMap<>();
        UpdateWrapper<Emp> wrapper = new UpdateWrapper<>();
        wrapper.set("eflag",0);
        wrapper.eq("eid",eid);
        boolean update = empService.update(wrapper);
        map.put("flag",update);
        return map;
    }

    /**
     * 获取离职员工信息
     * @param
     * @return
     */

    @GetMapping("/getDeleteEmps")
    public Map getDeleteEmp(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Page<Emp> page1 = PageHelper.startPage(page, limit);
        Map<String,Object> map=new HashMap<>();
        List<Emp> allEmpInfo = empService.getAllEmpInfo(new Emp().setEflag(0));
        map.put("data",allEmpInfo);
        map.put("count",page1.getTotal());
        map.put("code",0);
        logger.info("获取员工信息成功");
        return map;
    }
    
    @RequestMapping("/restoreEmpInfo")
    public Map restoreEmp(@RequestParam("eid") Integer eid){
        Map<String,Object> map=new HashMap<>();
        UpdateWrapper<Emp> wrapper = new UpdateWrapper<>();
        wrapper.set("eflag",1);
        wrapper.eq("eid",eid);
        boolean update = empService.update(wrapper);
        map.put("flag",update);
        return map;
    }

    /**
     * 验证员工账号是否存在  员工账号不能重复
     * @return
     */
    @GetMapping("/checkEname")
    
    public Map chechEname(@RequestParam("ename") String ename){
        System.out.println(ename);
        Map<String,Object> map=new HashMap<>();
        BaseMapper<Emp> baseMapper = empService.getBaseMapper();
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("ename",ename);
        Integer integer = baseMapper.selectCount(wrapper);
        map.put("count",integer);
        return map;
    }

    /**
     * 查询该部门是否已存在经理  经理只能有一位
     * @param pid
     * @return
     */
    
    @GetMapping("/checkJobs")
    public Map checkJobs(@RequestParam("pid") Integer pid){
        Map<String,Object> map=new HashMap<>();
        BaseMapper<Emp> baseMapper = empService.getBaseMapper();
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("pid",pid);
        wrapper.eq("jid",2);
        Integer integer = baseMapper.selectCount(wrapper);
        map.put("count",integer);
        return map;
    }
    @PostMapping("/updatePassword")
    public boolean updatePassword(@RequestBody Emp emp,HttpServletRequest request){
        Emp emp1 = (Emp) request.getSession().getAttribute("user");
        boolean b = emp.setEid(emp1.getEid()).updateById();
        return b;
    }
}

