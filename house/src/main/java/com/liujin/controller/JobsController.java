package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Emp;
import com.liujin.entity.Jobs;
import com.liujin.service.IEmpService;
import com.liujin.service.IJobsService;
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
 * @since 2019-10-30
 */
@RestController
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    private IJobsService jobsService;
    @Autowired
    IEmpService empService;
    /**
     * 岗位全查询
     *
     * @param
     * @return
     */
    @GetMapping("jobs2")
    public Map getAllJobs(@RequestParam("limit") Integer limit,@RequestParam("page")Integer page) {
        Page<Object> page1 = PageHelper.startPage(page, limit);
        Map<String, Object> map = new HashMap<>();
        BaseMapper<Jobs> baseMapper = jobsService.getBaseMapper();
        List<Jobs> jobs = baseMapper.selectList(null);
        map.put("data", jobs);
        map.put("code",0);
        map.put("count",page1.getTotal());
        return map;
    }

    /**
     * 检查岗位名称是否重复
     *
     * @param jname
     * @return
     */

    @GetMapping("/checkJnam")
    public Map checkJnam(@RequestParam("jname") String jname) {
        Map<String, Object> map = new HashMap<>();
        BaseMapper<Jobs> baseMapper = jobsService.getBaseMapper();
        QueryWrapper<Jobs> wrapper = new QueryWrapper<>();
        wrapper.eq("jname", jname);
        Integer integer = baseMapper.selectCount(wrapper);
        map.put("count", integer);
        return map;
    }

    /**
     * 增加岗位
     *
     * @param jobs
     * @return
     */

    @PostMapping("/addJobs")
    public Map addJobs(@RequestBody Jobs jobs) {
        Map<String, Object> map = new HashMap<>();
        boolean insert = jobs.insert();
        map.put("flag", insert);
        return map;
    }

    /**
     * 根据id查询
     *
     * @param jid
     * @return
     */

    @GetMapping("/getOneJobs")
    public Map getOneJobs(@RequestParam("jid") Integer jid) {
        Map<String, Object> map = new HashMap<>();
        Jobs job = jobsService.getById(jid);
        map.put("job", job);
        return map;
    }

    /**
     * 修改岗位信息
     *
     * @param jobs
     * @return
     */

    @PostMapping("/updateJobs2")
    public Map updateJobsByAjax(@RequestBody Jobs jobs) {
        Map<String, Object> map = new HashMap<>();
        boolean update = jobs.updateById();
        map.put("falg", update);
        return map;
    }

    /**
     * 删除岗位信息
     *
     * @param jid
     * @return
     */

    @RequestMapping("/deleteJobs2")
    public Map deletJobs2(@RequestParam("jid") Integer jid) {
        Map<String, Object> map = new HashMap<>();
        UpdateWrapper<Emp> wrapper = new UpdateWrapper<>();
        wrapper.set("jid", 3);
        wrapper.eq("jid", jid);
        empService.update(wrapper);
        boolean remove = jobsService.removeById(jid);
        map.put("flag", remove);
        return map;
    }

}