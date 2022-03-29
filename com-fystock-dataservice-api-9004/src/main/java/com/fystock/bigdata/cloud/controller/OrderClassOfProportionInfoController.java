package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.model.OrderClassOfProportionInfo;
import com.fystock.bigdata.cloud.model.TickerQuatInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.OrderClassOfProportionInfoService;
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
 * 成交明细N日大单占比
 *
 * @author He.Yong
 * @since 2021-07-30 11:07:25
 */
@Api(value = "/api/dashboard/v1/orderclassofproportioninfo", tags = "成交明细N日大单占比")
@RequestMapping("/api/dashboard/v1/orderclassofproportioninfo")
@RestController
public class OrderClassOfProportionInfoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private OrderClassOfProportionInfoService orderClassOfProportionInfoService;

    /**
     * 获取指定股票N日成交明细
     *
     * @return
     */
    @ApiOperation("获取指定股票N日成交明细")
    @GetMapping("/getTickerQuatInfo/{stockCode}/{startTradeDate}/{endTradeDate}")
    public CommonResult<List<TickerQuatInfo>> getTickerQuatInfo(@PathVariable String stockCode,
                                                                @PathVariable String startTradeDate,
                                                                @PathVariable String endTradeDate) {
        List<TickerQuatInfo> tickerQuatInfos = orderClassOfProportionInfoService.getTickerQuatInfo(stockCode, startTradeDate, endTradeDate);
        return CommonResult.success(tickerQuatInfos, "获取指定股票N日成交明细成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取指定股票N日成交占比和增仓占比
     *
     * @return
     */
    @ApiOperation("获取指定股票N日成交占比和增仓占比")
    @GetMapping("/getOrderClassOfProportionInfo/{stockCode}/{startTradeDate}/{endTradeDate}")
    public CommonResult<OrderClassOfProportionInfo> getOrderClassOfProportionInfo(@PathVariable String stockCode,
                                                                                  @PathVariable String startTradeDate,
                                                                                  @PathVariable String endTradeDate) {
        OrderClassOfProportionInfo orderClassOfProportionInfo = orderClassOfProportionInfoService.getOrderClassOfProportionInfo(stockCode, startTradeDate, endTradeDate);
        return CommonResult.success(orderClassOfProportionInfo, "获取指定股票N日成交占比和增仓占比成功, 处理服务端口：" + serverPort);
    }
}