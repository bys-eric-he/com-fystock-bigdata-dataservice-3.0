package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.entity.clickhouse.SellShortTransactionDataInfo;
import com.fystock.bigdata.cloud.model.SellShortCostInfo;
import com.fystock.bigdata.cloud.model.SellShortRatioInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.SellShortTransactionDataInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 即日卖空成交数据
 *
 * @author He.Yong
 * @since 2021-08-05 09:56:14
 */
@Api(value = "/api/dashboard/v1/sellshorttransactiondatainfo", tags = "即日卖空成交数据")
@RequestMapping("/api/dashboard/v1/sellshorttransactiondatainfo")
@RestController
public class SellShortTransactionDataInfoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private SellShortTransactionDataInfoService sellShortTransactionDataInfoService;

    /**
     * 获取指定交易日、指定股票做空占比
     *
     * @param tradeDate
     * @param stockCode
     * @return
     */
    @ApiOperation("获取指定交易日、指定股票做空占比")
    @GetMapping("/getSellShortRatioInfo/{tradeDate}/{stockCode}")
    public CommonResult<SellShortRatioInfo> getSellShortRatioInfo(@PathVariable String tradeDate, @PathVariable String stockCode) {
        SellShortRatioInfo sellShortRatioInfo = sellShortTransactionDataInfoService.getSellShortRatioInfo(tradeDate, stockCode);

        return CommonResult.success(sellShortRatioInfo, "交易日【" + tradeDate + "】,股票【" + stockCode + "】做空占比成功,处理服务端口：" + serverPort);
    }

    /**
     * 获取指定交易日、指定股票做空成本
     *
     * @param tradeDate
     * @param stockCode
     * @return
     */
    @ApiOperation("获取指定交易日、指定股票做空成本")
    @GetMapping("/getSellShortCostInfo/{tradeDate}/{stockCode}")
    public CommonResult<SellShortCostInfo> getSellShortCostInfo(@PathVariable String tradeDate, @PathVariable String stockCode) {
        SellShortCostInfo sellShortCostInfo = sellShortTransactionDataInfoService.getSellShortCostInfo(tradeDate, stockCode);

        return CommonResult.success(sellShortCostInfo, "交易日【" + tradeDate + "】,股票【" + stockCode + "】做空成本,处理服务端口：" + serverPort);
    }

    /**
     * 获取指定交易日卖空数据
     *
     * @param tradeDate
     * @return
     */
    @ApiOperation("获取指定交易日卖空数据")
    @GetMapping("/getSellShortTransactionData/{tradeDate}")
    public CommonResult<List<SellShortTransactionDataInfo>> getSellShortTransactionData(@PathVariable String tradeDate) {
        List<SellShortTransactionDataInfo> sellShortTransactionDataInfos =
                sellShortTransactionDataInfoService.getSellShortTransactionData(tradeDate);

        return CommonResult.success(sellShortTransactionDataInfos, "获取日期为 " + tradeDate + " 的做空成交数据成功,处理服务端口：" + serverPort);
    }

    /**
     * 插入即日卖空成交数据
     *
     * @param sellShortTransactionDataInfos
     * @return
     */
    @ApiOperation("插入即日做空成交数据")
    @PostMapping("/insertSellShortTransactionData")
    public CommonResult<Object> insertSellShortTransactionData(@RequestBody List<SellShortTransactionDataInfo> sellShortTransactionDataInfos) {
        int rows = sellShortTransactionDataInfoService.insertSellShortTransactionData(sellShortTransactionDataInfos);
        return CommonResult.success(String.format("插入即日卖空成交数据完成! 影响行数: %s 行!", rows), "处理服务端口：" + serverPort);
    }
}

