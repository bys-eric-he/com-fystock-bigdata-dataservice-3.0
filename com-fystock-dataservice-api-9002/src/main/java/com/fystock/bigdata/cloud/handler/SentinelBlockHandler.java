package com.fystock.bigdata.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fystock.bigdata.cloud.model.StockTradeCalendarModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Sentinel处理器, 若本次访问被限流或服务降级，则调用blockHandler指定的接口。
 *
 * @author He.Yong
 * @since 2021-02-04 18:31:22
 */
@Slf4j
public class SentinelBlockHandler {
    /**
     * 方法一定要是静态方法，否则无法解析，调用时会报500
     * 同时返回值和参数要保持和调用的方法一致
     *
     * @param ex 异常信息
     */
    public static CommonResult<List<StockTradeCalendarModel>> stockTradeCalendarExceptionHandler(LocalDate normalDate, String regionCode, BlockException ex) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = normalDate.format(pattern);
        log.error("-------->获取交易日历查询限流,查询条件日期:" + date + "市场:" + regionCode + ", BlockHandler回调处理BlockException异常信息：" + ex.getMessage());
        return CommonResult.failed("-------->获取交易日历查询限流, BlockHandler回调处理BlockException异常信息：" + ex.getMessage());
    }
}
