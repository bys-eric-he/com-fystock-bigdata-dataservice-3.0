package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.TotalTradeStockNumberInfo;
import com.fystock.bigdata.cloud.model.TotalTradeStockNumberInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 11:16:28
 */
public class TotalTradeStockNumberInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param totalTradeStockNumberInfo
     * @return
     */
    public static TotalTradeStockNumberInfoModel toModel(TotalTradeStockNumberInfo totalTradeStockNumberInfo) {
        if (totalTradeStockNumberInfo == null) {
            return null;
        }

        TotalTradeStockNumberInfoModel totalTradeStockNumberInfoModel = new TotalTradeStockNumberInfoModel();
        totalTradeStockNumberInfoModel.setDayTimes(totalTradeStockNumberInfo.getDayTimes());
        totalTradeStockNumberInfoModel.setExchangeType(totalTradeStockNumberInfo.getExchangeType());
        totalTradeStockNumberInfoModel.setStockCode(totalTradeStockNumberInfo.getStockCode());
        totalTradeStockNumberInfoModel.setStockName(totalTradeStockNumberInfo.getStockName());
        totalTradeStockNumberInfoModel.setTradeDate(totalTradeStockNumberInfo.getTradeDate());
        totalTradeStockNumberInfoModel.setTradeKind(totalTradeStockNumberInfo.getTradeKind());
        totalTradeStockNumberInfoModel.setImportDateTime(totalTradeStockNumberInfo.getImportDateTime());

        return totalTradeStockNumberInfoModel;
    }
}
