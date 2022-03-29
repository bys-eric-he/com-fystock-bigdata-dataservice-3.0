package com.fystock.bigdata.cloud.entity.clickhouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * +
 * 交易数据大宽表
 *
 * @author He.Yong
 * @since 2021-06-21 11:19:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeWideInfo {
    /**
     * 交易:成交价格
     */
    private String businessPrice;
    /**
     * 交易:委托单号
     */
    private String entrustNo;
    /**
     * 交易:成交编号
     */
    private String businessNo;
    /**
     * 交易:币种 0人民币 1美圆 2港币
     */
    private String moneyType;
    /**
     * 交易:委托价格
     */
    private String entrustPrice;
    /**
     * 交易:是否暗盘，1是0否
     */
    private String isGrey;
    /**
     * 交易:买卖方向[1-买入 2-卖出]
     */
    private String bsFlag;
    /**
     * 交易:委托数量
     */
    private String entrustQty;
    /**
     * 交易:委托状态 7-部成 8-全成
     */
    private String entrustStatus;
    /**
     * 交易:成交数量
     */
    private String businessQty;
    /**
     * 交易:成交时间
     */
    private String businessTime;
    /**
     * 交易:交易账号
     */
    private String tradeAccount;
    /**
     * 交易:成交日期
     */
    private String businessDay;
    /**
     * 交易:成交金额
     */
    private String businessBalance;
    /**
     * 股票:市场
     */
    private String mktCode;
    /**
     * 股票:板块代码
     */
    private String boardCode;
    /**
     * 股票:股票代码
     */
    private String stkCode;
    /**
     * 股票:资产ID
     */
    private String assetId;
    /**
     * 股票:拼音简称
     */
    private String spellingAbbr;
    /**
     * 股票:股票市场[K-港股 P-美股]
     */
    private String exchangeType;
    /**
     * 股票:记录外部系统时间
     */
    private String extTime;
    /**
     * 股票:每手股数
     */
    private String lotSize;
    /**
     * 股票:记录创建版本号
     */
    private String addVersion;
    /**
     * 股票:行业分类体系标识
     */
    private String induSysMark;
    /**
     * 股票:最新版本号
     */
    private String version;
    /**
     * 股票:币种代码
     */
    private String ccyType;
    /**
     * 股票:记录股票是否可以交易的种类
     */
    private String isInvest;
    /**
     * 股票:证券类别
     */
    private String secType;
    /**
     * 股票:是否确认
     */
    private String isConfirm;
    /**
     * 股票:行业二级名称
     */
    private String induNameB;
    /**
     * 股票:行业一级名称
     */
    private String induNameA;
    /**
     * 股票:公司ID
     */
    private String corpId;
    /**
     * 股票:上市日期
     */
    private String listingDate;
    /**
     * 股票:繁体全称
     */
    private String stkTraditionalNameLong;
    /**
     * 股票:是否支持模拟交易
     */
    private String isSimuInvest;
    /**
     * 股票:记录状态
     */
    private String isStatus;
    /**
     * 股票:行业二级代码
     */
    private String induCodeB;
    /**
     * 股票:行业一级代码
     */
    private String induCodeA;
    /**
     * 股票:是否修改
     */
    private String isRevise;
    /**
     * 股票:股票全称
     */
    private String stkNameLong;
    /**
     * 股票:行业分类体系名
     */
    private String induSysName;
    /**
     * 股票:拼音全称
     */
    private String spelling;
    /**
     * 股票:0,可覆盖 1,不允许覆盖
     */
    private String stkNameIscover;
    /**
     * 股票:是否龙头股 0 表示非龙头股,1 表示龙头股
     */
    private String isFaucet;
    /**
     * 股票:细分类别
     */
    private String secStype;
    /**
     * 股票:股票简称
     */
    private String stkName;
    /**
     * 用户:证件号码
     */
    private String idNo;
    /**
     * 用户:是否开通美股市场[0=否 1=是]
     */
    private String isOpenUSAStockMarket;
    /**
     * 用户:机构标志[0-个人 1、4-机构 2-自营]
     */
    private String organFlag;
    /**
     * 用户:是否开通港股市场[0=否 1=是]
     */
    private String isOpenHKStockMarket;
    /**
     * 用户:银行账户类型[0-香港账户 1-非香港帐号]
     */
    private String bankType;
    /**
     * 用户:银行名称
     */
    private String bankAccountName;
    /**
     * 用户:银行ID
     */
    private String bankId;
    /**
     * 用户:银行卡号
     */
    private String bankNo;
    /**
     * 用户:犇犇号
     */
    private String userId;
    /**
     * 用户:账户类型[0=现金账户 M=保证金账户]
     */
    private String fundAccountType;
    /**
     * 用户:英文名称
     */
    private String engName;
    /**
     * 是否通过身份验证[0=否 1=是]
     */
    private String isPassIdentityAuthentication;
    /**
     * 用户:手机号码
     */
    private String phoneNumber;
    /**
     * 用户:生日
     */
    private String birthday;
    /**
     * 用戶:开户时间
     */
    private String openAccountTime;
    /**
     * 用户:记录状态[0-无效 1-有效]
     */
    private String recordStatus;
    /**
     * 用户:资金账号
     */
    private String fundAccount;
    /**
     * 用户:就业情况类型[0-未知 1=受雇 2=自营/个体户 3=退休 4=学生 5=其他 6 =农林牧副渔 7=待业 8=自由职业者 9-投资者 10-家庭主妇]
     */
    private String professionCode;
    /**
     * 用户:客户中文名
     */
    private String clientName;
    /**
     * 用户:风险承受程度:[1=低风险 2=中风险 3=高风险]
     */
    private String acceptRisk;
    /**
     * 用户:渠道ID
     */
    private String sourceChannelId;
    /**
     * 用户:性别
     */
    private String sex;
    /**
     * 用户:繁体简称
     */
    private String stkTraditionalName;
    /**
     * 用户:北向交易资料收集声明([0=否 1=是])
     */
    private String northTrade;
    /**
     * 用户:客户状态[0-正常 1-冻结 2-挂失 3-销户 D-休眠 E-不合格 F-锁定]
     */
    private String clientStatus;
    /**
     * 用户:是否允许衍生品交易[0=否 1=是]
     */
    private String isAllowDerivativesTransaction;
    /**
     * 用户:注销日期
     */
    private String cancelDate;
    /**
     * 可扩展代码
     */
    private String spreadTableCode;
    /**
     * 时间:binlog滚动时间戳
     */
    private String es;
    /**
     * 时间:binlog滚动时间
     */
    private String tradeTime;
}
