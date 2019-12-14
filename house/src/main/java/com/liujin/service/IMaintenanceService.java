package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Maintenance;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujin
 * @since 2019-11-03
 */
public interface IMaintenanceService extends IService<Maintenance> {
    List<Maintenance> getAllMaintenance();
}
