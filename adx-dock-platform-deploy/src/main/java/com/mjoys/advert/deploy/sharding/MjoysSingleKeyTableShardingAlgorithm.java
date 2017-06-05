package com.mjoys.advert.deploy.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/6/2 17:24.<br/>
 * 功能描述 : 分库策略.<br/>
 * 变更记录 : .<br/>
 */
public class MjoysSingleKeyTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames,
                                  ShardingValue<Long> shardingValue) {
        return getDataSource(availableTargetNames, shardingValue.getValue());
    }

    /**
     * Test string.
     *
     * @param availableTargetNames the available target names
     * @param shardingValue the sharding value
     * @return the string
     */
    private String getDataSource(Collection<String> availableTargetNames, Long shardingValue) {
        String modStr = shardingValue % 2+ "";
        for (String each : availableTargetNames) {
            if (each.endsWith(modStr)) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames,
                                           ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Long modValue : shardingValue.getValues()) {
            result.add(getDataSource(availableTargetNames, modValue));
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
                                                ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Long modValue : shardingValue.getValues()) {
            result.add(getDataSource(availableTargetNames, modValue));
        }
        return result;
    }
}
