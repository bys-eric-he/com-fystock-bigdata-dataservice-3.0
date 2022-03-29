package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 大盘云图实时行情计算缓存中WebSocket实时计算结果
 *
 * @author He.Yong
 * @since 2021-05-20 10:58:29
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "realTimeMarketCalculationCacheModel", description = "大盘云图实时行情缓存中WebSocket实时计算结果")
public class RealTimeMarketCalculationCacheModel implements Serializable {
    private static final long serialVersionUID = 8655851615225363655L;

    /**
     * 版块数据
     */
    @ApiModelProperty(value = "版块数据", name = "data")
    private MarketSection data;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "数据类型", name = "type")
    private String type;

    /**
     * 行业板块及前10排名股票
     */
    @Data
    @NoArgsConstructor
    @ApiModel(value = "marketSection", description = "股票行情数据")
    public static class MarketSection {
        /**
         * 版块名称
         */
        @ApiModelProperty(value = "版块名称", name = "induName")
        private String induName;

        /**
         * 版块代码
         */
        @ApiModelProperty(value = "版块代码", name = "induCode")
        private String induCode;

        /**
         * 时间戳
         */
        @ApiModelProperty(value = "时间戳", name = "timestamp")
        private String timestamp;

        /**
         * 行情数据集合
         */
        @ApiModelProperty(value = "行情数据集合", name = "tops")
        private List<MarketData> tops;


        @Data
        @NoArgsConstructor
        @ApiModel(value = "marketData", description = "股票行情数据")
        public static class MarketData {
            /**
             * 股票代码
             */
            @ApiModelProperty(value = "股票代码", name = "stockCode")
            private String stockCode;

            /**
             * 股票名称
             */
            @ApiModelProperty(value = "股票名称", name = "stockName")
            private String stockName;

            /**
             * 总市值
             */
            @ApiModelProperty(value = "总市值", name = "totalMarketValue")
            private String totalMarketValue;

            /**
             * 交易日
             */
            @ApiModelProperty(value = "交易日", name = "tradeDate")
            private String tradeDate;

            /**
             * 涨跌幅
             */
            @ApiModelProperty(value = "涨跌幅", name = "upAndDown")
            private String upAndDown;
        }
    }
}
