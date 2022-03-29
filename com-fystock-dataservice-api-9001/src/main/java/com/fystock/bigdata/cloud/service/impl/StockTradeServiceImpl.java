package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.mapper.TotalTradeNumberAmountAllInfoMapper;
import com.fystock.bigdata.cloud.mapper.TotalTradeNumberAmountInfoMapper;
import com.fystock.bigdata.cloud.mapper.TotalTradeStockNumberInfoMapper;
import com.fystock.bigdata.cloud.entity.mysql.TotalTradeNumberAmountAllInfo;
import com.fystock.bigdata.cloud.entity.mysql.TotalTradeNumberAmountInfo;
import com.fystock.bigdata.cloud.entity.mysql.TotalTradeStockNumberInfo;
import com.fystock.bigdata.cloud.mapping.TotalTradeNumberAmountAllInfoMapping;
import com.fystock.bigdata.cloud.mapping.TotalTradeNumberAmountInfoMapping;
import com.fystock.bigdata.cloud.mapping.TotalTradeStockNumberInfoMapping;
import com.fystock.bigdata.cloud.model.TotalTradeNumberAmountAllInfoModel;
import com.fystock.bigdata.cloud.model.TotalTradeNumberAmountInfoModel;
import com.fystock.bigdata.cloud.model.TotalTradeStockNumberInfoModel;
import com.fystock.bigdata.cloud.service.StockTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 交易数据分析统计结果
 *
 * @author He.Yong
 * @since 2021-04-26 19:39:25
 */
@Service
public class StockTradeServiceImpl implements StockTradeService {

    private TotalTradeNumberAmountInfoMapper totalTradeNumberAmountInfoMapper;
    private TotalTradeNumberAmountAllInfoMapper totalTradeNumberAmountAllInfoMapper;
    private TotalTradeStockNumberInfoMapper totalTradeStockNumberInfoMapper;

    @Autowired
    public StockTradeServiceImpl(TotalTradeNumberAmountInfoMapper totalTradeNumberAmountInfoMapper,
                                 TotalTradeNumberAmountAllInfoMapper totalTradeNumberAmountAllInfoMapper,
                                 TotalTradeStockNumberInfoMapper totalTradeStockNumberInfoMapper) {
        this.totalTradeNumberAmountInfoMapper = totalTradeNumberAmountInfoMapper;
        this.totalTradeNumberAmountAllInfoMapper = totalTradeNumberAmountAllInfoMapper;
        this.totalTradeStockNumberInfoMapper = totalTradeStockNumberInfoMapper;
    }

