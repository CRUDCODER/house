package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Emp;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liujin
 * @since 2019-10-30
 */
public interface EmpMapper extends BaseMapper<Emp> {
    Emp getEname(Integer eid);
    List<Emp> getAllEmpInfo(Emp emp);
}
