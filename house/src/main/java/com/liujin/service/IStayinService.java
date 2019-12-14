package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Stayin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujin
 * @since 2019-10-19
 */
public interface IStayinService extends IService<Stayin> {
    List<Stayin> getAllStayin();
    Stayin getOneStayin(Integer mid);
    List<Stayin> getHistoryInfo();

    /**
     * 组合查询
     * @param stayin
     * @return
     */
    List<Stayin> combinationQuery(Stayin stayin);

    List<Stayin> getOverdueInfo(@Param("date") String date);

    List<Stayin> getAllDetail(@Param("hid") Integer hid);
}
