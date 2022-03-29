package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.redis.BigScreenTradeStockActiveTopInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.BigScreenTradeStockActiveTopInfoService;
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
 * 港股交易个股活跃TOP排行榜
 *
 * @author He.Yong
 * @since 2021-06-23 17:05:15
 */
@Api(value = "/api/dashboard/v1/bigscreentradestockactivetopinfo", tags = "港股交易个股活跃TOP排行榜")
@RequestMapping("/api/dashboard/v1/bigscreentradestockactivetopinfo")
@RestController
public class BigScreenTradeStockActiveTopInfoController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private BigScreenTradeStockActiveTopInfoService bigScreenTradeStockActiveTopInfoService;

    /**
     * 获取当前交易活跃个股Top10
     *
     * @return
     */
    @ApiOperation("获取当前交易活跃个股Top10")
    @GetMapping("/getTradeStockActiveTopInfo")
    public CommonResult<List<BigScreenTradeStockActiveTopInfo>> getTradeStockActiveTopInfo() {
        List<BigScreenTradeStockActiveTopInfo> tradeStockActiveTopInfos =
                bigScreenTradeStockActiveTopInfoService.getTradeStockActiveTopInfo();
        return CommonResult.success(tradeStockActiveTopInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据指定交易日获取交易活跃个股Top10
     *
     * @param tradeDate
     * @return
     */
    @ApiOperation("根据指定交易日获取交易活跃个股Top10")
    @GetMapping("/getTradeStockActiveTopInfo/{tradeDate}")
    public CommonResult<List<BigScreenTradeStockActiveTopInfo>> getTradeStockActiveTopInfo(@PathVariable String tradeDate) {
        List<BigScreenTradeStockActiveTopInfo> tradeStockActiveTopInfos =
                bigScreenTradeStockActiveTopInfoService.getTradeStockActiveTopInfo(tradeDate);
        return CommonResult.success(tradeStockActiveTopInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据近一周交易日获取交易活跃个股Top10
     *
     * @return
     */
    @ApiOperation("根据近一周交易日获取交易活跃个股Top10")
    @GetMapping("/getLastWeekTradeStockActiveTopInfo")
    public CommonResult<List<BigScreenTradeStockActiveTopInfo>> getLastWeekTradeStockActiveTopInfo() {
        String beginTradeDate = DateTimeUtil.getLastWeekDate();
        String endTradeDate = DateTimeUtil.getCurrentDate();
        List<BigScreenTradeStockActiveTopInfo> tradeStockActiveTopInfos =
                bigScreenTradeStockActiveTopInfoService.getTradeStockActiveTopInfoByInterval(beginTradeDate, endTradeDate);
        return CommonResult.success(tradeStockActiveTopInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据近一周交易日获取交易活跃个股Top10
     *
     * @return
     */
    @ApiOperation("根据近一月交易日获取交易活跃个股Top10")
    @GetMapping("/getLastMonthTradeStockActiveTopInfo")
    public CommonResult<List<BigScreenTradeStockActiveTopInfo>> getLastMonthTradeStockActiveTopInfo() {
        String beginTradeDate = DateTimeUtil.getLastMonthDate();
        String endTradeDate = DateTimeUtil.getCurrentDate();
        List<BigScreenTradeStockActiveTopInfo> tradeStockActiveTopInfos =
                bigScreenTradeStockActiveTopInfoService.getTradeStockActiveTopInfoByInterval(beginTradeDate, endTradeDate);
        return CommonResult.success(tradeStockActiveTopInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }
}

