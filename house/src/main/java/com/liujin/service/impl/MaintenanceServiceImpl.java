package com.liujin.service.impl;

import com.liujin.entity.Maintenance;
import com.liujin.mapper.EmpMapper;
import com.liujin.mapper.MaintenanceMapper;
import com.liujin.service.IMaintenanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liujin
 * @since 2019-11-03
 */
@Service
public class MaintenanceServiceImpl extends ServiceImpl<MaintenanceMapper, Maintenance> implements IMaintenanceService {
    @Autowired
    private MaintenanceMapper maintenanceMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Maintenance> getAllMaintenance() {
        List<Maintenance> allMaintenance = maintenanceMapper.getAllMaintenance();
        for (Maintenance maintenance : allMaintenance) {
            maintenance.setBsename(empMapper.getEname(maintenance.getBseid()).getEname());
            maintenance.setWxename(empMapper.getEname(maintenance.getWxeid()).getEname());
        }
        return allMaintenance;
    }
}
