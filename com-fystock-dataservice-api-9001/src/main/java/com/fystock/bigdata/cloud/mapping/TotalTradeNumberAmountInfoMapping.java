package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.TotalTradeNumberAmountInfo;
import com.fystock.bigdata.cloud.model.TotalTradeNumberAmountInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 11:06:14
 */
public class TotalTradeNumberAmountInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param totalTradeNumberAmountInfo
     * @return
     */
    public static TotalTradeNumberAmountInfoModel toModel(TotalTradeNumberAmountInfo totalTradeNumberAmountInfo) {
        if (totalTradeNumberAmountInfo == null) {
            return null;
        }

        TotalTradeNumberAmountInfoModel totalTradeNumberAmountInfoModel = new TotalTradeNumberAmountInfoModel();
        totalTradeNumberAmountInfoModel.setDayCount(totalTradeNumberAmountInfo.getDayCount());
        totalTradeNumberAmountInfoModel.setDayBalance(totalTradeNumberAmountInfo.getDayBalance());
        totalTradeNumberAmountInfoModel.setMoneyType(totalTradeNumberAmountInfo.getMoneyType());
        totalTradeNumberAmountInfoModel.setTradeDate(totalTradeNumberAmountInfo.getTradeDate());
        totalTradeNumberAmountInfoModel.setTradeKind(totalTradeNumberAmountInfo.getTradeKind());
        totalTradeNumberAmountInfoModel.setImportDateTime(totalTradeNumberAmountInfo.getImportDateTime());

        return totalTradeNumberAmountInfoModel;
    }
}
