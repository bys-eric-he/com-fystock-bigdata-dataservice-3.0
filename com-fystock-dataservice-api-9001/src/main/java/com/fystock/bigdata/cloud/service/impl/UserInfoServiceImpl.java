package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.mapper.*;
import com.fystock.bigdata.cloud.entity.mysql.*;
import com.fystock.bigdata.cloud.mapping.*;
import com.fystock.bigdata.cloud.model.*;
import com.fystock.bigdata.cloud.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户相关信息业务处理
 *
 * @author He.Yong
 * @since 2021-03-22 17:46:00
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private AgeRatioUserInfoMapper ageRatioUserInfoMapper;
    private GenderCountRatioUserInfoMapper genderCountRatioUserInfoMapper;
    private CityCountRatioUserInfoMapper cityCountRatioUserInfoMapper;
    private TotalCountUserInfoMapper totalCountUserInfoMapper;
    private TotalCountRatioUserInfoMapper totalCountRatioUserInfoMapper;
    private DayOnlineCountUserInfoMapper dayOnlineCountUserInfoMapper;
    private DayActiveCountUserInfoMapper dayActiveCountUserInfoMapper;
    private UserAssetDistributionMapper userAssetDistributionMapper;
    private UserExceptionalCaseDMapper userExceptionalCaseDMapper;
    private UserPaymentDMapper userPaymentDMapper;

    @Autowired
    public UserInfoServiceImpl(AgeRatioUserInfoMapper ageRatioUserInfoMapper,
                               GenderCountRatioUserInfoMapper genderCountRatioUserInfoMapper,
                               CityCountRatioUserInfoMapper cityCountRatioUserInfoMapper,
                               TotalCountUserInfoMapper totalCountUserInfoMapper,
                               TotalCountRatioUserInfoMapper totalCountRatioUserInfoMapper,
                               DayOnlineCountUserInfoMapper dayOnlineCountUserInfoMapper,
                               DayActiveCountUserInfoMapper dayActiveCountUserInfoMapper,
                               UserAssetDistributionMapper userAssetDistributionMapper,
                               UserExceptionalCaseDMapper userExceptionalCaseDMapper,
                               UserPaymentDMapper userPaymentDMapper) {
        this.ageRatioUserInfoMapper = ageRatioUserInfoMapper;
        this.genderCountRatioUserInfoMapper = genderCountRatioUserInfoMapper;
        this.cityCountRatioUserInfoMapper = cityCountRatioUserInfoMapper;
        this.totalCountUserInfoMapper = totalCountUserInfoMapper;
        this.totalCountRatioUserInfoMapper = totalCountRatioUserInfoMapper;
        this.dayOnlineCountUserInfoMapper = dayOnlineCountUserInfoMapper;
        this.dayActiveCountUserInfoMapper = dayActiveCountUserInfoMapper;
        this.userAssetDistributionMapper = userAssetDistributionMapper;
        this.userExceptionalCaseDMapper = userExceptionalCaseDMapper;
        this.userPaymentDMapper = userPaymentDMapper;
    }

    /**
     * 获取注册用户年龄段占总人数比率
     *
     * @return
     */
    @Log4FYStock(value = "获取注册用户年龄段占总人数比率")
    @Override
    public List<AgeRatioUserInfoModel> findAllAgeRatioUser() {
        List<AgeRatioUserInfo> ageRatioUserInfos = ageRatioUserInfoMapper.findAllAgeRatioUser();
        List<AgeRatioUserInfoModel> results = new ArrayList<>();
        ageRatioUserInfos.forEach(o -> results.add(AgeRatioUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 根据导入日期, 获取注册用户年龄段占总人数比率
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "根据导入日期, 获取注册用户年龄段占总人数比率")
    @Override
    public List<AgeRatioUserInfoModel> findAllAgeRatioUserByImportDateTime(String importDateTime) {
        List<AgeRatioUserInfo> ageRatioUserInfos = ageRatioUserInfoMapper.findAllAgeRatioUserByImportDateTime(importDateTime);
        List<AgeRatioUserInfoModel> results = new ArrayList<>();
        ageRatioUserInfos.forEach(o -> results.add(AgeRatioUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 获取每日注册人数性别比率
     *
     * @return
     */
    @Log4FYStock(value = "获取每日注册人数性别比率")
    @Override
    public List<GenderCountRatioUserInfoModel> findAllGenderCountRatioUserInfo() {
        List<GenderCountRatioUserInfo> genderCountRatioUserInfos = genderCountRatioUserInfoMapper.findAllGenderCountRatioUserInfo();

        List<GenderCountRatioUserInfoModel> results = new ArrayList<>();
        genderCountRatioUserInfos.forEach(o -> results.add(GenderCountRatioUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 根据导入日期获取每日注册人数性别比率
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "根据导入日期获取每日注册人数性别比率")
    @Override
    public List<GenderCountRatioUserInfoModel> findAllGenderCountRatioUserInfoByImportDateTime(String importDateTime) {
        List<GenderCountRatioUserInfo> genderCountRatioUserInfos = genderCountRatioUserInfoMapper.findAllByImportDateTime(importDateTime);

        List<GenderCountRatioUserInfoModel> results = new ArrayList<>();
        genderCountRatioUserInfos.forEach(o -> results.add(GenderCountRatioUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 获取注册人数按城市分布比率
     *
     * @return
     */
    @Log4FYStock(value = "获取注册人数按城市分布比率")
    @Override
    public List<CityCountRatioUserInfoModel> findAllCityCountRatioUser() {
        List<CityCountRatioUserInfo> cityCountRatioUserInfos = cityCountRatioUserInfoMapper.findAllCityCountRatioUser();

        List<CityCountRatioUserInfoModel> results = new ArrayList<>();
        cityCountRatioUserInfos.forEach(o -> results.add(CityCountRatioUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 根据导入日期获取注册人数按城市分布比率
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "根据导入日期获取注册人数按城市分布比率")
    @Override
    public List<CityCountRatioUserInfoModel> findAllCityCountRatioUserByImportDateTime(String importDateTime) {
        List<CityCountRatioUserInfo> cityCountRatioUserInfos = cityCountRatioUserInfoMapper.findAllByImportDateTime(importDateTime);

        List<CityCountRatioUserInfoModel> results = new ArrayList<>();
        cityCountRatioUserInfos.forEach(o -> results.add(CityCountRatioUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 获取各类累计用户数统计
     *
     * @return
     */
    @Log4FYStock(value = "获取各类累计用户数统计")
    @Override
    public List<TotalCountUserInfoModel> findAllTotalCountUserInfo() {

        List<TotalCountUserInfo> totalCountUserInfos = totalCountUserInfoMapper.findAllTotalCountUserInfo();

        List<TotalCountUserInfoModel> results = new ArrayList<>();
        totalCountUserInfos.forEach(o -> results.add(TotalCountUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 根据导入日期获取各类累计用户数统计
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "根据导入日期获取各类累计用户数统计")
    @Override
    public List<TotalCountUserInfoModel> findAllTotalCountUserInfoByImportDateTime(String importDateTime) {
        List<TotalCountUserInfo> totalCountUserInfos = totalCountUserInfoMapper.findAllTotalCountUserInfoByImportDateTime(importDateTime);

        List<TotalCountUserInfoModel> results = new ArrayList<>();
        totalCountUserInfos.forEach(o -> results.add(TotalCountUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 获取各类用户数占注册总人数比率(转化率)
     *
     * @return
     */
    @Log4FYStock(value = "获取各类用户数占注册总人数比率(转化率)")
    @Override
    public List<TotalCountRatioUserInfoModel> findAllTotalCountRatioUserInfo() {

        List<TotalCountRatioUserInfo> totalCountUserInfos = totalCountRatioUserInfoMapper.findAllTotalCountRatioUserInfo();

        List<TotalCountRatioUserInfoModel> results = new ArrayList<>();
        totalCountUserInfos.forEach(o -> results.add(TotalCountRatioUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 根据导入日期获取各类用户数占注册总人数比率(转化率)
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "根据导入日期获取各类用户数占注册总人数比率(转化率)")
    @Override
    public List<TotalCountRatioUserInfoModel> findAllTotalCountRatioUserInfoByImportDateTime(String importDateTime) {
        List<TotalCountRatioUserInfo> totalCountUserInfos = totalCountRatioUserInfoMapper.findAllTotalCountRatioUserInfoByImportDateTime(importDateTime);

        List<TotalCountRatioUserInfoModel> results = new ArrayList<>();
        totalCountUserInfos.forEach(o -> results.add(TotalCountRatioUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 根据指定时间区间获取每天在线用户数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Log4FYStock(value = "根据指定时间区间获取每天在线用户数")
    @Override
    public List<DayOnlineCountUserInfoModel> findDayOnlineCountUserInfo(String startDate, String endDate) {

        List<DayOnlineCountUserInfo> dayOnlineCountUserInfos = dayOnlineCountUserInfoMapper.findDayOnlineCountUserInfo(startDate, endDate);
        List<DayOnlineCountUserInfoModel> results = new ArrayList<>();

        dayOnlineCountUserInfos.forEach(o -> results.add(DayOnlineCountUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 根据指定时间区间获取日活跃用户数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Log4FYStock(value = "根据指定时间区间获取日活跃用户数")
    @Override
    public List<DayActiveCountUserInfoModel> findDayActiveCountUserInfo(String startDate, String endDate) {

        List<DayActiveCountUserInfo> dayActiveCountUserInfos = dayActiveCountUserInfoMapper.findDayActiveCountUserInfo(startDate, endDate);
        List<DayActiveCountUserInfoModel> results = new ArrayList<>();

        dayActiveCountUserInfos.forEach(o -> results.add(DayActiveCountUserInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 获取各类用户资产规模人数
     *
     * @return
     */
    @Log4FYStock(value = "获取各类用户资产规模人数")
    @Override
    public List<UserAssetDistributionModel> findAllUserAssetDistributionInfo() {

        List<UserAssetDistributionInfo> userAssetDistributionInfos = userAssetDistributionMapper.findAllUserAssetDistributionInfo();
        List<UserAssetDistributionModel> results = new ArrayList<>();

        userAssetDistributionInfos.forEach(o -> results.add(UserAssetDistributionMapping.toModel(o)));
        return results;
    }

    /**
     * 根据导入日期获取各类用户资产规模人数
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "根据导入日期获取各类用户资产规模人数")
    @Override
    public List<UserAssetDistributionModel> findAllUserAssetDistributionInfoByImportDateTime(String importDateTime) {

        List<UserAssetDistributionInfo> userAssetDistributionInfos = userAssetDistributionMapper.findAllUserAssetDistributionInfoByImportDateTime(importDateTime);
        List<UserAssetDistributionModel> results = new ArrayList<>();

        userAssetDistributionInfos.forEach(o -> results.add(UserAssetDistributionMapping.toModel(o)));
        return results;
    }

    /**
     * 获取各类异常用户数
     *
     * @return
     */
    @Log4FYStock(value = "获取各类异常用户数")
    @Override
    public List<UserExceptionalCaseDModel> findAllUserExceptionalCaseDInfo() {
        List<UserExceptionalCaseDInfo> userExceptionalCaseDInfos = userExceptionalCaseDMapper.findAllUserExceptionalCaseDInfo();
        List<UserExceptionalCaseDModel> results = new ArrayList<>();

        userExceptionalCaseDInfos.forEach(o -> results.add(UserExceptionalCaseDMapping.toModel(o)));
        return results;
    }

    /**
     * 根据导入日期获取各类异常用户数
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "根据导入日期获取各类异常用户数")
    @Override
    public List<UserExceptionalCaseDModel> findAllUserExceptionalCaseDInfoByImportDateTime(String importDateTime) {
        List<UserExceptionalCaseDInfo> userExceptionalCaseDInfos = userExceptionalCaseDMapper.findAllUserExceptionalCaseDInfoByImportDateTime(importDateTime);
        List<UserExceptionalCaseDModel> results = new ArrayList<>();

        userExceptionalCaseDInfos.forEach(o -> results.add(UserExceptionalCaseDMapping.toModel(o)));
        return results;
    }

    /**
     * 获取付费用户数据信息
     *
     * @return
     */
    @Log4FYStock(value = "获取付费用户数据信息")
    @Override
    public UserPaymentDModel findAllUserPaymentDInfo() {
        UserPaymentDInfo userPaymentDInfo = userPaymentDMapper.findAllUserPaymentDInfo();
        return UserPaymentDMapping.toModel(userPaymentDInfo);
    }

    /**
     * 根据导入日期获取付费用户数据信息
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "根据导入日期获取付费用户数据信息")
    @Override
    public UserPaymentDModel findAllUserPaymentDInfoByImportDateTime(String importDateTime) {
        UserPaymentDInfo userPaymentDInfo = userPaymentDMapper.findAllUserPaymentDInfoByImportDateTime(importDateTime);
        return UserPaymentDMapping.toModel(userPaymentDInfo);
    }
}
