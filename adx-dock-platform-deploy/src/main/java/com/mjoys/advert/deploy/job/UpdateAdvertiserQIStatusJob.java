package com.mjoys.advert.deploy.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/5/31 18:34.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Component
public class UpdateAdvertiserQIStatusJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.printf("\n" + new DateTime().toString("yyyy-MM-dd HH:mm:ss") + "  参数："
                          + shardingContext.getShardingParameter());
        System.out.printf("  片区：" + shardingContext.getShardingItem());
    }
}
