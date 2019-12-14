package com.liujin.service.impl;

import com.liujin.entity.Housedata;
import com.liujin.mapper.HousedataMapper;
import com.liujin.service.IHousedataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liujin
 * @since 2019-10-30
 */
@Service
public class HousedataServiceImpl extends ServiceImpl<HousedataMapper, Housedata> implements IHousedataService {

    @Autowired
    private HousedataMapper housedataMapper;
    @Override
    public List<Housedata> getAllHouseData(Housedata housedata) {
        List<Housedata> allHouseData = housedataMapper.getAllHouseData(housedata);
        for (Housedata allHouseDatum : allHouseData) {
            if (allHouseDatum.getHflag()==1){
                allHouseDatum.setFlag("待出租");
            }else if (allHouseDatum.getHflag()==0){
                allHouseDatum.setFlag("已出租");
            }else if (allHouseDatum.getHflag()==2){
                allHouseDatum.setFlag("维修中");
            }
        }
        return allHouseData;
    }
}
