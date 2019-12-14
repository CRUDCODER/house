package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Housedata;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujin
 * @since 2019-10-30
 */
public interface IHousedataService extends IService<Housedata> {
    List<Housedata> getAllHouseData(Housedata housedata);
}
