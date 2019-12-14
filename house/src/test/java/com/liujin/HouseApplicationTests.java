package com.liujin;

import com.liujin.entity.Emp;
import com.liujin.entity.Maintenance;
import com.liujin.entity.Stayin;
import com.liujin.mapper.EmpMapper;
import com.liujin.mapper.MaintenanceMapper;
import com.liujin.service.IStayinService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class HouseApplicationTests {
    @Autowired
    private MaintenanceMapper maintenanceMapper;
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private IStayinService stayinService;
    @Test
    void contextLoads() {
    }
    @Test
    public void test01(){
        List<Emp> allEmpInfo = empMapper.getAllEmpInfo(new Emp().setEflag(0));
        for (Emp emp : allEmpInfo) {
            System.out.println(emp);
        }
    }
    @Test
    public void test02(){
        List<Maintenance> allMaintenance = maintenanceMapper.getAllMaintenance();
        for (Maintenance maintenance : allMaintenance) {
            maintenance.setBsename(empMapper.getEname(maintenance.getBseid()).getEname());
            maintenance.setWxename(empMapper.getEname(maintenance.getWxeid()).getEname());
            System.out.println("报损人："+maintenance.getBsename()+";维修人:"+maintenance.getWxename());
        }
    }
    @Test
    public void  test03(){
        List<Stayin> allDetail = stayinService.getAllDetail(1);
        for (Stayin stayin : allDetail) {
            System.out.println("编号:"+stayin.getMid()+",房屋地址"+stayin.getHaddress()+",房号"+stayin.getHfh()+",客户名称"+stayin.getCname()+",客户电话"+stayin.getCtel()+",小计"+stayin.getTotal());
        }
    }
}
