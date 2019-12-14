package com.liujin.service.impl;

import com.liujin.entity.Emp;
import com.liujin.mapper.EmpMapper;
import com.liujin.service.IEmpService;
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
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> getAllEmpInfo(Emp emp) {
        return empMapper.getAllEmpInfo(emp);
    }
}
