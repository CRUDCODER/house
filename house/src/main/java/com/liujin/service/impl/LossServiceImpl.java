package com.liujin.service.impl;

import com.liujin.entity.Loss;
import com.liujin.mapper.LossMapper;
import com.liujin.service.ILossService;
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
 * @since 2019-11-03
 */
@Service
public class LossServiceImpl extends ServiceImpl<LossMapper, Loss> implements ILossService {

    @Autowired
    private LossMapper lossMapper;
    @Override
    public List<Loss> getAllLostInfo(Loss loss) {
        return lossMapper.getAllLostInfo(loss);
    }
}
