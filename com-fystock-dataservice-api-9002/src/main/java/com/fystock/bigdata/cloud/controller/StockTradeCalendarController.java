package com.fystock.bigdata.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fystock.bigdata.cloud.handler.SentinelBlockHandler;
import com.fystock.bigdata.cloud.handler.SentinelFallBackHandler;
import com.fystock.bigdata.cloud.model.StockTradeCalendarModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.StockTradeCalendarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * 交易日历表查询控制器
 *
 * @author He.Yong
 * @since 2021-02-04 17:24:22
 */
@Slf4j
@Validated
@RestController
@Api(value = "/api/dataservice/v1/stock_trade_calendar", tags = "交易日历")
@RequestMapping(value = "/api/dataservice/v1/stock_trade_calendar")
public class StockTradeCalendarController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private StockTradeCalendarService stockTradeCalendarService;

    @ApiOperation("根据自然日期、区域编码,获取交易日")
    @RequestMapping(value = "/find/{normalDate}/{regionCode}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "normalDate", value = "自然日期(年-月-日)", required = true, dataType = "LocalDate", defaultValue = "2021-01-05"),
            @ApiImplicitParam(name = "regionCode", value = "交易地区编码", required = true, dataType = "String", defaultValue = "HK")
    })
    public CommonResult<StockTradeCalendarModel> findByNormalDate(@PathVariable
                                                                  @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                  @NotNull(message = "自然日期(年-月-日)不能为空!") LocalDate normalDate,
                                                                  @PathVariable
                                                                  @NotBlank(message = "交易地区编码不能为空字符串!")
                                                                  @NotNull(message = "交易地区编码不能为空!") String regionCode) {
        StockTradeCalendarModel stockTradeCalendarModel = stockTradeCalendarService.findByNormalDateAndRegionCode(normalDate, regionCode);

        return CommonResult.success(stockTradeCalendarModel, "查询数据成功, 处理服务端口：" + serverPort);
    }

    @ApiOperation("根据年-月获取当月的交易日历")
    @RequestMapping(value = "/find/month/{normalDate}/{regionCode}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "normalDate", value = "自然日期(年-月-日)", required = true, dataType = "LocalDate", defaultValue = "2021-01-05"),
            @ApiImplicitParam(name = "regionCode", value = "交易地区编码", required = true, dataType = "String", defaultValue = "HK")
    })
    @SentinelResource(value = "stockTradeCalendarService",
            fallbackClass = SentinelFallBackHandler.class, fallback = "stockTradeCalendarFallbackHandler",
            blockHandlerClass = SentinelBlockHandler.class, blockHandler = "stockTradeCalendarExceptionHandler")
    public CommonResult<List<StockTradeCalendarModel>> findByYearAndMonth(@PathVariable
                                                                          @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                          @NotNull(message = "自然日期(年-月-日)不能为空!") LocalDate normalDate,
                                                                          @PathVariable
                                                                          @NotBlank(message = "交易地区编码不能为空字符串!")
                                                                          @NotNull(message = "交易地区编码不能为空!") String regionCode) {
        List<StockTradeCalendarModel> stockTradeCalendarModels = stockTradeCalendarService.findByYearAndMonth(normalDate, regionCode);

        return CommonResult.success(stockTradeCalendarModels, "查询数据成功, 处理服务端口：" + serverPort);
    }
}
