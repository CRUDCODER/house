package com.liujin.service.impl;

import com.liujin.entity.Spending;
import com.liujin.mapper.SpendingMapper;
import com.liujin.service.ISpendingService;
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
 * @since 2019-11-05
 */
@Service
public class SpendingServiceImpl extends ServiceImpl<SpendingMapper, Spending> implements ISpendingService {
    @Autowired
    private SpendingMapper spendingMapper;

    @Override
    public List<Spending> getAllSpending() {
        return spendingMapper.getAllSpending();
    }
}
