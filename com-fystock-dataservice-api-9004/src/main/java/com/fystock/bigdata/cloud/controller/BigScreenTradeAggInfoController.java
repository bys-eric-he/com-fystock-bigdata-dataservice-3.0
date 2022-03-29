package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.model.TradeMoneySumInfo;
import com.fystock.bigdata.cloud.model.TradeStockCountInfo;
import com.fystock.bigdata.cloud.model.TradeUserCountInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.BigScreenTradeAggInfoService;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 交易用户数走势、成交笔数走势、交易额走势实时数据
 *
 * @author He.Yong
 * @since 2021-06-17 14:25:49
 */
@Api(value = "/api/dashboard/v1/bigscreentradeagginfo", tags = "交易用户数走势、成交笔数走势、交易额走势实时数据")
@RequestMapping("/api/dashboard/v1/bigscreentradeagginfo")
@RestController
public class BigScreenTradeAggInfoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private BigScreenTradeAggInfoService bigScreenTradeAggInfoService;

    /**
     * 获取当前最新交易额走势数据
     *
     * @return
     */
    @ApiOperation("获取当前最新交易额走势数据")
    @GetMapping("/getTradeMoneySumInfo")
    public CommonResult<Map<String, TradeMoneySumInfo>> getTradeMoneySumInfo() {
        Map<String, TradeMoneySumInfo> result = bigScreenTradeAggInfoService.getTradeMoneySumInfo();
        return CommonResult.success(result, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取指定交易日交易额走势数据
     *
     * @return
     */
    @ApiOperation("获取指定交易日交易额走势数据")
    @GetMapping("/getTradeMoneySumInfo/{tradeDate}")
    public CommonResult<Map<String, TradeMoneySumInfo>> getTradeMoneySumInfo(@PathVariable String tradeDate) {
        Map<String, TradeMoneySumInfo> result = bigScreenTradeAggInfoService.getTradeMoneySumInfo(tradeDate);
        return CommonResult.success(result, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取当前最新买入、卖出笔数走势数据
     *
     * @return
     */
    @ApiOperation("获取当前最新买入、卖出笔数走势数据")
    @GetMapping("/getTradeStockCountInfo")
    public CommonResult<Map<String, TradeStockCountInfo>> getTradeStockCountInfo() {
        Map<String, TradeStockCountInfo> result = bigScreenTradeAggInfoService.getTradeStockCountInfo();
        return CommonResult.success(result, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取指定交易日买入、卖出笔数走势数据
     *
     * @return
     */
    @ApiOperation("获取指定交易日买入、卖出笔数走势数据")
    @GetMapping("/getTradeStockCountInfo/{tradeDate}")
    public CommonResult<Map<String, TradeStockCountInfo>> getTradeStockCountInfo(@PathVariable String tradeDate) {
        Map<String, TradeStockCountInfo> result = bigScreenTradeAggInfoService.getTradeStockCountInfo(tradeDate);
        return CommonResult.success(result, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取当前最新交易用户数走势数据
     *
     * @return
     */
    @ApiOperation("获取当前最新交易用户数走势数据")
    @GetMapping("/getTradeUserCountInfo")
    public CommonResult<Map<String, TradeUserCountInfo>> getTradeUserCountInfo() {
        Map<String, TradeUserCountInfo> result = bigScreenTradeAggInfoService.getTradeUserCountInfo();
        return CommonResult.success(result, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取指定交易日交易用户数走势数据
     *
     * @return
     */
    @ApiOperation("获取指定交易日交易用户数走势数据")
    @GetMapping("/getTradeUserCountInfo/{tradeDate}")
    public CommonResult<Map<String, TradeUserCountInfo>> getTradeUserCountInfo(@PathVariable String tradeDate) {
        Map<String, TradeUserCountInfo> result = bigScreenTradeAggInfoService.getTradeUserCountInfo(tradeDate);
        return CommonResult.success(result, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取近一月交易日交易用户数量走势数据
     *
     * @return
     */
    @ApiOperation("获取近一月交易日交易用户数量走势数据")
    @GetMapping("/getLastMonthTradeUserCountInfo")
    public CommonResult<Map<String, TradeUserCountInfo>> getLastMonthTradeUserCountInfo() {
        String beginTradeDate = DateTimeUtil.getLastMonthDate();
        String endTradeDate = DateTimeUtil.getCurrentDate();
        Map<String, TradeUserCountInfo> result = bigScreenTradeAggInfoService.getLastMonthTradeUserCountInfo(beginTradeDate, endTradeDate);
        return CommonResult.success(result, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取近一月交易日交易额走势数据
     *
     * @return
     */
    @ApiOperation("获取近一月交易日交易额走势数据")
    @GetMapping("/getLastMonthTradeMoneySumInfo")
    public CommonResult<Map<String, TradeMoneySumInfo>> getLastMonthTradeMoneySumInfo() {
        String beginTradeDate = DateTimeUtil.getLastMonthDate();
        String endTradeDate = DateTimeUtil.getCurrentDate();
        Map<String, TradeMoneySumInfo> result = bigScreenTradeAggInfoService.getLastMonthTradeMoneySumInfo(beginTradeDate, endTradeDate);
        return CommonResult.success(result, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取近一月交易日交易额走势数据
     *
     * @return
     */
    @ApiOperation("获取近一个月交易日买入、卖出笔数走势数据")
    @GetMapping("/getLastMonthTradeTradeStockCountInfo")
    public CommonResult<Map<String, TradeStockCountInfo>> getLastMonthTradeTradeStockCountInfo() {
        String beginTradeDate = DateTimeUtil.getLastMonthDate();
        String endTradeDate = DateTimeUtil.getCurrentDate();
        Map<String, TradeStockCountInfo> result = bigScreenTradeAggInfoService.getLastMonthTradeTradeStockCountInfo(beginTradeDate, endTradeDate);
        return CommonResult.success(result, "查询数据成功, 处理服务端口：" + serverPort);
    }
}
