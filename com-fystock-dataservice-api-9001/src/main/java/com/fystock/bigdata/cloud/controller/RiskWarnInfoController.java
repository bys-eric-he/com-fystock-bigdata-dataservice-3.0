package com.fystock.bigdata.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fystock.bigdata.cloud.handler.SentinelBlockHandler;
import com.fystock.bigdata.cloud.handler.SentinelFallBackHandler;
import com.fystock.bigdata.cloud.model.RiskWarnInfoModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.RiskWarnInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * CUBP风控预警信息查询控制器
 *
 * @author He.Yong
 * @since 2021-04-21 12:21:19
 */
@Slf4j
@RestController
@Validated
@Api(value = "/api/bigdata/v1/risk_warn_info", tags = "CUBP风控预警信息")
@RequestMapping(value = "/api/bigdata/v1/risk_warn_info")
public class RiskWarnInfoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private RiskWarnInfoService riskWarnInfoService;

    /**
     * 获取当前最新预警信息
     *
     * @return
     */
    @ApiOperation("获取当前最新预警信息")
    @SentinelResource(value = "riskWarnInfoService",
            fallbackClass = SentinelFallBackHandler.class, fallback = "riskWarnInfoFallbackHandler",
            blockHandlerClass = SentinelBlockHandler.class, blockHandler = "riskWarnInfoExceptionHandler")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/all_latest_info", method = RequestMethod.GET)
    public CommonResult<List<RiskWarnInfoModel>> getAllLatestInfo() {
        List<RiskWarnInfoModel> riskWarnInfos = riskWarnInfoService.getAllLatestInfo();
        log.info("***查询结果：" + riskWarnInfos);
        return CommonResult.success(riskWarnInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取当前最新指定预警信息
     *
     * @return
     */
    @ApiOperation("获取当前最新指定预警信息")
    @RequestMapping(value = "/all_latest_info/condition", method = RequestMethod.GET)
    public CommonResult<List<RiskWarnInfoModel>> getAllLatestInfo(@RequestParam
                                                                  @NotEmpty(message = "预警项目编号不能为空字符串!")
                                                                  @NotNull(message = "预警项目编号不能为空!") Integer... items) {
        List<RiskWarnInfoModel> riskWarnInfoModels = riskWarnInfoService.getAllLatestInfo(items);

        return CommonResult.success(riskWarnInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取指定导入日期的风险预警信息
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("获取指定导入日期的风险预警信息")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/all_by_import_datetime/{importDateTime}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "importDateTime", value = "导入日期", required = true, dataType = "String", defaultValue = "2021-04-21")
    })
    public CommonResult<List<RiskWarnInfoModel>> getAllByImportDateTime(@PathVariable
                                                                        @NotBlank(message = "导入日期不能为空字符串!")
                                                                        @NotNull(message = "导入日期不能为空!") String importDateTime) {
        List<RiskWarnInfoModel> riskWarnInfos = riskWarnInfoService.getAllByImportDateTime(importDateTime);
        log.info("***查询结果：" + riskWarnInfos);
        return CommonResult.success(riskWarnInfos, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取指定日期的指定风险预警信息
     *
     * @param importDateTime 导入日期
     * @param items          风险预警项目ID
     * @return
     */
    @ApiOperation("获取指定日期的指定风险预警信息")
    @RequestMapping(value = "/all_by_import_datetime/{importDateTime}/condition", method = RequestMethod.GET)
    public CommonResult<List<RiskWarnInfoModel>> getAllByImportDateTime(@PathVariable
                                                                        @NotBlank(message = "导入日期不能为空字符串!")
                                                                        @NotNull(message = "导入日期不能为空!") String importDateTime,
                                                                        @RequestParam
                                                                        @NotEmpty(message = "预警项目编号不能为空字符串!")
                                                                        @NotNull(message = "预警项目编号不能为空!") Integer... items) {
        List<RiskWarnInfoModel> riskWarnInfoModels = riskWarnInfoService.getAllByImportDateTime(importDateTime, items);
        return CommonResult.success(riskWarnInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }
}
