package com.liujin.mapper;

import com.liujin.entity.Policy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liujin
 * @since 2019-11-07
 */
public interface PolicyMapper extends BaseMapper<Policy> {

    boolean addPolicy(Policy policy);
}
