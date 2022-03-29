package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.entity.clickhouse.SellShortTransactionDataInfo;
import com.fystock.bigdata.cloud.response.CommonResult;

import java.util.List;

/**
 * 卖空成交数据 Dubbo对内提供的RPC接口
 *
 * @author He.Yong
 * @since 2021-09-08 16:49:25
 */
public interface ISellShortTransactionDataInfoRPCProviderService {
    /**
     * 插入即日卖空成交数据
     *
     * @param sellShortTransactionDataInfos
     * @return
     */
    CommonResult<Object> insertSellShortTransactionData(List<SellShortTransactionDataInfo> sellShortTransactionDataInfos);
}
