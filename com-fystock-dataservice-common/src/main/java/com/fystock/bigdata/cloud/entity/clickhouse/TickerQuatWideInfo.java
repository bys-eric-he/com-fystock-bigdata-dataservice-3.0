package com.fystock.bigdata.cloud.entity.clickhouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 成交明细大宽表
 *
 * @author He.Yong
 * @since 2021-07-29 15:26:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TickerQuatWideInfo {
    /**
     * 股票ID
     */
    private String assetId;

    /**
     * 股票交易类型
     */
    private Integer tickerType;

    /**
     * 股票交易价格
     */
    private Float tickerTradePrice;

    /**
     * 股票交易量
     */
    private Integer tickerTradeQty;

    /**
     * 股票行情标识
     */
    private Integer tickerId;

    /**
     * 标识上一笔信息的序列号
     */
    private Integer seqNum;

    /**
     * 股票交易方向： 1-主买 2-主卖
     */
    private Integer tickerIsUp;

    /**
     * 股票交易时间戳
     */
    private Integer tickerTradeTimestamp;

    /**
     * 股票交易日期
     */
    private String tickerTradeDate;

    /**
     * 股票交易时间
     */
    private String tickerTradeTime;

    /**
     * 股票代码
     */
    private String stkCode;

    /**
     * 映射股票代码(香港/A股)
     */
    private String mapStkCode;

    /**
     * 市场代码
     */
    private String mktCode;

    /**
     * 证券类别
     */
    private Integer secType;

    /**
     * 细分类别
     */
    private Integer secStype;

    /**
     * 公司ID
     */
    private Integer corpId;

    /**
     * 股票简称
     */
    private String stkName;

    /**
     * 股票全称
     */
    private String stkNameLong;

    /**
     * 繁体简称
     */
    private String stkTraditionalName;

    /**
     * 繁体全称
     */
    private String stkTraditionalNameLong;

    /**
     * 拼音简称
     */
    private String spellingAbbr;

    /**
     * 拼音全称
     */
    private String spelling;

    /**
     * 英文名称
     */
    private String engName;

    /**
     * 股票板块
     */
    private Integer boardCode;

    /**
     * 每手股数
     */
    private Integer lotSize;

    /**
     * 涨跌幅限制
     */
    private String changeLimit;

    /**
     * 上市日期
     */
    private String listingDate;

    /**
     * 退市日期
     */
    private String delistDate;

    /**
     * 币种代码
     */
    private String ccyType;

    /**
     * 最新版本号
     */
    private Integer version;

    /**
     * 记录创建版本号
     */
    private Integer addVersion;

    /**
     * 记录股票是否可交易
     */
    private Integer isInvest;

    /**
     * 是否支持模拟交易
     */
    private Integer isSimuInvest;

    /**
     * 记录状态
     */
    private Integer isStatus;

    /**
     * 是否确认
     */
    private Integer isConfirm;

    /**
     * 是否修改
     */
    private Integer isRevise;

    /**
     * 上市发行价
     */
    private String issuePrice;

    /**
     * 美股交易市场
     */
    private String exchange;

    /**
     * 0,可覆盖
     * 1,不允许覆盖
     */
    private String stkNameIscover;

    private String spellingShort;

    private String spreadTableCode;

    /**
     * 行业分类信息：行业ID
     */
    private String induId;

    /**
     * 行业分类信息：外键链接股票id
     */
    private String corpCode;

    /**
     * 行业分类信息：变动日期
     */
    private String changeDate;

    /**
     * 行业分类信息：行业分类体系标识
     */
    private String induSysMark;

    /**
     * 行业分类信息：行业分类体系名
     */
    private String induSysName;

    /**
     * 行业分类信息：行业一级代码
     */
    private String induCodeA;

    /**
     * 行业分类信息：行业一级名称
     */
    private String induNameA;

    /**
     * 行业分类信息：行业二级代码
     */
    private String induCodeB;

    /**
     * 行业分类信息：行业二级名称
     */
    private String induNameB;

    /**
     * 行业分类信息：行业三级代码
     */
    private String induCodeC;

    /**
     * 行业分类信息：行业三级名称
     */
    private String induNameC;

    /**
     * 行业分类信息：行业四级代码
     */
    private String induCodeD;

    /**
     * 行业分类信息：行业四级名称
     */
    private String induNameD;

    /**
     * 行业分类信息状态
     */
    private String induIsStatus;

    /**
     * 行业分类信息
     */
    private String induIsFaucet;

    /**
     * 总股本
     */
    private Integer totalCapital;

    /**
     * 流通股本
     */
    private String flowCapital;

    /**
     * 股票维表信息更新日期
     */
    private String updateDate;
}
