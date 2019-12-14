package com.liujin.mapper;

import com.liujin.entity.Maintenance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liujin
 * @since 2019-11-03
 */
public interface MaintenanceMapper extends BaseMapper<Maintenance> {


    List<Maintenance> getAllMaintenance();
}
