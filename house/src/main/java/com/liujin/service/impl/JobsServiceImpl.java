package com.liujin.service.impl;

import com.liujin.entity.Jobs;
import com.liujin.mapper.JobsMapper;
import com.liujin.service.IJobsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liujin
 * @since 2019-10-30
 */
@Service
public class JobsServiceImpl extends ServiceImpl<JobsMapper, Jobs> implements IJobsService {

}
