package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Cusregistration;
import com.liujin.entity.Emp;
import com.liujin.entity.Housedata;
import com.liujin.entity.Stayin;
import com.liujin.service.ICusregistrationService;
import com.liujin.service.IHousedataService;
import com.liujin.service.IStayinService;
import com.liujin.service.IVisitorsService;
import com.liujin.util.Imges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liujin
 * @since 2019-10-19
 */
@RestController
@RequestMapping("/stayin")
public class StayinController {
    @Autowired
    private IStayinService stayinService;
    @Autowired
    private ICusregistrationService cusService;
    @Autowired
    private IHousedataService housedataService;
    @Autowired
    private IVisitorsService visitorsService;
    @GetMapping("/getAllStayin")
    public Map getAllStayinInfo(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit, Stayin stayin){
        Page<Object> page1 = PageHelper.startPage(page, limit);
        Map<String,Object> map=new HashMap<>(16);
        System.out.println(stayin);
        List<Stayin> stayins = stayinService.combinationQuery(stayin);
        map.put("data",stayins);
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    /**
     * 根据客户姓名和电话查询客户
     * @return
     */
    @GetMapping("/getByCname")
    public Map queryByCname(@RequestParam("cname") String cname){
        Map<String,Object>map=new HashMap<>();
        Page<Object> page = PageHelper.startPage(1, 5);
        BaseMapper<Cusregistration> baseMapper = cusService.getBaseMapper();
        QueryWrapper<Cusregistration> wrapper = new QueryWrapper<>();
        wrapper.like("cname",cname);
        List<Cusregistration> selectList = baseMapper.selectList(wrapper);
        map.put("list",selectList);
        return map;
    }

    /**
     * 根据aid和sid查询对应房屋资料
     * @return
     */
    @GetMapping("/queryBySidAndAid")
    public Map queryBySidAndAid(Housedata housedata){
        System.out.println(housedata);
        Map<String,Object>map=new HashMap<>();
        housedata.setHflag(1);
        List<Housedata> allHouseData = housedataService.getAllHouseData(housedata);
        map.put("house",allHouseData);
        return map;
    }

    @GetMapping("/queryBySidAndAid2")
    public Map queryBySidAndAid2(Housedata housedata){
        System.out.println(housedata);
        Map<String,Object>map=new HashMap<>();
        List<Housedata> allHouseData = housedataService.getAllHouseData(housedata);
        map.put("house",allHouseData);
        return map;
    }

    /**
     * 增加客户入住信息  新增完之后 对应的房屋状态变为已出租
     * @param stayin
     * @param imges
     * @param request
     * @return
     */
    @PostMapping("/addStayinInfo")
    public Map addStayinInfo(Stayin stayin, Imges imges, HttpServletRequest request){
        Emp emp = (Emp)request.getSession().getAttribute("user");
        stayin.setEid(emp.getEid());
        boolean flag=false;
        //表示上传了图片
        if (!imges.getImg1().getOriginalFilename().equals("")){
            //上传的位置
            String path = request.getSession().getServletContext().getRealPath("/uploads/");
            File file = new File(path);
            //判断路径是否存在
            if (!file.exists()) {
                //如果不存在则创建文件
                file.mkdirs();
            }
            String filename = imges.getImg1().getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("_", " ");
            filename = uuid + "_" + filename;
            stayin.setMimg(filename);
            try {
                imges.getImg1().transferTo(new File(path, filename));
                System.out.println("上传成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
            stayin.setMflag(1);
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String nowdata = simpleDateFormat.format(date);
            stayin.setMdate(nowdata);
            flag = stayin.insert();
            //添加成功后 需要将该房屋的信息改为出租中  以免其他人再出租
            UpdateWrapper<Housedata> wr = new UpdateWrapper<>();
            wr.eq("hid",stayin.getHid());
            wr.set("hflag",0);
            housedataService.update(wr);
        }
        //不上传图片
        else {
            //不上传合同 则设置图片为空
            stayin.setMimg(null);
            //房屋出租状态1为正在出租中
            stayin.setMflag(1);
            //获取当前时间 设置为登记时间
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String nowdata = simpleDateFormat.format(date);
            stayin.setMdate(nowdata);
            flag = stayin.insert();
            //添加成功后 需要将该房屋的信息改为出租中  以免其他人再出租
            UpdateWrapper<Housedata> wr = new UpdateWrapper<>();
            wr.eq("hid",stayin.getHid());
            wr.set("hflag",0);
            housedataService.update(wr);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("flag",flag);
        return map;
    }

    /**
     * 根据编号查询客户入住信息
     * @param mid
     * @return
     */
    @GetMapping("/getOneStayin")
    public Map getOneStayin(@RequestParam("mid") Integer mid){
        Map<String,Object> map=new HashMap<>();
        Stayin stayin = stayinService.getOneStayin(mid);
        map.put("stayin",stayin);
        return map;
    }

    /**
     * 修改出租信息  判断用户是否上传图片
     * @param stayin
     * @param imges
     * @param request
     * @return
     */
    @PostMapping("/updateStayin")
    public Map updateStain(Stayin stayin, Imges imges, HttpServletRequest request){
        boolean flag=false;
        //如果图片不为空
        if (!imges.getImg1().getOriginalFilename().equals("")){
            String path = request.getSession().getServletContext().getRealPath("/uploads/");
            File file = new File(path);
            //判断路径是否存在
            if (!file.exists()) {
                //如果不存在则创建文件
                file.mkdirs();
            }
            String filename = imges.getImg1().getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("_", " ");
            filename = uuid + "_" + filename;
            stayin.setMimg(filename);
            try {
                imges.getImg1().transferTo(new File(path, filename));
                System.out.println("上传成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
            stayin.setMflag(1);
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String nowdata = simpleDateFormat.format(date);
            stayin.setMdate(nowdata);
            flag = stayin.updateById();
        }
        //图片为空
        else {
            flag=stayin.updateById();
        }

        Map<String,Object> map=new HashMap<>();
        map.put("flag",flag);
        return map;
    }
    @GetMapping("/historyInfo")
    public Map getHistoryInfo(@RequestParam("page")Integer page,@RequestParam("limit") Integer limit){
        Page<Object> page1 = PageHelper.startPage(page, limit);
        Map<String,Object> map=new HashMap<>();
        List<Stayin> historyInfo = stayinService.getHistoryInfo();
        map.put("data",historyInfo);
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 根据出租房屋的编号 查询房屋信息和客户信息
     * @return
     */
    @GetMapping("/getHidAndCid")
    public Map getHidAndCid(@RequestParam("mid")Integer mid){
        Map<String,Object> map=new HashMap<>();
        Stayin id = stayinService.getById(mid);
        map.put("stayin",id);
        return map;
    }

    /**
     * 获取收租到期的房屋信息
     * @return
     */
    @GetMapping("/getDuetoInfo")
    public Map getDuetoInfo(){
        Map<String,Object> map=new HashMap<>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String format = simpleDateFormat.format(date);
        map.put("count",stayinService.getBaseMapper().selectCount(new QueryWrapper<Stayin>().eq("mbegintime",format)));
        map.put("visitors",visitorsService.getBaseMapper().selectCount(null));
        return map;
    }

    @GetMapping("/getAllDuetoInfo")
    public Map getAllDuetoInfo(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String format = simpleDateFormat.format(date);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",stayinService.getOverdueInfo(format));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @GetMapping("/getAllDetail")
    public Map getAllDetail(@RequestParam("hid") Integer hid,@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        List<Stayin> allDetail = stayinService.getAllDetail(hid);
        float total=0;
        for (Stayin stayin : allDetail) {
            total+=stayin.getTotal();
        }
        map.put("data",allDetail);
        map.put("count",page1.getTotal());
        map.put("code",0);
        map.put("total",total);
        return map;
    }
}

