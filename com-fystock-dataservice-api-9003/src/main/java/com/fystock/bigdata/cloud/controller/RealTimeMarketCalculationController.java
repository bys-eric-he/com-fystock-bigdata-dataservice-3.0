package com.fystock.bigdata.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fystock.bigdata.cloud.handler.SentinelBlockHandler;
import com.fystock.bigdata.cloud.handler.SentinelFallBackHandler;
import com.fystock.bigdata.cloud.model.RealTimeMarketCalculationModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.RealTimeMarketCalculationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 实时行情计算结果记录服务控制器
 *
 * @author He.Yong
 * @since 2021-03-15 15:28:49
 */
@Validated
@Api(value = "/api/market/v1/realtime-market-calculation", tags = "实时行情计算结果记录服务")
@RestController
@RequestMapping("/api/market/v1/realtime-market-calculation")
public class RealTimeMarketCalculationController {
    @Value("${server.port}")
    private String serverPort;

    @Resource
    @Qualifier("realTimeMarketCalculationService")
    private RealTimeMarketCalculationService realTimeMarketCalculationService;

    /**
     * 获取Redis缓存中所有版块中每只股票最后一次计算结果
     *
     * @return
     */
    @ApiOperation("从缓存中获取所有版块中每只股票最后一次计算结果")
    @RequestMapping(path = "/getMarketDataLatestFromCache", method = RequestMethod.GET)
    public CommonResult<Map<String, List<RealTimeMarketCalculationModel>>> getMarketDataLatestFromCache() {
        Map<String, List<RealTimeMarketCalculationModel>> results = realTimeMarketCalculationService.getMarketDataLatestFromCache();
        return CommonResult.success(results, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 按指定交易日获取所有版块中每只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    @ApiOperation("按指定交易日获取所有版块中每只股票最后一次计算结果")
    @SentinelResource(value = "realTimeMarketCalculationService",
            fallbackClass = SentinelFallBackHandler.class, fallback = "getMarketDataByTradeDateFallbackHandler",
            blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getMarketDataByTradeDateHandler")
    @RequestMapping(path = "/getMarketDataByTradeDate/{tradeDate}", method = RequestMethod.GET)
    public CommonResult<Map<String, List<RealTimeMarketCalculationModel>>> getMarketDataByTradeDate(@PathVariable
                                                                                                    @NotBlank(message = "交易日不能为空字符串!")
                                                                                                    @NotNull(message = "交易日不能为空!") String tradeDate) {
        Map<String, List<RealTimeMarketCalculationModel>> results = realTimeMarketCalculationService.getMarketDataByTradeDate(tradeDate);
        return CommonResult.success(results, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 按指定交易日获取所有版块中每只股票最后一次计算结果-定时器调用
     *
     * @param tradeDate
     * @return
     */
    @ApiOperation("按指定交易日获取所有版块中每只股票最后一次计算结果-定时器调用")
    @SentinelResource(value = "realTimeMarketCalculationService",
            fallbackClass = SentinelFallBackHandler.class, fallback = "getMarketDataByTradeDateFallbackHandler",
            blockHandlerClass = SentinelBlockHandler.class, blockHandler = "getMarketDataByTradeDateHandler")
    @RequestMapping(path = "/getMarketDataByTradeDateForTimer/{tradeDate}", method = RequestMethod.GET)
    public CommonResult<Map<String, List<RealTimeMarketCalculationModel>>> getMarketDataByTradeDateForTimer(@PathVariable
                                                                                                            @NotBlank(message = "交易日不能为空字符串!")
                                                                                                            @NotNull(message = "交易日不能为空!") String tradeDate) {
        Map<String, List<RealTimeMarketCalculationModel>> results = realTimeMarketCalculationService.getMarketDataByTradeDateForTimer(tradeDate);
        return CommonResult.success(results, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 按指定交易日和版块代码获取每只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    @ApiOperation("按指定交易日和版块代码获取每只股票最后一次计算结果")
    @RequestMapping(path = "/getMarketDataByTradeDateAndSectionCode/{tradeDate}/{sectionCode}", method = RequestMethod.GET)
    public CommonResult<Map<String, List<RealTimeMarketCalculationModel>>> getMarketDataByTradeDateAndSectionCode(@PathVariable
                                                                                                                  @NotBlank(message = "交易日不能为空字符串!")
                                                                                                                  @NotNull(message = "交易日不能为空!") String tradeDate,
                                                                                                                  @PathVariable
                                                                                                                  @NotBlank(message = "版块代码不能为空字符串!")
                                                                                                                  @NotNull(message = "版块代码不能为空!") String sectionCode) {
        Map<String, List<RealTimeMarketCalculationModel>> results = realTimeMarketCalculationService.getMarketDataByTradeDateAndSectionCode(tradeDate, sectionCode);
        return CommonResult.success(results, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 按指定交易日和版块代码获取市值最大的Top N只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    @ApiOperation("按指定交易日和版块代码获取市值最大的Top N只股票最后一次计算结果")
    @RequestMapping(path = "/getMarketDataByTradeDateAndSectionCodeAndTopN/{tradeDate}/{sectionCode}/{top}", method = RequestMethod.GET)
    public CommonResult<Map<String, List<RealTimeMarketCalculationModel>>> getMarketDataByTradeDateAndSectionCodeAndTopN(@PathVariable
                                                                                                                         @NotBlank(message = "交易日不能为空字符串!")
                                                                                                                         @NotNull(message = "交易日不能为空!") String tradeDate,
                                                                                                                         @PathVariable
                                                                                                                         @NotBlank(message = "版块代码不能为空字符串!")
                                                                                                                         @NotNull(message = "版块代码不能为空!") String sectionCode,
                                                                                                                         @PathVariable @NotNull(message = "Top值不能为空!") int top) {
        Map<String, List<RealTimeMarketCalculationModel>> results = realTimeMarketCalculationService.getMarketDataByTradeDateAndSectionCodeAndTopN(tradeDate, sectionCode, top);
        return CommonResult.success(results, "查询数据成功, 处理服务端口：" + serverPort);
    }
}
