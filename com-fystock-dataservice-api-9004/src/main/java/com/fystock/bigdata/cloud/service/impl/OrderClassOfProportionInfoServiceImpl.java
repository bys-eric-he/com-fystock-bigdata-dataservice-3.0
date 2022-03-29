package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.mapper.OrderClassOfProportionMapper;
import com.fystock.bigdata.cloud.model.OrderClassOfProportionInfo;
import com.fystock.bigdata.cloud.model.OrderSizeOfTotalSumAndCountInfo;
import com.fystock.bigdata.cloud.model.TickerQuatInfo;
import com.fystock.bigdata.cloud.model.UpOfTotalSumAndCountInfo;
import com.fystock.bigdata.cloud.service.OrderClassOfProportionInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * N日内的增仓占比
 *
 * @author He.Yong
 * @since 2021-07-29 18:32:25
 */
@Slf4j
@Service
public class OrderClassOfProportionInfoServiceImpl implements OrderClassOfProportionInfoService {

    @Autowired
    private OrderClassOfProportionMapper orderClassOfProportionMapper;

    /**
     * 计算指定股票N日内的增仓占比
     *
     * @param stockCode
     * @param startTradeDate
     * @param endTradeDate
     * @return
     */
    @Override
    public List<TickerQuatInfo> getTickerQuatInfo(String stockCode, String startTradeDate, String endTradeDate) {
        return orderClassOfProportionMapper.getTickerQuatInfo(stockCode, startTradeDate, endTradeDate);
    }

    /**
     * 计算指定股票N日内的增仓占比
     *
     * @param stockCode
     * @param startTradeDate
     * @param endTradeDate
     * @return
     */
    @Override
    public OrderClassOfProportionInfo getOrderClassOfProportionInfo(String stockCode,
                                                                    String startTradeDate,
                                                                    String endTradeDate) {
        List<OrderSizeOfTotalSumAndCountInfo> orderSizeOfTotalSumAndCountInfos =
                orderClassOfProportionMapper.getOrderSizeOfTotalSumAndCountInfo(stockCode, startTradeDate, endTradeDate);

        List<UpOfTotalSumAndCountInfo> upOfTotalSumAndCountInfos =
                orderClassOfProportionMapper.getUpOfTotalSumAndCountInfo(stockCode, startTradeDate, endTradeDate);

        OrderClassOfProportionInfo orderClassOfProportionInfo = new OrderClassOfProportionInfo();
        orderClassOfProportionInfo.setAssetId(stockCode);
        orderClassOfProportionInfo.setStartTradeDate(startTradeDate);
        orderClassOfProportionInfo.setEndTradeDate(endTradeDate);
        orderClassOfProportionInfo.setSmallTotalSum(new BigDecimal("0.0"));
        orderClassOfProportionInfo.setMasukuraProportion(new BigDecimal("0.0"));
        orderClassOfProportionInfo.setBuySum(new BigDecimal("0.0"));
        orderClassOfProportionInfo.setSaleSum(new BigDecimal("0.0"));
        orderClassOfProportionInfo.setMiddleTotalSum(new BigDecimal("0.0"));
        orderClassOfProportionInfo.setOtherSum(new BigDecimal("0.0"));
        orderClassOfProportionInfo.setBigTotalSum(new BigDecimal("0.0"));

        //将大中小单金额填充到POJO
        for (OrderSizeOfTotalSumAndCountInfo orderSizeOfTotalSumAndCountInfo : orderSizeOfTotalSumAndCountInfos) {
            switch (orderSizeOfTotalSumAndCountInfo.getOrderSize()) {
                case 0: {
                    orderClassOfProportionInfo.setMiddleTotalSum(orderSizeOfTotalSumAndCountInfo.getTotalSum());
                    orderClassOfProportionInfo.setMiddleTotalCount(orderSizeOfTotalSumAndCountInfo.getTotalCount());
                    break;
                }
                case 1: {
                    orderClassOfProportionInfo.setBigTotalSum(orderSizeOfTotalSumAndCountInfo.getTotalSum());
                    orderClassOfProportionInfo.setBigTotalCount(orderSizeOfTotalSumAndCountInfo.getTotalCount());
                    break;
                }
                case -1: {
                    orderClassOfProportionInfo.setSmallTotalSum(orderSizeOfTotalSumAndCountInfo.getTotalSum());
                    orderClassOfProportionInfo.setSmallTotalCount(orderSizeOfTotalSumAndCountInfo.getTotalCount());
                    break;
                }
            }
        }
        //将主买、主卖、非主买非主卖金额填充到POJO
        for (UpOfTotalSumAndCountInfo upOfTotalSumAndCountInfo : upOfTotalSumAndCountInfos) {
            switch (upOfTotalSumAndCountInfo.getUpValue()) {
                case 0: {
                    orderClassOfProportionInfo.setSaleSum(upOfTotalSumAndCountInfo.getTotalSum());
                    break;
                }
                case 1: {
                    orderClassOfProportionInfo.setBuySum(upOfTotalSumAndCountInfo.getTotalSum());
                    break;
                }
                case -1: {
                    orderClassOfProportionInfo.setOtherSum(upOfTotalSumAndCountInfo.getTotalSum());
                    break;
                }
            }
        }

        //大中小单总笔数
        Integer totalCount = orderClassOfProportionInfo.getBigTotalCount()
                + orderClassOfProportionInfo.getMiddleTotalCount()
                + orderClassOfProportionInfo.getSmallTotalCount();
        //计算成交占比
        orderClassOfProportionInfo.setTradeProportion(BigDecimal.valueOf(orderClassOfProportionInfo.getBigTotalCount() / totalCount));

        //大中小单总金额
        BigDecimal totalSum = orderClassOfProportionInfo.getBigTotalSum().add(
                orderClassOfProportionInfo.getMiddleTotalSum()).add(
                orderClassOfProportionInfo.getSmallTotalSum());

        //净流入资金
        BigDecimal netInflow = orderClassOfProportionInfo.getBuySum().subtract(orderClassOfProportionInfo.getSaleSum());
        //计算增仓占比
        orderClassOfProportionInfo.setMasukuraProportion(netInflow.divide(totalSum, 5, BigDecimal.ROUND_HALF_UP));

        return orderClassOfProportionInfo;
    }
}
