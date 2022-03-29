package com.fystock.bigdata.cloud.dubbo;

import com.fystock.bigdata.cloud.entity.clickhouse.CcassHoldingsInfo;
import com.fystock.bigdata.cloud.model.CcassHoldingsRatioInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.CcassHoldingsInfoService;
import com.fystock.bigdata.cloud.service.ICcassHoldingsInfoRPCProviderService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * CCASS持仓信息 Dubbo对内提供的RPC接口
 *
 * @author He.Yong
 * @since 2021-09-08 17:05:15
 */
@DubboService
public class CcassHoldingsInfoRPCProviderServiceImpl implements ICcassHoldingsInfoRPCProviderService {

    @Autowired
    private CcassHoldingsInfoService ccassHoldingsInfoService;

    /**
     * 开始持久化CCASS持仓数据比例
     *
     * @param updateDate
     * @return
     */
    @Override
    public CommonResult<Object> startToInsertCcassHoldingsRatioInfo(String updateDate) {
        List<CcassHoldingsRatioInfo> ccassHoldingsRatioInfos = ccassHoldingsInfoService.getCcassHoldingsRatioInfoList(updateDate);
        if (ObjectUtils.isEmpty(ccassHoldingsRatioInfos)) {
            return CommonResult.success("UpdateDate日期为: " + updateDate + " 的持仓比例数据为空! 终止持久化操作!", "Dubbo服务处理!");
        }
        int rows = ccassHoldingsInfoService.insertCcassHoldingsRatioInfos(ccassHoldingsRatioInfos);

        return CommonResult.success("持久化CCASS持仓数据比例完成! 影响行数 : " + rows + " 行!", "Dubbo服务处理!");
    }

    /**
     * 插入CCASS持仓数据
     *
     * @param ccassHoldingsInfos
     * @return
     */
    @Override
    public CommonResult<Object> insertCcassHoldingsInfo(List<CcassHoldingsInfo> ccassHoldingsInfos) {
        int rows = ccassHoldingsInfoService.insertCcassHoldingsInfos(ccassHoldingsInfos);
        return CommonResult.success(String.format("插入CCASS持仓数据完成! 影响行数: %s 行!", rows), "Dubbo服务处理!");
    }
}
