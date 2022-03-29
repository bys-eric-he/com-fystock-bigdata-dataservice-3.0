package com.fystock.bigdata.cloud.executor;

import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.ICcassHoldingsInfoRPCProviderService;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import com.xxl.job.core.handler.IJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * 定时任务同步CCASS参与者持仓比例数据
 *
 * @author He.Yong
 * @since 2021-08-13 14:22:15
 */
@Slf4j
@Service("CcassParticipantsHoldingRadioDataTimerExecutor")
public class CcassParticipantsHoldingRadioDataTimerExecutor extends IJobHandler {

    /**
     * Dubbo远程服务调用
     */
    @DubboReference
    private ICcassHoldingsInfoRPCProviderService iCcassHoldingsInfoRPCProviderService;

    @Override
    public void execute() throws Exception {
        try {
            log.info("-----开始执行定时任务同步CCASS参与者持仓比例数据任务-----");

            CommonResult<Object> response = iCcassHoldingsInfoRPCProviderService.startToInsertCcassHoldingsRatioInfo(DateTimeUtil.getYesterdayDate());

            if (ObjectUtils.isNotEmpty(response)) {
                if (response.getCode().equals(200)) {
                    log.info("-----返回数据包：{} ", response.getData());
                } else {
                    log.warn("******CCASS参与者持仓比例数据写入ClickHouse失败! 失败原因：{}******", response);
                }
            } else {
                log.warn("******定时任务接口调用,CCASS参与者持仓比例数据写入ClickHouse响应, 返回为空!******");
            }

            log.info("-----结束执行定时任务同步CCASS参与者持仓比例数据任务-----");
        } catch (Exception exception) {
            log.error("******定时任务同步CCASS参与者持仓比例数据异常 : {}", exception.getMessage());
            throw exception;
        }
    }
}
