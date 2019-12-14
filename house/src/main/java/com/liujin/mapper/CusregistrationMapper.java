package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Cusregistration;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liujin
 * @since 2019-10-19
 */
public interface CusregistrationMapper extends BaseMapper<Cusregistration> {
    /**
     * 查询所有客户登记信息
     * @return
     */
    @Select("select * from cusregistration a ,emp b where a.eid=b.eid")
    List<Cusregistration> getAllCusregistrationInfo();
}
