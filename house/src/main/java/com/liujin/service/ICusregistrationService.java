package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Cusregistration;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujin
 * @since 2019-10-19
 */
public interface ICusregistrationService extends IService<Cusregistration> {
    /**
     * 获取所有客户入住信息
     * @return
     */
    List<Cusregistration> getAllCusregistrationInfo();
}
