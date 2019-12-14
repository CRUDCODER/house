package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Loss;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujin
 * @since 2019-11-03
 */
public interface ILossService extends IService<Loss> {
    List<Loss> getAllLostInfo(Loss loss);
}
