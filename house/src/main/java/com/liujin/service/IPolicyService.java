package com.liujin.service;

import com.liujin.entity.Policy;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujin
 * @since 2019-11-07
 */
public interface IPolicyService extends IService<Policy> {
    boolean addPolicy(Policy policy);
}
