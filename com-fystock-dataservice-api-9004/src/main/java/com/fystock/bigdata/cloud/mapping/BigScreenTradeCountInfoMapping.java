package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.model.TradeCountInfo;
import com.fystock.bigdata.cloud.redis.BigScreenTradeCountInfo;

/**
 * 数据模型层转换
 *
 * @author He.Yong
 * @since 2021-06-18 17:15:19
 */
public class BigScreenTradeCountInfoMapping {
    /**
     * Entity转换Model
     *
     * @param bigScreenTradeCountInfo
     * @return
     */
    public static TradeCountInfo toModel(BigScreenTradeCountInfo bigScreenTradeCountInfo) {
        if (bigScreenTradeCountInfo == null) {
            return null;
        }

        TradeCountInfo tradeCountInfo = new TradeCountInfo();
        tradeCountInfo.setDate(bigScreenTradeCountInfo.getDate());
        tradeCountInfo.setTradeCount(bigScreenTradeCountInfo.getTradeCount());
        tradeCountInfo.setStartTime(bigScreenTradeCountInfo.getStartTime());
        tradeCountInfo.setEndTime(bigScreenTradeCountInfo.getEndTime());
        tradeCountInfo.setTime(bigScreenTradeCountInfo.getTime());
        return tradeCountInfo;
    }
}
