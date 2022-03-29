package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.hbase.RealTimeMarketCalculation;
import com.fystock.bigdata.cloud.model.RealTimeMarketCalculationCacheModel;
import com.fystock.bigdata.cloud.model.RealTimeMarketCalculationModel;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 实体层、模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 15:28:49
 */
@Slf4j
public class RealTimeMarketCalculationMapping {
    /**
     * Entity 转换成 Model
     *
     * @param realTimeMarketCalculation
     * @return
     */
    public static RealTimeMarketCalculationModel toModel(RealTimeMarketCalculation realTimeMarketCalculation) {
        if (realTimeMarketCalculation == null) {
            return null;
        }

        RealTimeMarketCalculationModel realTimeMarketCalculationModel = new RealTimeMarketCalculationModel();

        realTimeMarketCalculationModel.setEventTime(realTimeMarketCalculation.getEventTime());
        realTimeMarketCalculationModel.setFlowMarketValue(realTimeMarketCalculation.getFlowMarketValue());
        realTimeMarketCalculationModel.setExchangeType(realTimeMarketCalculation.getExchangeType());
        realTimeMarketCalculationModel.setPrice(realTimeMarketCalculation.getPrice());
        realTimeMarketCalculationModel.setYesPrice(realTimeMarketCalculation.getYesPrice());
        realTimeMarketCalculationModel.setTotalMarketValue(realTimeMarketCalculation.getTotalMarketValue());
        realTimeMarketCalculationModel.setInduCodeA(realTimeMarketCalculation.getInduCodeA());
        realTimeMarketCalculationModel.setInduNameA(realTimeMarketCalculation.getInduNameA());
        realTimeMarketCalculationModel.setStatus(realTimeMarketCalculation.getStatus());
        realTimeMarketCalculationModel.setStockCode(realTimeMarketCalculation.getStockCode());
        realTimeMarketCalculationModel.setStockName(realTimeMarketCalculation.getStockName());
        realTimeMarketCalculationModel.setInduCodeB(realTimeMarketCalculation.getInduCodeB());
        realTimeMarketCalculationModel.setInduNameB(realTimeMarketCalculation.getInduNameB());
        realTimeMarketCalculationModel.setTradeDate(realTimeMarketCalculation.getTradeDate());
        realTimeMarketCalculationModel.setUpAndDown(realTimeMarketCalculation.getUpAndDown());

        return realTimeMarketCalculationModel;
    }

    /**
     * 缓存Model 转换成 实体Model
     *
     * @param realTimeMarketCalculationCacheModel
     * @return
     */
    public static List<RealTimeMarketCalculationModel> toModels(RealTimeMarketCalculationCacheModel realTimeMarketCalculationCacheModel) {
        if (realTimeMarketCalculationCacheModel == null) {
            return null;
        }

        List<RealTimeMarketCalculationModel> results = new ArrayList<>();
        try {
            String format = "HH:mm:ss";
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String timeNowStr = sdf.format(now);

            Date nowTime = new SimpleDateFormat(format).parse(timeNowStr);
            Date startTime = new SimpleDateFormat(format).parse("08:50:00");
            /*Date endTime = new SimpleDateFormat(format).parse("09:00:00");*/

            for (RealTimeMarketCalculationCacheModel.MarketSection.MarketData marketData : realTimeMarketCalculationCacheModel.getData().getTops()) {
                RealTimeMarketCalculationModel realTimeMarketCalculationModel = new RealTimeMarketCalculationModel();

                realTimeMarketCalculationModel.setEventTime(realTimeMarketCalculationCacheModel.getData().getTimestamp());
                realTimeMarketCalculationModel.setInduCodeA(realTimeMarketCalculationCacheModel.getData().getInduCode());
                realTimeMarketCalculationModel.setInduNameA(realTimeMarketCalculationCacheModel.getData().getInduName());
                realTimeMarketCalculationModel.setTotalMarketValue(marketData.getTotalMarketValue());
                realTimeMarketCalculationModel.setStockCode(marketData.getStockCode());
                realTimeMarketCalculationModel.setStockName(marketData.getStockName());
                realTimeMarketCalculationModel.setTradeDate(marketData.getTradeDate());

                long timestamp = Long.parseLong(realTimeMarketCalculationCacheModel.getData().getTimestamp());
                //如果是在08:50以后,且Redis缓存中的数据还是昨天的数据,这时候就清掉涨跌幅返回0.00,模拟清盘的操作
                if (DateTimeUtil.isAfterDate(nowTime, startTime) && !DateTimeUtil.isToday(timestamp)) {
                    realTimeMarketCalculationModel.setUpAndDown("0.00");
                } else {
                    realTimeMarketCalculationModel.setUpAndDown(marketData.getUpAndDown());
                }
                results.add(realTimeMarketCalculationModel);
            }
        } catch (ParseException exception) {
            log.error("--->实时行情数据,缓存Model 转换成 实体Model异常!", exception);
        }

        return results;
    }
}
