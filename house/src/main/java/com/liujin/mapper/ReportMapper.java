package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Report;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liujin
 * @since 2019-11-03
 */
public interface ReportMapper extends BaseMapper<Report> {

    List<Report> getReport(Integer hid);
}
