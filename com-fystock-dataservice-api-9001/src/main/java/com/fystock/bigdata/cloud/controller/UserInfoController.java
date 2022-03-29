package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.model.*;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 大数据离线看板一期,注册用户数据分析统计结果查询
 *
 * @author He.Yong
 * @since 2021-03-15 19:24:19
 */
@Slf4j
@Validated
@RestController
@Api(value = "/api/bigdata/v1/user_info", tags = "注册用户数据分析统计结果")
@RequestMapping(value = "/api/bigdata/v1/user_info")
public class UserInfoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取注册用户年龄段占总人数比率
     *
     * @return
     */
    @ApiOperation("获取注册用户年龄段占总人数比率")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/age_ratio_user", method = RequestMethod.GET)
    public CommonResult<List<AgeRatioUserInfoModel>> findAllAgeRatioUserByImportDateTime() {
        List<AgeRatioUserInfoModel> ageRatioUserInfoModels = userInfoService.findAllAgeRatioUser();
        log.info("***查询结果：" + ageRatioUserInfoModels);
        return CommonResult.success(ageRatioUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取注册用户年龄段占总人数比率
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取注册用户年龄段占总人数比率")
    @PreAuthorize("hasAnyAuthority('FYSTOCK')")
    @RequestMapping(value = "/age_ratio_user/{importDateTime}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "importDateTime", value = "导入日期", required = true, dataType = "String", defaultValue = "2021-04-21")
    })
    public CommonResult<List<AgeRatioUserInfoModel>> findAllAgeRatioUserByImportDateTime(@PathVariable
                                                                                         @NotBlank(message = "导入日期不能为空字符串!")
                                                                                         @NotNull(message = "导入日期不能为空!") String importDateTime) {
        List<AgeRatioUserInfoModel> ageRatioUserInfoModels = userInfoService.findAllAgeRatioUserByImportDateTime(importDateTime);
        log.info("***查询结果：" + ageRatioUserInfoModels);
        return CommonResult.success(ageRatioUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取每日注册人数性别比率
     *
     * @return
     */
    @ApiOperation("获取每日注册人数性别比率")
    @RequestMapping(value = "/gender_count_ratio_user", method = RequestMethod.GET)
    public CommonResult<List<GenderCountRatioUserInfoModel>> findAllGenderCountRatioUserInfo() {
        List<GenderCountRatioUserInfoModel> genderCountRatioUserInfoModels = userInfoService.findAllGenderCountRatioUserInfo();

        return CommonResult.success(genderCountRatioUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取每日注册人数性别比率
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取每日注册人数性别比率")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "importDateTime", value = "导入日期", required = true, dataType = "String", defaultValue = "2021-04-21")
    })
    @RequestMapping(value = "/gender_count_ratio_user/{importDateTime}", method = RequestMethod.GET)
    public CommonResult<List<GenderCountRatioUserInfoModel>> findAllGenderCountRatioUserInfoByImportDateTime(
            @PathVariable
            @NotBlank(message = "导入日期不能为空字符串!")
            @NotNull(message = "导入日期不能为空!") String importDateTime) {
        List<GenderCountRatioUserInfoModel> genderCountRatioUserInfoModels = userInfoService.
                findAllGenderCountRatioUserInfoByImportDateTime(importDateTime);

        return CommonResult.success(genderCountRatioUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取注册人数按城市分布比率
     *
     * @return
     */
    @ApiOperation("获取注册人数按城市分布比率")
    @RequestMapping(value = "/city_count_ratio_user", method = RequestMethod.GET)
    public CommonResult<List<CityCountRatioUserInfoModel>> findAllCityCountRatioUser() {
        List<CityCountRatioUserInfoModel> cityCountRatioUserInfoModels = userInfoService.findAllCityCountRatioUser();

        return CommonResult.success(cityCountRatioUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取注册人数按城市分布比率
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取注册人数按城市分布比率")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "importDateTime", value = "导入日期", required = true, dataType = "String", defaultValue = "2021-04-21")
    })
    @RequestMapping(value = "/city_count_ratio_user/{importDateTime}", method = RequestMethod.GET)
    public CommonResult<List<CityCountRatioUserInfoModel>> findAllCityCountRatioUserByImportDateTime(
            @PathVariable
            @NotBlank(message = "导入日期不能为空字符串!")
            @NotNull(message = "导入日期不能为空!") String importDateTime) {
        List<CityCountRatioUserInfoModel> cityCountRatioUserInfoModels = userInfoService.
                findAllCityCountRatioUserByImportDateTime(importDateTime);

        return CommonResult.success(cityCountRatioUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取各类累计用户数统计
     *
     * @return
     */
    @ApiOperation("获取各类累计用户数统计")
    @RequestMapping(value = "/total_count_user", method = RequestMethod.GET)
    public CommonResult<List<TotalCountUserInfoModel>> findAllTotalCountUserInfo() {
        List<TotalCountUserInfoModel> totalCountUserInfoModels = userInfoService.
                findAllTotalCountUserInfo();

        return CommonResult.success(totalCountUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取各类累计用户数统计
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取各类累计用户数统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "importDateTime", value = "导入日期", required = true, dataType = "String", defaultValue = "2021-04-21")
    })
    @RequestMapping(value = "/total_count_user/{importDateTime}", method = RequestMethod.GET)
    public CommonResult<List<TotalCountUserInfoModel>> findAllTotalCountUserInfoByImportDateTime(
            @PathVariable
            @NotBlank(message = "导入日期不能为空字符串!")
            @NotNull(message = "导入日期不能为空!") String importDateTime) {
        List<TotalCountUserInfoModel> totalCountUserInfoModels = userInfoService.
                findAllTotalCountUserInfoByImportDateTime(importDateTime);

        return CommonResult.success(totalCountUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取各类用户数占注册总人数比率(转化率)
     *
     * @return
     */
    @ApiOperation("获取各类用户数占注册总人数比率(转化率)")
    @RequestMapping(value = "/total_count_ratio_user", method = RequestMethod.GET)
    public CommonResult<List<TotalCountRatioUserInfoModel>> findAllTotalCountRatioUserInfo() {
        List<TotalCountRatioUserInfoModel> totalCountRatioUserInfoModels = userInfoService.
                findAllTotalCountRatioUserInfo();

        return CommonResult.success(totalCountRatioUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取各类用户数占注册总人数比率(转化率)
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取各类用户数占注册总人数比率(转化率)")
    @RequestMapping(value = "/total_count_ratio_user/{importDateTime}", method = RequestMethod.GET)
    public CommonResult<List<TotalCountRatioUserInfoModel>> findAllTotalCountRatioUserInfoByImportDateTime(
            @PathVariable
            @NotBlank(message = "导入日期不能为空字符串!")
            @NotNull(message = "导入日期不能为空!") String importDateTime) {
        List<TotalCountRatioUserInfoModel> totalCountRatioUserInfoModels = userInfoService.
                findAllTotalCountRatioUserInfoByImportDateTime(importDateTime);

        return CommonResult.success(totalCountRatioUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据指定时间区间获取每天在线用户数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @ApiOperation("根据指定时间区间获取每天在线用户数")
    @RequestMapping(value = "/day_online_count_user_list/{startDate}/{endDate}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始日期(格式：2020-05-01)", required = true, dataType = "String", defaultValue = "2020-05-01"),
            @ApiImplicitParam(name = "endDate", value = "截止日期(格式：2020-05-31)", required = true, dataType = "String", defaultValue = "2020-05-31")
    })
    public CommonResult<List<DayOnlineCountUserInfoModel>> findDayOnlineCountUserInfo(@PathVariable
                                                                                      @NotBlank(message = "开始日期不能为空字符串!")
                                                                                      @NotNull(message = "开始日期不能为空!") String startDate,
                                                                                      @PathVariable
                                                                                      @NotBlank(message = "截止日期不能为空字符串!")
                                                                                      @NotNull(message = "截止日期不能为空!") String endDate) {
        List<DayOnlineCountUserInfoModel> dayOnlineCountUserInfoModels = userInfoService.findDayOnlineCountUserInfo(startDate, endDate);
        return CommonResult.success(dayOnlineCountUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据指定时间区间获取日活跃用户数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @ApiOperation("根据指定时间区间获取日活跃用户数")
    @RequestMapping(value = "/day_active_count_user", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始日期(格式：2020-12-01)", required = true, dataType = "String", defaultValue = "2020-12-01"),
            @ApiImplicitParam(name = "endDate", value = "截止日期(格式：2020-12-31)", required = true, dataType = "String", defaultValue = "2020-12-31")
    })
    public CommonResult<List<DayActiveCountUserInfoModel>> findDayActiveCountUserInfo(@NotBlank(message = "开始日期不能为空字符串!")
                                                                                      @NotNull(message = "开始日期不能为空!") String startDate,
                                                                                      @NotBlank(message = "截止日期不能为空字符串!")
                                                                                      @NotNull(message = "截止日期不能为空!") String endDate) {
        List<DayActiveCountUserInfoModel> dayActiveCountUserInfoModels = userInfoService.findDayActiveCountUserInfo(startDate, endDate);
        return CommonResult.success(dayActiveCountUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取各类用户资产规模人数
     *
     * @return
     */
    @ApiOperation("获取各类用户资产规模人数")
    @RequestMapping(value = "/user_asset_distribution", method = RequestMethod.GET)
    public CommonResult<List<UserAssetDistributionModel>> findAllUserAssetDistributionInfo() {
        List<UserAssetDistributionModel> userAssetDistributionModels = userInfoService.findAllUserAssetDistributionInfo();
        return CommonResult.success(userAssetDistributionModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取各类用户资产规模人数
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取各类用户资产规模人数")
    @RequestMapping(value = "/user_asset_distribution/{importDateTime}", method = RequestMethod.GET)
    public CommonResult<List<UserAssetDistributionModel>> findAllUserAssetDistributionInfoByImportDateTime(@PathVariable
                                                                                                           @NotBlank(message = "导入日期不能为空字符串!")
                                                                                                           @NotNull(message = "导入日期不能为空!") String importDateTime) {
        List<UserAssetDistributionModel> userAssetDistributionModels = userInfoService.findAllUserAssetDistributionInfoByImportDateTime(importDateTime);
        return CommonResult.success(userAssetDistributionModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取各类异常用户数
     *
     * @return
     */
    @ApiOperation("获取各类异常用户数")
    @RequestMapping(value = "/user_exceptional_case_d", method = RequestMethod.GET)
    public CommonResult<List<UserExceptionalCaseDModel>> findAllUserExceptionalCaseDInfo() {
        List<UserExceptionalCaseDModel> userExceptionalCaseDModels = userInfoService.findAllUserExceptionalCaseDInfo();
        return CommonResult.success(userExceptionalCaseDModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取各类异常用户数
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取各类异常用户数")
    @RequestMapping(value = "/user_exceptional_case_d/{importDateTime}", method = RequestMethod.GET)
    public CommonResult<List<UserExceptionalCaseDModel>> findAllUserExceptionalCaseDInfoByImportDateTime(@PathVariable
                                                                                                         @NotBlank(message = "导入日期不能为空字符串!")
                                                                                                         @NotNull(message = "导入日期不能为空!") String importDateTime) {
        List<UserExceptionalCaseDModel> userExceptionalCaseDModels = userInfoService.findAllUserExceptionalCaseDInfoByImportDateTime(importDateTime);
        return CommonResult.success(userExceptionalCaseDModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取付费用户数据信息
     *
     * @return
     */
    @ApiOperation("获取付费用户数据信息")
    @RequestMapping(value = "/user_payment_d", method = RequestMethod.GET)
    public CommonResult<UserPaymentDModel> findAllUserPaymentDInfo() {
        UserPaymentDModel userPaymentDModel = userInfoService.findAllUserPaymentDInfo();
        return CommonResult.success(userPaymentDModel, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取付费用户数据信息
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取付费用户数据信息")
    @RequestMapping(value = "/user_payment_d/{importDateTime}", method = RequestMethod.GET)
    public CommonResult<UserPaymentDModel> findAllUserPaymentDInfoByImportDateTime(@PathVariable
                                                                                   @NotBlank(message = "导入日期不能为空字符串!")
                                                                                   @NotNull(message = "导入日期不能为空!") String importDateTime) {
        UserPaymentDModel userPaymentDModel = userInfoService.findAllUserPaymentDInfoByImportDateTime(importDateTime);
        return CommonResult.success(userPaymentDModel, "查询数据成功, 处理服务端口：" + serverPort);
    }
}