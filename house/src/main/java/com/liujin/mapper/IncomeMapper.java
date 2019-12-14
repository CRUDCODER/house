package com.liujin.mapper;

import com.liujin.entity.Income;
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
public interface IncomeMapper extends BaseMapper<Income> {

    @Select("select * from income a,housedata b,emp c where a.hid=b.hid and a.eid=c.eid")
    List<Income> getAllIncomeInfo();

}
