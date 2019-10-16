package com.flyshadow.sharding.algorithm;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author ZHANGBIN
 * @ProjectName ytbs-jg-mcenter
 * @Title: MyPreciseShardingAlgorithm
 * @Description: 精确分片-用于处理使用单一键作为分片键的=与IN进行分片的场景
 * @date 2019/6/11 20:03
 */
@Slf4j
public class PeriodPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    private int BUCKETS = 2;
    @Override
    public String doSharding(Collection<String> collection,
                             PreciseShardingValue<Integer> preciseShardingValue) {
        String logicTableName = preciseShardingValue.getLogicTableName();
        Integer orderId = preciseShardingValue.getValue();
        int bucket = Hashing.consistentHash(orderId, BUCKETS);
        int seq = orderId % BUCKETS;

        String subTableName = logicTableName + "_" + seq;
        log.info("period sub-table:{}",subTableName);
        return subTableName;
    }
}
