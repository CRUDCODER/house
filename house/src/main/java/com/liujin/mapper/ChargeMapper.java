package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Charge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liujin
 * @since 2019-10-25
 */
public interface ChargeMapper extends BaseMapper<Charge> {

    @Select("select  * from charge a,stayin b,cusregistration c,housedata d,emp e where a.mid=b.mid and b.cid=c.cid and b.hid=d.hid and a.eid=e.eid and a.mid=#{mid}")
    List<Charge> getChargeDetail(@Param("mid") Integer mid);
    @Select("select SUM(b.myzj) myzj from stayin a , charge b where a.mid=b.mid and a.mid=#{mid}")
    Charge getTotalChargeByMid(@Param("mid") Integer mid);
}
