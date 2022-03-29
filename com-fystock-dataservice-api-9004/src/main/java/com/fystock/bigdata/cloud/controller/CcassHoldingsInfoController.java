package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.entity.clickhouse.CcassHoldingsInfo;
import com.fystock.bigdata.cloud.model.CcassHoldingsChangeInfo;
import com.fystock.bigdata.cloud.model.CcassHoldingsRatioInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.CcassHoldingsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CCASS持仓数据
 *
 * @author He.Yong
 * @since 2021-08-11 16:16:24
 */
@Api(value = "/api/dashboard/v1/ccassholdingsinfo", tags = "CCASS持仓数据")
@RequestMapping("/api/dashboard/v1/ccassholdingsinfo")
@RestController
public class CcassHoldingsInfoController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private CcassHoldingsInfoService ccassHoldingsInfoService;

    /**
     * 获取指定交易日CCASS持仓数据
     *
     * @param updateDate
     * @return
     */
    @ApiOperation("获取指定交易日CCASS持仓数据")
    @GetMapping("/getCcassHoldingsInfoList/{updateDate}")
    public CommonResult<List<CcassHoldingsInfo>> getCcassHoldingsInfoList(@PathVariable String updateDate) {
        List<CcassHoldingsInfo> ccassHoldingsInfos =
                ccassHoldingsInfoService.getCcassHoldingsInfoList(updateDate);

        return CommonResult.success(ccassHoldingsInfos, "获取日期为 " + updateDate + " 的CCASS持仓数据成功,处理服务端口：" + serverPort);
    }

    /**
     * 获取指定交易日CCASS持仓数据比例
     *
     * @param updateDate
     * @return
     */
    @ApiOperation("获取指定交易日CCASS持仓数据比例")
    @GetMapping("/getCcassHoldingsRatioInfoList/{updateDate}")
    public CommonResult<List<CcassHoldingsRatioInfo>> getCcassHoldingsRatioInfoList(@PathVariable String updateDate) {
        List<CcassHoldingsRatioInfo> ccassHoldingsRatioInfos =
                ccassHoldingsInfoService.getCcassHoldingsRatioInfoList(updateDate);

        return CommonResult.success(ccassHoldingsRatioInfos, "获取日期为 " + updateDate + " 的CCASS持仓数据比例成功,处理服务端口：" + serverPort);
    }

    /**
     * 获取指定日期CCASS持仓数据和某个基准时刻的持股变动
     *
     * @param holdDateA
     * @param holdDateB
     * @param stockCode
     * @return
     */
    @ApiOperation("获取指定日期CCASS持仓数据和某个基准时刻的持股变动")
    @GetMapping("/getCcassHoldingsChangeInfo/{holdDateA}/{holdDateB}/{stockCode}")
    public CommonResult<CcassHoldingsChangeInfo> getCcassHoldingsChangeInfo(@PathVariable String holdDateA,
                                                                            @PathVariable String holdDateB,
                                                                            @PathVariable String stockCode) {
        CcassHoldingsChangeInfo ccassHoldingsChangeInfo = ccassHoldingsInfoService.getCcassHoldingsChangeInfo(holdDateA, holdDateB, stockCode);
        return CommonResult.success(ccassHoldingsChangeInfo, "获取日期 【" + holdDateA + " 】和基准时刻【 " + holdDateB + "】 的持股变动数据成功,处理服务端口：" + serverPort);

    }

    /**
     * 开始持久化CCASS持仓数据比例
     *
     * @return
     */
    @ApiOperation("开始持久化CCASS持仓比例数据")
    @GetMapping("/startToInsertCcassHoldingsRatioInfo/{updateDate}")
    public CommonResult<Object> startToInsertCcassHoldingsRatioInfo(@PathVariable String updateDate) {
        List<CcassHoldingsRatioInfo> ccassHoldingsRatioInfos = ccassHoldingsInfoService.getCcassHoldingsRatioInfoList(updateDate);
        if (ObjectUtils.isEmpty(ccassHoldingsRatioInfos)) {
            return CommonResult.success("UpdateDate日期为: " + updateDate + " 的持仓比例数据为空, 终止持久化操作!", "处理服务端口：" + serverPort);
        }
        int rows = ccassHoldingsInfoService.insertCcassHoldingsRatioInfos(ccassHoldingsRatioInfos);

        return CommonResult.success("持久化CCASS持仓数据比例完成, 影响行数 : " + rows + " 行!", "处理服务端口：" + serverPort);
    }

    /**
     * 插入CCASS持仓数据
     *
     * @param ccassHoldingsInfos
     * @return
     */
    @ApiOperation("插入CCASS持仓数据")
    @PostMapping("/insertCcassHoldingsInfo")
    public CommonResult<Object> insertCcassHoldingsInfo(@RequestBody List<CcassHoldingsInfo> ccassHoldingsInfos) {
        int rows = ccassHoldingsInfoService.insertCcassHoldingsInfos(ccassHoldingsInfos);
        return CommonResult.success(String.format("插入CCASS持仓数据完成! 影响行数: %s 行!", rows), "处理服务端口：" + serverPort);
    }
}
