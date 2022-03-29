package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.model.TradeCountInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.BigScreenTradeCountInfoService;
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
 * 港股交易个股数量
 *
 * @author He.Yong
 * @since 2021-06-15 11:25:21
 */
@Api(value = "/api/dashboard/v1/bigscreentradecountinfo", tags = "港股交易个股数量")
@RequestMapping("/api/dashboard/v1/bigscreentradecountinfo")
@RestController
public class BigScreenTradeCountInfoController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private BigScreenTradeCountInfoService bigScreenTradeCountInfoService;

    /**
     * 获取当前港股交易个股数量
     *
     * @return
     */
    @ApiOperation("获取当前港股交易个股数量")
    @GetMapping("/getTradeCountInfo")
    public CommonResult<Map<String, TradeCountInfo>> getTradeCountInfo() {
        Map<String, TradeCountInfo> tradeCountInfos = bigScreenTradeCountInfoService.getTradeCountInfo();
        return CommonResult.success(tradeCountInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取指定交易日港股交易个股数量
     *
     * @return
     */
    @ApiOperation("获取指定交易日港股交易个股数量")
    @GetMapping("/getTradeCountInfo/{tradeDate}")
    public CommonResult<Map<String, TradeCountInfo>> getTradeCountInfo(@PathVariable String tradeDate) {
        Map<String, TradeCountInfo> tradeCountInfos = bigScreenTradeCountInfoService.getTradeCountInfo(tradeDate);
        return CommonResult.success(tradeCountInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }
}
