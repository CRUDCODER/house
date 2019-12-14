package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Emp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujin
 * @since 2019-10-30
 */
public interface IEmpService extends IService<Emp> {
    List<Emp> getAllEmpInfo(Emp emp);
}
