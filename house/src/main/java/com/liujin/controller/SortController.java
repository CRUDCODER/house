package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Sort;
import com.liujin.service.ISortService;
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
@RequestMapping("/sort")
public class SortController {
    @Autowired
    private ISortService sortService;
    /**
     * 房屋类别全查询
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping("/getAllSorts")
    public Map getAllSort(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Page<Sort> page1 = PageHelper.startPage(page, limit);
        Map<String,Object> map=new HashMap<>();
        BaseMapper<Sort> baseMapper = sortService.getBaseMapper();
        List<Sort> sorts = baseMapper.selectList(null);
        map.put("data",sorts);
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 检查房屋类别名称是否重复
     * @param sname
     * @return
     */
    @ResponseBody
    @GetMapping("/checkSname")
    public Map checkSname(@RequestParam("sname") String sname){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<Sort> wrapper = new QueryWrapper<>();
        wrapper.eq("sname",sname);
        BaseMapper<Sort> baseMapper = sortService.getBaseMapper();
        Integer integer = baseMapper.selectCount(wrapper);
        map.put("count",integer);
        return map;
    }

    /**
     * 增加房屋类别
     * @param sort
     * @return
     */
    @ResponseBody
    @PostMapping("/addSorts")
    public Map addSorts(@RequestBody Sort sort){
        Map<String,Object> map=new HashMap<>();
        boolean insert = sort.insert();
        map.put("flag",insert);
        return map;
    }

    /**
     * 根据类别编号查询
     * @param sid
     * @return
     */
    @ResponseBody
    @GetMapping("/getOneSort")
    public Map getOneSort(@RequestParam("sid") Integer sid){
        Map<String,Object> map=new HashMap<>();
        Sort sort = sortService.getById(sid);
        map.put("sort",sort);
        return map;
    }

    /**
     * 修改房屋类别信息
     * @param sort
     * @return
     */
    @ResponseBody
    @PutMapping("/updateSortInfo")
    public Map updateSortInfo(@RequestBody Sort sort){
        Map<String,Object> map=new HashMap<>();
        boolean b = sort.updateById();
        map.put("flag",b);
        return map;
    }
    @ResponseBody
    @DeleteMapping("/delSort")
    public Map deleteSort2(@RequestParam("sid") Integer sid){
        Map<String,Object> map=new HashMap<>();
        boolean b = sortService.removeById(sid);
        map.put("flag",b);
        return map;
    }
}

