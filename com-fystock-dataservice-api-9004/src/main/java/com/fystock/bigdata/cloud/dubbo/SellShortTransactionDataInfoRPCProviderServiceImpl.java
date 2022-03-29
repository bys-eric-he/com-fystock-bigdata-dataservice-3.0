package com.fystock.bigdata.cloud.dubbo;

import com.fystock.bigdata.cloud.entity.clickhouse.SellShortTransactionDataInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.ISellShortTransactionDataInfoRPCProviderService;
import com.fystock.bigdata.cloud.service.SellShortTransactionDataInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 即日卖空成交数据 Dubbo对内提供的RPC接口
 *
 * @author He.Yong
 * @since 2021-09-08 17:05:15
 */
@DubboService
public class SellShortTransactionDataInfoRPCProviderServiceImpl implements ISellShortTransactionDataInfoRPCProviderService {

    @Autowired
    private SellShortTransactionDataInfoService sellShortTransactionDataInfoService;

    /**
     * 插入即日卖空成交数据
     *
     * @param sellShortTransactionDataInfos
     * @return
     */
    @Override
    public CommonResult<Object> insertSellShortTransactionData(List<SellShortTransactionDataInfo> sellShortTransactionDataInfos) {
        int rows = sellShortTransactionDataInfoService.insertSellShortTransactionData(sellShortTransactionDataInfos);
        return CommonResult.success(String.format("插入即日卖空成交数据完成! 影响行数: %s 行!", rows), "Dubbo服务处理!");
    }
}
