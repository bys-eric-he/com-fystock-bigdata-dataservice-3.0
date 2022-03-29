package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.entity.clickhouse.CcassHoldingsInfo;
import com.fystock.bigdata.cloud.response.CommonResult;

import java.util.List;

/**
 * CCASS持仓数据 Dubbo对内提供的RPC接口
 *
 * @author He.Yong
 * @since 2021-09-08 16:49:25
 */
public interface ICcassHoldingsInfoRPCProviderService {
    /**
     * 开始持久化CCASS持仓数据比例
     *
     * @param updateDate
     * @return
     */
    CommonResult<Object> startToInsertCcassHoldingsRatioInfo(String updateDate);

    /**
     * 插入CCASS持仓数据
     *
     * @param ccassHoldingsInfos
     * @return
     */
    CommonResult<Object> insertCcassHoldingsInfo(List<CcassHoldingsInfo> ccassHoldingsInfos);
}
