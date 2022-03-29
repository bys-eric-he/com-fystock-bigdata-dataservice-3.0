package com.fystock.bigdata.cloud.executor;

import com.fystock.bigdata.cloud.entity.mysql.CapitalInfo;
import com.fystock.bigdata.cloud.service.CapitalInfoService;
import com.xxl.job.core.handler.IJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务同步股本信息
 *
 * @author He.Yong
 * @since 2021-06-24 15:14:12
 */
@Slf4j
@Service("CapitalInfoDataTimerExecutor")
public class CapitalInfoDataTimerExecutor extends IJobHandler {

    @Autowired
    private CapitalInfoService capitalInfoService;

    @Override
    public void execute() throws Exception {
        try {
            log.info("-----开始执行定时同步CapitalInfo股本信息任务-----");
            List<CapitalInfo> capitalInfos = capitalInfoService.getAllCapitalInfoSourceList();
            if (capitalInfos.size() <= 0) {
                log.info("-----没有新的CapitalInfo股本信息,结束同步操作-----");
                return;
            }

            log.info("-----开始清除旧的CapitalInfo股本信息数据-----");
            int number = capitalInfoService.truncateCapitalInfoTable();
            log.info("-----TRUNCATE TABLE方式,清除旧的CapitalInfo股本信息： {} 条!-----", number);
            log.info("-----开始批量插入新的CapitalInfo股本信息数据-----");
            int rows = capitalInfoService.insertCapitalInfoBatch(capitalInfos);
            log.info("-----批量插入新的CapitalInfo股本信息: {} 条! 结束同步操作-----", rows);
        } catch (Exception exception) {
            log.error("-----执行定时同步CapitalInfo股本信息任务发生异常! 异常信息: ", exception);
            throw exception;
        }
    }
}
