package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Charge;
import com.liujin.mapper.ChargeMapper;
import com.liujin.service.IChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liujin
 * @since 2019-10-25
 */
@Service
public class ChargeServiceImpl extends ServiceImpl<ChargeMapper, Charge> implements IChargeService {
    @Autowired
    private ChargeMapper chargeMapper;
    @Override
    public List<Charge> getChargeDetail(Integer mid) {
        return chargeMapper.getChargeDetail(mid);
    }
}
