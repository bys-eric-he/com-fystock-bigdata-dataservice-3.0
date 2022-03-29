package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 股票基础详情信息
 *
 * @author He.Yong
 * @since 2021-04-22 09:19:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetInfo {
    /**
     * 资产ID
     */
    private String assetId;
    /**
     * 股票代码
     */
    private String stkCode;
    /**
     * (香港/A股)映射股票代码
     */
    private String mapStkCode;
    /**
     * 市场
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
     * 板块代码
     */
    private Integer boardCode;

    /**
     * 每手股数
     */
    private Integer lotSize;

    /**
     * 涨跌幅限制
     */
    private BigDecimal changeLimit;

    /**
     * 上市日期
     */
    private Date listingDate;

    /**
     * 退市日期
     */
    private Date delistDate;

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
     * 记录股票是否可以交易的种类
     */
    private Boolean isInvest;

    /**
     * 是否支持模拟交易
     */
    private Boolean isSimuInvest;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 记录外部系统时间
     */
    private Date extTime;

    /**
     * 记录状态
     */
    private Boolean isStatus;

    /**
     * 是否确认
     */
    private Boolean isConfirm;

    /**
     * 是否修改
     */
    private Boolean isRevise;

    /**
     * 上市发行价
     */
    private BigDecimal issuePrice;

    /**
     * 美股交易市场
     */
    private String exchange;

    /**
     * 0,可覆盖,1,不允许覆盖
     */
    private Boolean stkNameIsCover;

    private String spellingShort;

    private String spreadTableCode;
}
