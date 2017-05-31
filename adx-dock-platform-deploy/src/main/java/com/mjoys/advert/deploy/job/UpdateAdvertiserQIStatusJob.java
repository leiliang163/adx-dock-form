package com.mjoys.advert.deploy.job;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.mjoys.advert.biz.bo.IAdvertiserQIBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/5/31 15:17.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Component
public class UpdateAdvertiserQIStatusJob extends AbstractSimpleElasticJob {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateAdvertiserQIStatusJob.class);

    @Autowired
    private IAdvertiserQIBo     advertiserQIBo;

    @Override
    public void process(JobExecutionMultipleShardingContext shardingContext) {
        try {
            LOGGER.info("查询广告主审核状态开始");
//            advertiserQIBo.updateAdvertiserQIStatus();
            LOGGER.info("查询广告主审核状态结束");
        } catch (Exception e) {
            LOGGER.warn("更新广告主审核状态作业失败", e);
        }
    }
}
