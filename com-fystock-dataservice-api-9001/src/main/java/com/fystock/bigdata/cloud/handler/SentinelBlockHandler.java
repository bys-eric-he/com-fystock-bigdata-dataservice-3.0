package com.fystock.bigdata.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fystock.bigdata.cloud.model.RiskWarnInfoModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Sentinel处理器, 若本次访问被限流或服务降级,则调用blockHandler指定的接口。
 *
 * @author He.Yong
 * @since 2021-03-15 09:56:21
 */
@Slf4j
public class SentinelBlockHandler {
    /**
     * 方法一定要是静态方法，否则无法解析，调用时会报500
     * 同时返回值和参数要保持和调用的方法一致
     *
     * @param ex 异常信息
     */
    public static CommonResult<List<RiskWarnInfoModel>> riskWarnInfoExceptionHandler(BlockException ex) {
        log.error("-------->获取当前最新预警信息查询限流, BlockHandler回调处理BlockException异常信息：" + ex.getMessage());
        return CommonResult.failed("-------->获取当前最新预警信息查询限流, BlockHandler回调处理BlockException异常信息：" + ex.getMessage());
    }
}
