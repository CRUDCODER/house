package com.liujin.service.impl;

import com.liujin.entity.Policy;
import com.liujin.mapper.PolicyMapper;
import com.liujin.service.IPolicyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liujin
 * @since 2019-11-07
 */
@Service
public class PolicyServiceImpl extends ServiceImpl<PolicyMapper, Policy> implements IPolicyService {
    @Autowired
    private PolicyMapper policyMapper;
    @Override
    public boolean addPolicy(Policy policy) {
        System.out.println(policy.getCremark());
        return policyMapper.addPolicy(policy);
    }
}
