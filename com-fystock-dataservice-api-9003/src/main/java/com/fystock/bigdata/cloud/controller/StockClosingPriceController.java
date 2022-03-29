package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.model.StockClosingPriceModel;
import com.fystock.bigdata.cloud.model.StockRelativeAbsoluteIndexModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.StockClosingPriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@Api(value = "/api/market/v1/stock-closing-price", tags = "股票收盘价")
@RestController
@RequestMapping("/api/market/v1/stock-closing-price")
public class StockClosingPriceController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private StockClosingPriceService stockClosingPriceService;

    @ApiOperation("获取指定股票指定交易日收盘价")
    @RequestMapping(path = "/getStockClosingPriceInfo/{stockCode}/{tradeDate}", method = RequestMethod.GET)
    public CommonResult<StockClosingPriceModel> getStockClosingPriceInfo(
            @PathVariable
            @NotBlank(message = "股票代码不能为空字符串!")
            @NotNull(message = "股票代码不能为空!")
                    String stockCode,
            @PathVariable
            @NotBlank(message = "交易日不能为空字符串!")
            @NotNull(message = "交易日不能为空!") String tradeDate) {
        StockClosingPriceModel stockClosingPriceModel = stockClosingPriceService.getStockClosingPriceInfo(stockCode, tradeDate);

        return CommonResult.success(stockClosingPriceModel, "查询数据成功, 处理服务端口：" + serverPort);
    }

    @ApiOperation("获取指定两只股票同一交易日相对强势指数")
    @RequestMapping(path = "/getStockClosingPriceInfo/{stockCodeA}/{stockCodeB}/{tradeDate}", method = RequestMethod.GET)
    public CommonResult<StockRelativeAbsoluteIndexModel> getStockRelativeAbsoluteIndex(@PathVariable
                                                                                       @NotBlank(message = "股票代码A不能为空字符串!")
                                                                                       @NotNull(message = "股票代码A不能为空!")
                                                                                               String stockCodeA,
                                                                                       @PathVariable
                                                                                       @NotBlank(message = "股票代码B不能为空字符串!")
                                                                                       @NotNull(message = "股票代码B不能为空!")
                                                                                               String stockCodeB,
                                                                                       @PathVariable
                                                                                       @NotBlank(message = "交易日不能为空字符串!")
                                                                                       @NotNull(message = "交易日不能为空!")
                                                                                               String tradeDate) {
        StockRelativeAbsoluteIndexModel stockRelativeAbsoluteIndexModel =
                stockClosingPriceService.getStockRelativeAbsoluteIndex(stockCodeA, stockCodeB, tradeDate);


        return CommonResult.success(stockRelativeAbsoluteIndexModel, "获取指定两只股票同一交易日相对强势指数数据成功, 处理服务端口：" + serverPort);
    }
}
