package com.fystock.bigdata.cloud.executor;

import com.fystock.bigdata.cloud.entity.mysql.MoneyRateInfo;
import com.fystock.bigdata.cloud.service.MoneyRateInfoService;
import com.xxl.job.core.handler.IJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 同步货币汇率基础信息
 *
 * @author He.Yong
 * @since 2021-09-10 14:38:25
 */
@Slf4j
@Service("MoneyRateInfoDateTimerExecutor")
public class MoneyRateInfoDateTimerExecutor extends IJobHandler {

    @Autowired
    private MoneyRateInfoService moneyRateInfoService;

    /**
     * 执行处理器
     *
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        try {
            log.info("-----开始执行定时同步货币汇率MoneyRateInfo数据信息任务-----");
            List<MoneyRateInfo> moneyRateInfos = moneyRateInfoService.getAllMoneyRateInfoList();
            if (moneyRateInfos.size() <= 0) {
                log.info("-----没有新的MoneyRateInfo数据信息,结束同步操作-----");
                return;
            }

            log.info("-----开始清除旧的货币汇率MoneyRateInfo数据信息数据-----");
            int number = moneyRateInfoService.truncateMoneyRateInfo();
            log.info("-----TRUNCATE TABLE方式,清除旧的货币汇率MoneyRateInfo数据信息： {} 条!-----", number);
            log.info("-----开始批量插入新的货币汇率MoneyRateInfo数据信息数据-----");
            int rows = moneyRateInfoService.insertMoneyRateInfoList(moneyRateInfos);
            log.info("-----批量插入新的货币汇率MoneyRateInfo数据信息: {} 条! 结束同步操作-----", rows);
        } catch (Exception exception) {
            log.error("-----执行定时同步货币汇率MoneyRateInfo数据信息任务发生异常! 异常信息: ", exception);
            throw exception;
        }
    }
}