    /**
     * 获取每日交易总笔数、交易总金额数
     *
     * @return
     */
    @Log4FYStock(value = "获取每日交易总笔数、交易总金额数")
    @Override
    public List<TotalTradeNumberAmountInfoModel> findAllTotalTradeNumberAmountInfo() {

        List<TotalTradeNumberAmountInfo> totalTradeNumberAmountInfos = totalTradeNumberAmountInfoMapper.findAllTotalTradeNumberAmountInfo();
        List<TotalTradeNumberAmountInfoModel> results = new ArrayList<>();

        totalTradeNumberAmountInfos.forEach(o -> results.add(TotalTradeNumberAmountInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 根据导入日期获取每日交易总笔数、交易总金额数
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "根据导入日期获取每日交易总笔数、交易总金额数")
    @Override
    public List<TotalTradeNumberAmountInfoModel> findAllTotalTradeNumberAmountInfoByImportDateTime(String importDateTime) {
        List<TotalTradeNumberAmountInfo> totalTradeNumberAmountInfos = totalTradeNumberAmountInfoMapper.findAllTotalTradeNumberAmountInfoByImportDateTime(importDateTime);

        List<TotalTradeNumberAmountInfoModel> results = new ArrayList<>();
        totalTradeNumberAmountInfos.forEach(o -> results.add(TotalTradeNumberAmountInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 获取累计交易总笔数、交易总金额数
     *
     * @return
     */
    @Log4FYStock(value = "获取累计交易总笔数、交易总金额数")
    @Override
    public List<TotalTradeNumberAmountAllInfoModel> findAllTotalTradeNumberAmountAllInfo() {

        List<TotalTradeNumberAmountAllInfo> totalTradeNumberAmountAllInfos = totalTradeNumberAmountAllInfoMapper.findAllTotalTradeNumberAmountAllInfo();
        List<TotalTradeNumberAmountAllInfoModel> results = new ArrayList<>();

        totalTradeNumberAmountAllInfos.forEach(o -> results.add(TotalTradeNumberAmountAllInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 根据导入日期获取累计交易总笔数、交易总金额数
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "根据导入日期获取累计交易总笔数、交易总金额数")
    @Override
    public List<TotalTradeNumberAmountAllInfoModel> findAllTotalTradeNumberAmountAllInfoByImportDateTime(String importDateTime) {
        List<TotalTradeNumberAmountAllInfo> totalTradeNumberAmountAllInfos = totalTradeNumberAmountAllInfoMapper.findAllTotalTradeNumberAmountAllInfoByImportDateTime(importDateTime);

        List<TotalTradeNumberAmountAllInfoModel> results = new ArrayList<>();
        totalTradeNumberAmountAllInfos.forEach(o -> results.add(TotalTradeNumberAmountAllInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 根据指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param tradeDate
     * @return
     */
    @Override
    public List<TotalTradeStockNumberInfoModel> findAllByTradeDateMax(String tradeDate) {


        List<TotalTradeStockNumberInfo> totalTradeStockNumberInfos = totalTradeStockNumberInfoMapper.findAllByTradeDateMax(tradeDate);
        List<TotalTradeStockNumberInfoModel> results = new ArrayList<>();


        Optional<TotalTradeStockNumberInfo> totalTradeStockNumberInfoOptional = totalTradeStockNumberInfos.stream().max(Comparator.comparingInt(TotalTradeStockNumberInfo::getDayTimes));

        totalTradeStockNumberInfoOptional.ifPresent(totalTradeStockNumberInfo -> results.add(TotalTradeStockNumberInfoMapping.toModel(totalTradeStockNumberInfo)));

        return results;
    }

    /**
     * 根据指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param tradeDate
     * @return
     */
    @Override
    public List<TotalTradeStockNumberInfoModel> findAllByTradeDate(String tradeDate) {

        List<TotalTradeStockNumberInfo> totalTradeStockNumberInfos = totalTradeStockNumberInfoMapper.findAllByTradeDate(tradeDate);
        List<TotalTradeStockNumberInfoModel> results = new ArrayList<>();


        Optional<TotalTradeStockNumberInfo> totalTradeStockNumberInfoOptional = totalTradeStockNumberInfos.stream().max(Comparator.comparingInt(TotalTradeStockNumberInfo::getDayTimes));

        totalTradeStockNumberInfoOptional.ifPresent(totalTradeStockNumberInfo -> results.add(TotalTradeStockNumberInfoMapping.toModel(totalTradeStockNumberInfo)));

        return results;
    }

    /**
     * 根据导入日期及指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param importDateTime
     * @param tradeDate
     * @return
     */
    @Override
    public List<TotalTradeStockNumberInfoModel> findAllByImportDateTimeAndTradeDate(String importDateTime, String tradeDate) {
        List<TotalTradeStockNumberInfo> totalTradeStockNumberInfos = totalTradeStockNumberInfoMapper.findAllByImportDateTimeAndTradeDate(importDateTime, tradeDate);

        List<TotalTradeStockNumberInfoModel> results = new ArrayList<>();
        totalTradeStockNumberInfos.forEach(o -> results.add(TotalTradeStockNumberInfoMapping.toModel(o)));

        return results;
    }
}
