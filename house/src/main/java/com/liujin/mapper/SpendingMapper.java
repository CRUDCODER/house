package com.liujin.mapper;

import com.liujin.entity.Spending;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liujin
 * @since 2019-11-05
 */
public interface SpendingMapper extends BaseMapper<Spending> {

    @Select("select * from spending a,housedata b,emp c where a.hid=b.hid and a.eid=c.eid")
    List<Spending> getAllSpending();
}
