package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.TotalTradeNumberAmountAllInfo;
import com.fystock.bigdata.cloud.model.TotalTradeNumberAmountAllInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 10:48:27
 */
public class TotalTradeNumberAmountAllInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param totalTradeNumberAmountAllInfo
     * @return
     */
    public static TotalTradeNumberAmountAllInfoModel toModel(TotalTradeNumberAmountAllInfo totalTradeNumberAmountAllInfo) {
        if (totalTradeNumberAmountAllInfo == null) {
            return null;
        }

        TotalTradeNumberAmountAllInfoModel totalTradeNumberAmountAllInfoModel = new TotalTradeNumberAmountAllInfoModel();
        totalTradeNumberAmountAllInfoModel.setCount(totalTradeNumberAmountAllInfo.getCount());
        totalTradeNumberAmountAllInfoModel.setBalance(totalTradeNumberAmountAllInfo.getBalance());
        totalTradeNumberAmountAllInfoModel.setMoneyType(totalTradeNumberAmountAllInfo.getMoneyType());
        totalTradeNumberAmountAllInfoModel.setTradeKind(totalTradeNumberAmountAllInfo.getTradeKind());
        totalTradeNumberAmountAllInfoModel.setImportDateTime(totalTradeNumberAmountAllInfo.getImportDateTime());

        return totalTradeNumberAmountAllInfoModel;
    }
}
