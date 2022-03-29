package com.fystock.bigdata.cloud.executor;

import com.fystock.bigdata.cloud.entity.mysql.InduDataInfo;
import com.fystock.bigdata.cloud.entity.sqlserver.InduSourceInfo;
import com.fystock.bigdata.cloud.service.InduInfoService;
import com.xxl.job.core.handler.IJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时同步行业、股票分类信息
 *
 * @author He.Yong
 * @since 2021-06-03 11:24:25
 */
@Slf4j
@Component("InduInfoDataTimerExecutor")
public class InduInfoDataTimerExecutor extends IJobHandler {

    @Autowired
    private InduInfoService induInfoService;

    @Override
    public void execute() throws Exception {
        try {
            log.info("----------开始执行定时同步行业、股票分类信息任务--------------");
            List<InduDataInfo> induInfoDataEntityList = induInfoService.getAllInduInfoDataList();
            List<InduSourceInfo> induInfoSourceEntityList = induInfoService.getAllInduInfoSourceList();

            induInfoService.saveOrUpdateInduInfo(induInfoDataEntityList, induInfoSourceEntityList);
            log.info("----------结束执行定时同步行业、股票分类信息任务--------------");
        } catch (Exception exception) {
            log.error("-----执行定时同步行业、股票分类信息任务发生异常! 异常信息: ", exception);
            throw exception;
        }
    }
}
