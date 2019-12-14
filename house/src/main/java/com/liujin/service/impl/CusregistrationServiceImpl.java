package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Cusregistration;
import com.liujin.mapper.CusregistrationMapper;
import com.liujin.service.ICusregistrationService;
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
public class CusregistrationServiceImpl extends ServiceImpl<CusregistrationMapper, Cusregistration> implements ICusregistrationService {
    @Autowired
    private CusregistrationMapper cusregistrationMapper;
    @Override
    public List<Cusregistration> getAllCusregistrationInfo() {
        return cusregistrationMapper.getAllCusregistrationInfo();
    }
}
