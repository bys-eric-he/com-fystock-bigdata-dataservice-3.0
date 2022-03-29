package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.model.TradeInduInfo;
import com.fystock.bigdata.cloud.redis.BigScreenTradeInduInfo;

/**
 * 数据模型层转换
 *
 * @author He.Yong
 * @since 2021-06-18 17:41:19
 */
public class BigScreenTradeInduInfoMapping {
    /**
     * Entity转换Model
     *
     * @param bigScreenTradeInduInfo
     * @return
     */
    public static TradeInduInfo toModel(BigScreenTradeInduInfo bigScreenTradeInduInfo) {
        if (bigScreenTradeInduInfo == null) {
            return null;
        }
        TradeInduInfo tradeInduInfo = new TradeInduInfo();
        tradeInduInfo.setDate(bigScreenTradeInduInfo.getDate());
        tradeInduInfo.setInduCode(bigScreenTradeInduInfo.getInduCode());
        tradeInduInfo.setInduName(bigScreenTradeInduInfo.getInduName());
        tradeInduInfo.setTradeCount(bigScreenTradeInduInfo.getTradeCount());
        tradeInduInfo.setEndTime(bigScreenTradeInduInfo.getEndTime());
        tradeInduInfo.setStartTime(bigScreenTradeInduInfo.getStartTime());
        tradeInduInfo.setVersion(bigScreenTradeInduInfo.getVersion());
        tradeInduInfo.setRank(bigScreenTradeInduInfo.getRank());
        return tradeInduInfo;
    }
}
