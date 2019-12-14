package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Contract;
import com.liujin.service.IContractService;
import com.liujin.util.Imges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private IContractService contractService;
    @GetMapping("/getAllContract")
    public Map getAllContract(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Page<Object> page1 = PageHelper.startPage(page, limit);
        Map<String,Object> map=new HashMap<>(16);
        map.put("data",contractService.getBaseMapper().selectList(null));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/addContract")
    public Map addContract(Contract contract, Imges imges, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>(16);
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
            contract.setHtremark(filename);
            try {
                imges.getImg1().transferTo(new File(path, filename));
                System.out.println("上传成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
            map.put("flag",contract.insert());
            return map;
    }
    @GetMapping("/getOneContract")
    public Map getOneContract(@RequestParam("htid") Integer htid){
        Map<String,Object> map=new HashMap<>(16);
        map.put("data",contractService.getById(htid));
        return map;
    }

    @RequestMapping("/updateContract")
    public Map updateContract(Contract contract,Imges imges,HttpServletRequest request){
        Map<String,Object> map=new HashMap<>(16);
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
            contract.setHtremark(filename);
            try {
                imges.getImg1().transferTo(new File(path, filename));
                System.out.println("上传成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(path);
            System.out.println(filename);
            map.put("flag",contract.updateById());
        }else {
            map.put("flag",contract.updateById());
        }
        return map;
    }
    @GetMapping("/deleteContract")
    public Map deleteContract(@RequestParam("htid") Integer htid){
        Map<String,Object> map=new HashMap<>(16);
        map.put("flag",contractService.removeById(htid));
        return map;
    }
}

