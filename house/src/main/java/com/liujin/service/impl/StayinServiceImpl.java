package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Charge;
import com.liujin.entity.Stayin;
import com.liujin.mapper.ChargeMapper;
import com.liujin.mapper.StayinMapper;
import com.liujin.service.IStayinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liujin
 * @since 2019-10-19
 */
@Service
public class StayinServiceImpl extends ServiceImpl<StayinMapper, Stayin> implements IStayinService {
    @Autowired
    private StayinMapper stayinMapper;
    @Autowired
    private ChargeMapper chargeMapper;
    @Override
    public List<Stayin> getAllStayin() {
        return stayinMapper.getAllStayin();
    }

    @Override
    public Stayin getOneStayin(Integer mid) {
        return stayinMapper.getOneStayin(mid);
    }

    @Override
    public List<Stayin> getHistoryInfo() {
        return stayinMapper.getHistoryInfo();
    }

    @Override
    public List<Stayin> combinationQuery(Stayin stayin) {
        return stayinMapper.combinationQuery(stayin);
    }

    @Override
    public List<Stayin> getOverdueInfo(String date) {
        return stayinMapper.getOverdueInfo(date);
    }

    @Override
    public List<Stayin> getAllDetail(Integer hid) {
        List<Stayin> allDetail = stayinMapper.getAllDetail(hid);
        for (Stayin stayin : allDetail) {
            System.out.println(stayin.getMid());
            Charge charge = chargeMapper.getTotalChargeByMid(stayin.getMid());
            if (charge!=null){
                stayin.setTotal(charge.getMyzj());
            }else {
                stayin.setTotal(0.f);
            }
        }
        return allDetail;
    }
}
