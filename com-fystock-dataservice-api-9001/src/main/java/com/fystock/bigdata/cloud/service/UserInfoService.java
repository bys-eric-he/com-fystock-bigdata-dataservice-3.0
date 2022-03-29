package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.*;

import java.util.List;

/**
 * 用户相关信息业务处理
 *
 * @author He.Yong
 * @since 2021-03-22 17:46:00
 */
public interface UserInfoService {
    /**
     * 获取注册用户年龄段占总人数比率
     *
     * @return
     */
    List<AgeRatioUserInfoModel> findAllAgeRatioUser();

    /**
     * 根据导入日期, 获取注册用户年龄段占总人数比率
     *
     * @param importDateTime
     * @return
     */
    List<AgeRatioUserInfoModel> findAllAgeRatioUserByImportDateTime(String importDateTime);

    /**
     * 获取每日注册人数性别比率
     *
     * @return
     */
    List<GenderCountRatioUserInfoModel> findAllGenderCountRatioUserInfo();

    /**
     * 根据导入日期, 获取每日注册人数性别比率
     *
     * @param importDateTime
     * @return
     */
    List<GenderCountRatioUserInfoModel> findAllGenderCountRatioUserInfoByImportDateTime(String importDateTime);

    /**
     * 获取注册人数按城市分布比率
     *
     * @return
     */
    List<CityCountRatioUserInfoModel> findAllCityCountRatioUser();

    /**
     * 根据导入日期, 获取注册人数按城市分布比率
     *
     * @param importDateTime
     * @return
     */
    List<CityCountRatioUserInfoModel> findAllCityCountRatioUserByImportDateTime(String importDateTime);

    /**
     * 获取各类累计用户数统计
     *
     * @return
     */
    List<TotalCountUserInfoModel> findAllTotalCountUserInfo();

    /**
     * 根据导入日期, 获取各类累计用户数统计
     *
     * @param importDateTime
     * @return
     */
    List<TotalCountUserInfoModel> findAllTotalCountUserInfoByImportDateTime(String importDateTime);

    /**
     * 获取各类用户数占注册总人数比率(转化率)
     *
     * @return
     */
    List<TotalCountRatioUserInfoModel> findAllTotalCountRatioUserInfo();

    /**
     * 根据导入日期, 获取各类用户数占注册总人数比率(转化率)
     *
     * @param importDateTime
     * @return
     */
    List<TotalCountRatioUserInfoModel> findAllTotalCountRatioUserInfoByImportDateTime(String importDateTime);


    /**
     * 根据指定时间区间获取每天在线用户数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<DayOnlineCountUserInfoModel> findDayOnlineCountUserInfo(String startDate, String endDate);

    /**
     * 根据指定时间区间获取日活跃用户数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<DayActiveCountUserInfoModel> findDayActiveCountUserInfo(String startDate, String endDate);

    /**
     * 获取各类用户资产规模人数
     *
     * @return
     */
    List<UserAssetDistributionModel> findAllUserAssetDistributionInfo();

    /**
     * 根据导入日期获取各类用户资产规模人数
     *
     * @param importDateTime
     * @return
     */
    List<UserAssetDistributionModel> findAllUserAssetDistributionInfoByImportDateTime(String importDateTime);

    /**
     * 获取各类异常用户数
     *
     * @return
     */
    List<UserExceptionalCaseDModel> findAllUserExceptionalCaseDInfo();

    /**
     * 根据导入日期获取各类异常用户数
     *
     * @param importDateTime
     * @return
     */
    List<UserExceptionalCaseDModel> findAllUserExceptionalCaseDInfoByImportDateTime(String importDateTime);

    /**
     * 获取付费用户数据信息
     *
     * @return
     */
    UserPaymentDModel findAllUserPaymentDInfo();

    /**
     * 根据导入日期获取付费用户数据信息
     *
     * @param importDateTime
     * @return
     */
    UserPaymentDModel findAllUserPaymentDInfoByImportDateTime(String importDateTime);
}
