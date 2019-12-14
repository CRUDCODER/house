package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Spending;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujin
 * @since 2019-11-05
 */
public interface ISpendingService extends IService<Spending> {
    List<Spending> getAllSpending();
}
