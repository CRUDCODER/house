package com.liujin.mapper;

import com.liujin.entity.Housedata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liujin
 * @since 2019-10-30
 */
public interface HousedataMapper extends BaseMapper<Housedata> {

    /**
     * 获取房屋资料
     * @param housedata
     * @return
     */
    List<Housedata> getAllHouseData(Housedata housedata);
}
