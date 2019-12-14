package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Policy;
import com.liujin.service.IPolicyService;
import com.liujin.util.Imges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liujin
 * @since 2019-11-07
 */
@RestController
@RequestMapping("/policy")
public class PolicyController {
    @Autowired
    private IPolicyService policyService;
    @GetMapping("/getAllPolicy")
    public Map getAllPolicy(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",policyService.getBaseMapper().selectList(null));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/upload")
    public Map upload(Imges imges, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>(16);
        String url="";
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
            try {
                imges.getImg1().transferTo(new File(path, filename));
                System.out.println("上传成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(path);
            System.out.println(filename);
            url=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/uploads/"+filename;
            map.put("url",url);
            map.put("code",1);
        }
        return map;
    }
    @PostMapping("/addPolicy")
    public boolean addPolicy(@RequestBody Policy policy){
        boolean insert=false;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        try {

            byte[] bytes = policy.getContent().getBytes();//得到byte
//            System.out.println(policy.getContent());
//            SerialBlob x =new SerialBlob(policy.getContent().getBytes());
            policy.setCremark(bytes);
            policy.setCtime(format);
            insert= policy.insert();
        } catch (Exception e){
            e.printStackTrace();
        }
        return insert;
    }
    @GetMapping("/getOnePolicy")
    public Map getOnePolicy(@RequestParam("cid") Integer cid){
        Map<String,Object> map=new HashMap<>(16);
        Policy policy = policyService.getById(cid);
        String str=new String(policy.getCremark());
        policy.setContent(str);
        map.put("polict",policy);
        return map;
    }
    @RequestMapping("/deletePolicy")
    public boolean deletePolicy(@RequestParam("cid") Integer cid){
      return  policyService.removeById(cid);
    }
    @GetMapping("/getPolicyOnIndex")
    public Map getPolicyOnIndex(){
        Map<String,Object> map=new HashMap<>(16);
        PageHelper.startPage(1,5);
        map.put("data",policyService.getBaseMapper().selectList(new QueryWrapper<Policy>().orderByDesc("cid")));
        map.put("code",0);
        return map;
    }
}

