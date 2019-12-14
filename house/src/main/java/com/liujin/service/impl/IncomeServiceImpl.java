package com.liujin.service.impl;

import com.liujin.entity.Income;
import com.liujin.mapper.IncomeMapper;
import com.liujin.service.IIncomeService;
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
public class IncomeServiceImpl extends ServiceImpl<IncomeMapper, Income> implements IIncomeService {
    @Autowired
    private IncomeMapper incomeMapper;
    @Override
    public List<Income> getAllIncomeInfo() {
        return incomeMapper.getAllIncomeInfo();
    }
}
