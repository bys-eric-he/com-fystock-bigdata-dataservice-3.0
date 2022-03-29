package com.fystock.bigdata.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fystock.bigdata.cloud.entity.hbase.RealTimeMarketCalculation;
import com.fystock.bigdata.cloud.response.CommonResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Sentinel处理器,若本次访问被限流或服务降级，则调用blockHandler指定的接口。
 *
 * @author He.Yong
 * @since 2021-03-11 17:57:39
 */
@Slf4j
public class SentinelBlockHandler {

    /**
     * 方法一定要是静态方法，否则无法解析，调用时会报500
     * 同时返回值和参数要保持和调用的方法一致
     *
     * @param tradeDate 交易日
     * @param ex        异常信息
     */
    public static CommonResult<List<RealTimeMarketCalculation>> getMarketDataByTradeDateHandler(String tradeDate, BlockException ex) {
        log.error("-------->行情数据查询, BlockHandler回调处理 交易日：" + tradeDate + "BlockException异常信息：" + ex.getMessage());
        return CommonResult.failed("-------->行情数据查询, BlockHandler回调处理 交易日：" + tradeDate + "BlockException异常信息：" + ex.getMessage());
    }
}
