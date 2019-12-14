package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Area;
import com.liujin.service.IAreaService;
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
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private IAreaService areaService;
    /**
     * 查询所有片区信息
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping("/getAllArea")
    public Map getAllArea(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Page<Area> page1 = PageHelper.startPage(page, limit);
        Map<String,Object> map=new HashMap<>(16);
        BaseMapper<Area> baseMapper = areaService.getBaseMapper();
        List<Area> areas = baseMapper.selectList(null);
        map.put("data",areas);
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 根据id查询
     * @param aid
     * @return
     */
    @ResponseBody
    @GetMapping("/getOneArea")
    public Map getOneArea(@RequestParam("aid") Integer aid){
        Map<String,Object> map=new HashMap<>(16);
        Area area = areaService.getById(aid);
        map.put("area",area);
        return map;
    }

    /**
     * 增加片区信息
     * @param area
     * @return
     */
    @ResponseBody
    @PostMapping("/addArea")
    public Map addArea(@RequestBody Area area){
        Map<String,Object> map=new HashMap<>(16);
        boolean insert = area.insert();
        map.put("flag",insert);
        return map;
    }
    @ResponseBody
    @GetMapping("/checkAname")
    public Map checkAname(@RequestParam("aname") String aname){
        Map<String,Object> map=new HashMap<>(16);
        BaseMapper<Area> baseMapper = areaService.getBaseMapper();
        QueryWrapper<Area> wrapper = new QueryWrapper<>();
        wrapper.eq("aname",aname);
        Integer integer = baseMapper.selectCount(wrapper);
        map.put("count",integer);
        return map;
    }

    /**
     * 修改片区信息
     * @param area
     * @return
     */
    @ResponseBody
    @PutMapping("/updateAreaInfo")
    public Map updateArea2(@RequestBody Area area){
        Map<String,Object> map=new HashMap<>(16);
        boolean b = area.updateById();
        map.put("flag",b);
        return map;
    }

    /**
     * 删除片区信息
     * @param aid
     * @return
     */
    @ResponseBody
    @DeleteMapping("/deleteAreaInfo")
    public Map deleteArea2(@RequestParam("aid") Integer aid){
        Map<String,Object> map=new HashMap<>(16);
        boolean b = areaService.removeById(aid);
        map.put("flag",b);
        return map;
    }
}

