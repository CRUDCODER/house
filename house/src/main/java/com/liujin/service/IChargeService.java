package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Charge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujin
 * @since 2019-10-25
 */
public interface IChargeService extends IService<Charge> {
    List<Charge> getChargeDetail(@Param("mid") Integer mid);
}
