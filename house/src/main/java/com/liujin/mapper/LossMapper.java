package com.liujin.mapper;

import com.liujin.entity.Loss;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liujin
 * @since 2019-11-03
 */
public interface LossMapper extends BaseMapper<Loss> {


    List<Loss> getAllLostInfo(Loss loss);
}
