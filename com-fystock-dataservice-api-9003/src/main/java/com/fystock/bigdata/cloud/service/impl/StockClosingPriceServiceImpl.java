package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.entity.hbase.StockClosingPrice;
import com.fystock.bigdata.cloud.mapper.StockClosingPriceMapper;
import com.fystock.bigdata.cloud.mapping.StockClosingPriceMapping;
import com.fystock.bigdata.cloud.model.StockClosingPriceModel;
import com.fystock.bigdata.cloud.model.StockRelativeAbsoluteIndexModel;
import com.fystock.bigdata.cloud.service.StockClosingPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 股票收盘价
 *
 * @author He.Yong
 * @since 2021-08-06 11:31:25
 */
@Slf4j
@Service("stockClosingPriceService")
public class StockClosingPriceServiceImpl implements StockClosingPriceService {

    @Autowired
    private StockClosingPriceMapper stockClosingPriceMapper;

    /**
     * 获取指定股票指定交易日收盘价
     *
     * @param stockCode
     * @param tradeDate
     * @return
     */
    @Log4FYStock(value = "获取指定股票指定交易日收盘价")
    @Override
    public StockClosingPriceModel getStockClosingPriceInfo(String stockCode, String tradeDate) {
        StockClosingPrice stockClosingPrice = stockClosingPriceMapper.getStockClosingPriceInfo(stockCode, tradeDate);
        return StockClosingPriceMapping.toModel(stockClosingPrice);
    }

    /**
     * 获取指定两只股票同一交易日相对强势指数
     *
     * @param stockCodeA
     * @param stockCodeB
     * @param tradeDate
     * @return
     */
    @Log4FYStock(value = "获取指定两只股票同一交易日相对强势指数")
    @Override
    public StockRelativeAbsoluteIndexModel getStockRelativeAbsoluteIndex(String stockCodeA, String stockCodeB, String tradeDate) {

        StockClosingPrice stockClosingPriceA = stockClosingPriceMapper.getStockClosingPriceInfo(stockCodeA, tradeDate);
        StockClosingPrice stockClosingPriceB = stockClosingPriceMapper.getStockClosingPriceInfo(stockCodeB, tradeDate);

        if (stockClosingPriceA == null || stockClosingPriceB == null) {
            return null;
        }

        StockRelativeAbsoluteIndexModel stockRelativeAbsoluteIndexModel = new StockRelativeAbsoluteIndexModel();

        stockRelativeAbsoluteIndexModel.setTradeDate(tradeDate);
        stockRelativeAbsoluteIndexModel.setStockCodeA(stockClosingPriceA.getAssetId());
        stockRelativeAbsoluteIndexModel.setStockPriceA(stockClosingPriceA.getClosingPrice());
        stockRelativeAbsoluteIndexModel.setStockCodeB(stockClosingPriceB.getAssetId());
        stockRelativeAbsoluteIndexModel.setStockPriceB(stockClosingPriceB.getClosingPrice());

        //相对强势指数=指定频率股票1（或指数1）的收盘价/指定频率股票2（或指数2）的收盘价
        stockRelativeAbsoluteIndexModel.setIndex(stockClosingPriceA.getClosingPrice().divide(stockClosingPriceB.getClosingPrice(), 5, BigDecimal.ROUND_HALF_UP));

        return stockRelativeAbsoluteIndexModel;
    }
}
