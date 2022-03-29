package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.entity.clickhouse.SellShortTransactionDataInfo;
import com.fystock.bigdata.cloud.mapper.SellShortTransactionDataInfoMapper;
import com.fystock.bigdata.cloud.mapper.TickerTradeQtyTotalCountInfoMapper;
import com.fystock.bigdata.cloud.model.SellShortCostInfo;
import com.fystock.bigdata.cloud.model.SellShortRatioInfo;
import com.fystock.bigdata.cloud.model.TickerTradeQtyTotalCountInfo;
import com.fystock.bigdata.cloud.service.SellShortTransactionDataInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 即日卖空成交数据
 *
 * @author He.Yong
 * @since 2021-08-04 17:52:25
 */
@Slf4j
@Service
public class SellShortTransactionDataInfoServiceImpl implements SellShortTransactionDataInfoService {

    @Autowired
    private SellShortTransactionDataInfoMapper sellShortTransactionDataInfoMapper;

    @Autowired
    private TickerTradeQtyTotalCountInfoMapper tickerTradeQtyTotalCountInfoMapper;

    /**
     * 持久化即日卖空成交数据
     *
     * @param sellShortTransactionDataInfos
     */
    @Log4FYStock(value = "持久化即日卖空成交数据")
    @Override
    public int insertSellShortTransactionData(List<SellShortTransactionDataInfo> sellShortTransactionDataInfos) {
        sellShortTransactionDataInfoMapper.insertSellShortTransactionDataInfo(sellShortTransactionDataInfos);
        int rows = sellShortTransactionDataInfos.size();
        log.info("-----插入即日卖空成交数据完成! 影响行数: {} 行!", rows);
        return rows;
    }

    /**
     * 获取指定交易日卖空成交数据
     *
     * @param tradeDate
     * @return
     */
    @Log4FYStock(value = "获取指定交易日卖空成交数据")
    @Override
    public List<SellShortTransactionDataInfo> getSellShortTransactionData(String tradeDate) {
        return sellShortTransactionDataInfoMapper.getSellShortTransactionDataList(tradeDate);
    }

    /**
     * 获取指定交易日、指定股票做空成本
     *
     * @param tradeDate
     * @param stockCode
     * @return
     */
    @Log4FYStock(value = "获取指定交易日、指定股票做空成本")
    @Override
    public SellShortCostInfo getSellShortCostInfo(String tradeDate, String stockCode) {
        SellShortTransactionDataInfo sellShortTransactionDataInfo = sellShortTransactionDataInfoMapper.getSellShortTransactionDataInfo(tradeDate, stockCode);

        if (sellShortTransactionDataInfo == null) {
            return null;
        }
        //计算做空成本 做空成本=某只股票一段时间区间做空的金额/某只股票一段时间区间做空的成交量
        BigDecimal cost = sellShortTransactionDataInfo.getSum()
                .divide(BigDecimal.valueOf(sellShortTransactionDataInfo.getCount()), 5, BigDecimal.ROUND_HALF_UP);

        SellShortCostInfo sellShortCostInfo = new SellShortCostInfo();
        sellShortCostInfo.setAssetId(sellShortTransactionDataInfo.getStockCode() + ".HK");
        sellShortCostInfo.setStockCode(sellShortTransactionDataInfo.getStockCode());
        sellShortCostInfo.setStockName(sellShortTransactionDataInfo.getStockName());
        sellShortCostInfo.setCost(cost);
        sellShortCostInfo.setTradeDate(sellShortTransactionDataInfo.getTradeDate());

        return sellShortCostInfo;
    }

    /**
     * 获取指定交易日、指定股票做空占比
     *
     * @param tradeDate
     * @param stockCode
     * @return
     */
    @Log4FYStock(value = "获取指定交易日、指定股票做空占比")
    @Override
    public SellShortRatioInfo getSellShortRatioInfo(String tradeDate, String stockCode) {
        //指定股票指定交易日做空数据
        SellShortTransactionDataInfo sellShortTransactionDataInfo = sellShortTransactionDataInfoMapper.getSellShortTransactionDataInfo(tradeDate, stockCode);
        TickerTradeQtyTotalCountInfo tickerTradeQtyTotalCountInfo = tickerTradeQtyTotalCountInfoMapper.getTickerTradeQtyTotalCountInfo(tradeDate, stockCode);

        if (sellShortTransactionDataInfo == null || tickerTradeQtyTotalCountInfo == null) {
            return null;
        }

        //计算做空占比 做空占比=某只股票一段时间区间做空的股数/该段时间区间总成交股数
        BigDecimal ratio = BigDecimal.valueOf(sellShortTransactionDataInfo.getCount())
                .divide(BigDecimal.valueOf(tickerTradeQtyTotalCountInfo.getTotalCount()), 5, BigDecimal.ROUND_HALF_UP);

        SellShortRatioInfo sellShortRatioInfo = new SellShortRatioInfo();
        sellShortRatioInfo.setStockCode(sellShortTransactionDataInfo.getStockCode());
        sellShortRatioInfo.setStockName(sellShortTransactionDataInfo.getStockName());
        sellShortRatioInfo.setTradeDate(sellShortTransactionDataInfo.getTradeDate());
        sellShortRatioInfo.setAssetId(tickerTradeQtyTotalCountInfo.getAssetId());
        sellShortRatioInfo.setRatio(ratio);
        return sellShortRatioInfo;
    }
}
