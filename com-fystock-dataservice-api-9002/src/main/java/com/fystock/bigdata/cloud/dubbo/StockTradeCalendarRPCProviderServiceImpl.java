package com.fystock.bigdata.cloud.dubbo;

import com.fystock.bigdata.cloud.model.StockTradeCalendarModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.IStockTradeCalendarRPCProviderService;
import com.fystock.bigdata.cloud.service.StockTradeCalendarService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

/**
 * 交易日历查询 Dubbo对内提供的RPC接口
 *
 * @author He.Yong
 * @since 2021-09-09 10:31:45
 */
@DubboService
public class StockTradeCalendarRPCProviderServiceImpl implements IStockTradeCalendarRPCProviderService {

    @Autowired
    private StockTradeCalendarService stockTradeCalendarService;

    /**
     * 根据自然日期、区域编码,获取交易日
     *
     * @param normalDate
     * @param regionCode
     * @return
     */
    @Override
    public CommonResult<Object> findByNormalDate(LocalDate normalDate, String regionCode) {
        StockTradeCalendarModel stockTradeCalendarModel = stockTradeCalendarService.findByNormalDateAndRegionCode(normalDate, regionCode);

        return CommonResult.success(stockTradeCalendarModel, "Dubbo服务处理!");
    }
}
