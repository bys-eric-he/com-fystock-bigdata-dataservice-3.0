package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.model.TradeInduInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.BigScreenTradeInduInfoService;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 港股行业交易量排行榜
 *
 * @author He.Yong
 * @since 2021-06-16 10:25:21
 */
@Api(value = "/api/dashboard/v1/bigscreentradeinduinfo", tags = "港股行业交易量排行榜")
@RequestMapping("/api/dashboard/v1/bigscreentradeinduinfo")
@RestController
public class BigScreenTradeInduInfoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private BigScreenTradeInduInfoService bigScreenTradeInduInfoService;

    /**
     * 获取港股行业交易量TOP10
     *
     * @return
     */
    @ApiOperation("获取当前港股行业交易量TOP10")
    @GetMapping("/getTradeInduTopInfo")
    public CommonResult<List<TradeInduInfo>> getTradeInduTopInfo() {
        List<TradeInduInfo> tradeInduInfos = bigScreenTradeInduInfoService.getTradeInduTopInfo();
        return CommonResult.success(tradeInduInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取指定交易日港股行业交易量TOP10
     *
     * @return
     */
    @ApiOperation("获取指定交易日港股行业交易量TOP10")
    @GetMapping("/getTradeInduTopInfo/{tradeDate}")
    public CommonResult<List<TradeInduInfo>> getTradeInduTopInfo(@PathVariable String tradeDate) {
        List<TradeInduInfo> tradeInduInfos = bigScreenTradeInduInfoService.getTradeInduTopInfo(tradeDate);
        return CommonResult.success(tradeInduInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取近一周交易日港股行业交易量TOP10
     *
     * @return
     */
    @ApiOperation("获取近一周交易日港股行业交易量TOP10")
    @GetMapping("/getLastWeekTradeInduTopInfo")
    public CommonResult<List<TradeInduInfo>> getLastWeekTradeInduTopInfo() {
        String beginTradeDate = DateTimeUtil.getLastWeekDate();
        String endTradeDate = DateTimeUtil.getCurrentDate();
        List<TradeInduInfo> tradeInduInfos = bigScreenTradeInduInfoService.getTradeInduTopInfoByInterval(beginTradeDate, endTradeDate);
        return CommonResult.success(tradeInduInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取近一周交易日港股行业交易量TOP10
     *
     * @return
     */
    @ApiOperation("获取近一月交易日港股行业交易量TOP10")
    @GetMapping("/getLastMonthTradeInduTopInfo")
    public CommonResult<List<TradeInduInfo>> getLastMonthTradeInduTopInfo() {
        String beginTradeDate = DateTimeUtil.getLastMonthDate();
        String endTradeDate = DateTimeUtil.getCurrentDate();
        List<TradeInduInfo> tradeInduInfos = bigScreenTradeInduInfoService.getTradeInduTopInfoByInterval(beginTradeDate, endTradeDate);
        return CommonResult.success(tradeInduInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }
}
