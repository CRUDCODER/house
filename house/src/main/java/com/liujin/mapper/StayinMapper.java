package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Stayin;
import org.apache.ibatis.annotations.Param;
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
public interface StayinMapper extends BaseMapper<Stayin> {
    /**
     * 获取所有登记入住信息
     * @return
     */
    @Select("select * from stayin a,cusregistration b,emp c,housedata d where a.cid=b.cid and a.eid=c.eid and a.hid= d.hid and a.mflag=1")
    List<Stayin> getAllStayin();

    /**
     * 根据id查询信息
     * @param mid
     * @return
     */
    @Select("select * from stayin a,cusregistration b,emp c,housedata d,area e,sort f where a.cid=b.cid and a.eid=c.eid and a.hid= d.hid and d.aid=e.aid and d.sid=f.sid  and a.mid=#{mid}")
    Stayin getOneStayin(Integer mid);

    /**
     * 获取历史出租记录
     * @return
     */
    @Select("select * from stayin a,cusregistration b,emp c,housedata d where a.cid=b.cid and a.eid=c.eid and a.hid= d.hid and a.mflag=0")
    List<Stayin> getHistoryInfo();

    List<Stayin> combinationQuery(Stayin stayin);

    @Select("select * from stayin a,cusregistration b,emp c,housedata d where a.cid=b.cid and a.eid=c.eid and a.hid= d.hid and mbegintime=#{date}")
    List<Stayin> getOverdueInfo(@Param("date") String date);

    @Select("select * from stayin a, housedata b,cusregistration c where a.hid=b.hid and a.cid=c.cid and a.hid=#{hid}")
    List<Stayin> getAllDetail(@Param("hid") Integer hid);
}
