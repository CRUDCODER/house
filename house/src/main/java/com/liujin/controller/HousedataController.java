package com.liujin.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Area;
import com.liujin.entity.Housedata;
import com.liujin.entity.Sort;
import com.liujin.service.IAreaService;
import com.liujin.service.IHousedataService;
import com.liujin.service.ISortService;
import com.liujin.util.Imges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liujin
 * @since 2019-10-30
 */
@RestController
@RequestMapping("/housedata")
public class HousedataController {
    @Autowired
    private IHousedataService housedataService;
    @Autowired
    private ISortService sortService;
    @Autowired
    private IAreaService areaService;
    /**
     * 获取全部房屋资料
     * @param page
     * @param limit
     * @param housedata
     * @return
     */
    @GetMapping("/getAllHouseData")
    public Map getAllHouseData(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit,Housedata housedata){
        Map<String,Object> map=new HashMap<>(16);
        Page<Housedata> page1 = PageHelper.startPage(page, limit);
        map.put("data",housedataService.getAllHouseData(housedata));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 获取房屋类型信息
     * @return
     */
    @GetMapping("/sortInfo")
    public Map sortInfo(){
        Map<String,Object> map=new HashMap<>(16);
        BaseMapper<Sort> baseMapper = sortService.getBaseMapper();
        List<Sort> sorts = baseMapper.selectList(null);
        map.put("sorts",sorts);
        return map;
    }

    /**
     * 获取区域信息
     * @return
     */
    @GetMapping("/areaInfo")
    public Map areaInfo(){
        Map<String,Object> map=new HashMap<>(16);
        BaseMapper<Area> baseMapper = areaService.getBaseMapper();
        List<Area> areas = baseMapper.selectList(null);
        map.put("areas",areas);
        return map;
    }
    /**
     * 增加房屋信息 含图片上传
     * @param request
     * @param imges
     * @param housedata
     * @return
     */
    @PostMapping("/addHouseData")
    public Map addHouseData(HttpServletRequest request, Imges imges, Housedata housedata){
        MultipartFile[] img={imges.getImg1(),imges.getImg2(),imges.getImg3()};
        boolean falg=false;
        if (!imges.getImg1().getOriginalFilename().equals("") && !imges.getImg2().getOriginalFilename().equals("") && imges.getImg3().getOriginalFilename().equals("")){
       //使用filiupload组件进行文件上传
            //上传的位置
            String path = request.getSession().getServletContext().getRealPath("/uploads/");

            System.out.println("路径：" + path);
            File file = new File(path);
            //判断路径是否存在
            if (!file.exists()) {
                //如果不存在则创建文件
                file.mkdirs();
            }
            //说明上传文件项
            //获取上传文件名称
            String fileName = "";
            for (int i = 0; i < img.length; i++) {
                String filename = img[i].getOriginalFilename();
                System.out.println("上传文件名称:" + filename);
                //把文件名称设为唯一值 uuid
                String uuid = UUID.randomUUID().toString().replace("_", " ");
                System.out.println("uid:" + uuid);
                filename = uuid + "_" + filename;
                fileName += filename + ",";
                try {
                    img[i].transferTo(new File(path, filename));
                    System.out.println("上传成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("旧名字" + fileName);
            fileName = fileName.substring(0, (fileName.length() - 1));
            System.out.println("新名字:" + fileName);
            housedata.setHimg(fileName);
            housedata.setHflag(1);
            System.out.println(housedata);
            falg= housedata.insert();
        }else {
            housedata.setHflag(1);
            housedata.setHimg(null);
            falg= housedata.insert();
        }
        Map<String,Object>map=new HashMap<>(16);
        map.put("flag",falg);
        return map;
    }

    /**
     * 根据房屋编号查询
     * @param hid
     * @return
     */
    @GetMapping("/getOneInfo")
    public Map getOneHouseData(@RequestParam("hid") Integer hid){
        Map<String,Object> map=new HashMap<>();
        map.put("house",housedataService.getAllHouseData(new Housedata().setHid(hid)));
        return map;
    }

    /**
     * 修改图片
     * @param request
     * @param imges
     * @param housedata
     * @return
     */
    @PostMapping("/updateHousedata")
    public Map updateHouseData(HttpServletRequest request, Imges imges, Housedata housedata){
        System.out.println("图片"+imges.getImg1().getOriginalFilename().length()+","+imges.getImg2().getOriginalFilename()+","+imges.getImg3().getOriginalFilename());
        boolean insert=false;
        if (!imges.getImg1().getOriginalFilename().equals("") && !imges.getImg2().getOriginalFilename().equals("") && !imges.getImg3().getOriginalFilename().equals("")){
            MultipartFile [] img={imges.getImg1(),imges.getImg2(),imges.getImg3()};
            //使用filiupload组件进行文件上传
            //上传的位置
            String path = request.getSession().getServletContext().getRealPath("/uploads/");
            System.out.println("路径："+path);
            File file = new File(path);
            //判断路径是否存在
            if(!file.exists()){
                //如果不存在则创建文件
                file.mkdirs();
            }
            //说明上传文件项
            //获取上传文件名称
            String fileName="";
            for (int i=0;i<img.length;i++){
                String filename = img[i].getOriginalFilename();
                System.out.println("上传文件名称:"+filename);
                //把文件名称设为唯一值 uuid
                String uuid = UUID.randomUUID().toString().replace("_", " ");
                System.out.println("uid:"+uuid);
                filename=uuid+"_"+filename;
                fileName+=filename+",";
                try {
                    img[i].transferTo(new File(path,filename));
                    System.out.println("上传成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("旧名字"+fileName);
            fileName= fileName.substring(0,(fileName.length()-1));
            System.out.println("新名字:"+fileName);
            housedata.setHimg(fileName);
            System.out.println(housedata);
            insert = housedata.updateById();
        }else {
            Housedata house = housedataService.getById(housedata.getHid());
            housedata.setHimg(house.getHimg());
            System.out.println(house.getHimg());
            insert = housedata.updateById();
        }
        Map<String,Object>map=new HashMap<>();
        map.put("flag",insert);
        return map;
    }
}

