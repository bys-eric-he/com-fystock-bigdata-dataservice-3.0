package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.model.TotalTradeNumberAmountAllInfoModel;
import com.fystock.bigdata.cloud.model.TotalTradeNumberAmountInfoModel;
import com.fystock.bigdata.cloud.model.TotalTradeStockNumberInfoModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.StockTradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 大数据离线看板一期,交易数据分析统计结果查询
 *
 * @author He.Yong
 * @since 2021-03-15 15:58:49
 */
@RestController
@Validated
@Api(value = "/api/bigdata/v1/stock_trade", tags = "交易数据分析统计结果")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping(value = "/api/bigdata/v1/stock_trade")
public class StockTradeController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private StockTradeService stockTradeService;

    /**
     * 获取每日交易总笔数、交易总金额数
     *
     * @return
     */
    @ApiOperation("获取每日交易总笔数、交易总金额数")
    @RequestMapping(value = "/total_trade_number_amount", method = RequestMethod.GET)
    public CommonResult<List<TotalTradeNumberAmountInfoModel>> findAllTotalTradeNumberAmountInfo() {
        List<TotalTradeNumberAmountInfoModel> totalTradeNumberAmountInfoModels = stockTradeService.
                findAllTotalTradeNumberAmountInfo();

        return CommonResult.success(totalTradeNumberAmountInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取每日交易总笔数、交易总金额数
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取每日交易总笔数、交易总金额数")
    @RequestMapping(value = "/total_trade_number_amount_import/{importDateTime}", method = RequestMethod.GET)
    public CommonResult<List<TotalTradeNumberAmountInfoModel>> findAllTotalTradeNumberAmountInfoByImportDateTime(
            @PathVariable
            @NotBlank(message = "导入日期不能为空字符串!")
            @NotNull(message = "导入日期不能为空!") String importDateTime) {
        List<TotalTradeNumberAmountInfoModel> totalTradeNumberAmountInfoModels = stockTradeService.
                findAllTotalTradeNumberAmountInfoByImportDateTime(importDateTime);

        return CommonResult.success(totalTradeNumberAmountInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取累计交易总笔数、交易总金额数
     *
     * @return
     */
    @ApiOperation("获取累计交易总笔数、交易总金额数")
    @RequestMapping(value = "/total_trade_number_amount_all", method = RequestMethod.GET)
    public CommonResult<List<TotalTradeNumberAmountAllInfoModel>> findAllTotalTradeNumberAmountAllInfo() {
        List<TotalTradeNumberAmountAllInfoModel> totalTradeNumberAmountAllInfoModels = stockTradeService.
                findAllTotalTradeNumberAmountAllInfo();

        return CommonResult.success(totalTradeNumberAmountAllInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取累计交易总笔数、交易总金额数
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取累计交易总笔数、交易总金额数")
    @RequestMapping(value = "/total_trade_number_amount_all_import/{importDateTime}", method = RequestMethod.GET)
    public CommonResult<List<TotalTradeNumberAmountAllInfoModel>> findAllTotalTradeNumberAmountAllInfoByImportDateTime(
            @PathVariable
            @NotBlank(message = "导入日期不能为空字符串!")
            @NotNull(message = "导入日期不能为空!") String importDateTime) {
        List<TotalTradeNumberAmountAllInfoModel> totalTradeNumberAmountAllInfoModels = stockTradeService.
                findAllTotalTradeNumberAmountAllInfoByImportDateTime(importDateTime);

        return CommonResult.success(totalTradeNumberAmountAllInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param tradeDate
     * @return
     */
    @ApiOperation("根据指定交易日期, 获取股票交易次数 可视化数据")
    @RequestMapping(value = "/total_trade_stock_number_date/{tradeDate}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeDate", value = "交易日期(年-月-日)", required = true, dataType = "String", defaultValue = "2021-01-05")
    })
    public CommonResult<List<TotalTradeStockNumberInfoModel>> findByImportDateTimeAndAndTradeDate(@PathVariable
                                                                                                  @NotBlank(message = "交易日期不能为空字符串!")
                                                                                                  @NotNull(message = "交易日期不能为空!") String tradeDate) {
        List<TotalTradeStockNumberInfoModel> totalTradeStockNumberInfoModels = stockTradeService.
                findAllByTradeDate(tradeDate);

        return CommonResult.success(totalTradeStockNumberInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param tradeDate
     * @return
     */
    @ApiOperation("根据指定交易日期, 获取最后一次统计时间的股票交易次数 可视化数据")
    @RequestMapping(value = "/total_trade_stock_number_date/max/{tradeDate}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeDate", value = "交易日期(年-月-日)", required = true, dataType = "String", defaultValue = "2021-01-05")
    })
    public CommonResult<List<TotalTradeStockNumberInfoModel>> findByImportDateTimeAndAndTradeDateMax(@PathVariable
                                                                                                     @NotBlank(message = "交易日期不能为空字符串!")
                                                                                                     @NotNull(message = "交易日期不能为空!") String tradeDate) {
        List<TotalTradeStockNumberInfoModel> totalTradeStockNumberInfoModels = stockTradeService.
                findAllByTradeDateMax(tradeDate);

        return CommonResult.success(totalTradeStockNumberInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期及指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param importDateTime
     * @param tradeDate
     * @return
     */
    @ApiOperation("根据导入日期及指定交易日期, 获取股票交易次数 可视化数据")
    @RequestMapping(value = "/total_trade_stock_number/{importDateTime}/{tradeDate}", method = RequestMethod.GET)
    public CommonResult<List<TotalTradeStockNumberInfoModel>> findByImportDateTimeAndAndTradeDate(
            @PathVariable
            @NotBlank(message = "导入日期不能为空字符串!")
            @NotNull(message = "导入日期不能为空!") String importDateTime,
            @PathVariable
            @NotBlank(message = "交易日期不能为空字符串!")
            @NotNull(message = "交易日期不能为空!") String tradeDate) {
        List<TotalTradeStockNumberInfoModel> totalTradeStockNumberInfoModels = stockTradeService.
                findAllByImportDateTimeAndTradeDate(importDateTime, tradeDate);

        return CommonResult.success(totalTradeStockNumberInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }
}
