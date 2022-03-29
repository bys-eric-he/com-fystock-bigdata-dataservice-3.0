package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.hbase.StockClosingPrice;
import com.fystock.bigdata.cloud.model.StockClosingPriceModel;

/**
 * 实体层、模型层数据转换
 *
 * @author He.Yong
 * @since 2021-08-06 14:33:21
 */
public class StockClosingPriceMapping {

    /**
     * 转换Model
     *
     * @param stockClosingPrice
     * @return
     */
    public static StockClosingPriceModel toModel(StockClosingPrice stockClosingPrice) {
        if (stockClosingPrice == null) {
            return null;
        }

        StockClosingPriceModel stockClosingPriceModel = new StockClosingPriceModel();

        stockClosingPriceModel.setAssetId(stockClosingPrice.getAssetId());
        stockClosingPriceModel.setTradeDate(stockClosingPrice.getTradeDate());
        stockClosingPriceModel.setClosingPrice(stockClosingPrice.getClosingPrice());

        return stockClosingPriceModel;
    }
}
