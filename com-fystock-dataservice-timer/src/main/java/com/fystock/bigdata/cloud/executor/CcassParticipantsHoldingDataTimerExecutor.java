package com.fystock.bigdata.cloud.executor;

import com.fystock.bigdata.cloud.crawler.CcassHoldingsCrawler;
import com.fystock.bigdata.cloud.entity.clickhouse.CcassHoldingsInfo;
import com.fystock.bigdata.cloud.entity.mysql.CapitalInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.CapitalInfoService;
import com.fystock.bigdata.cloud.service.ICcassHoldingsInfoRPCProviderService;
import com.xxl.job.core.handler.IJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务同步CCASS信息
 *
 * @author He.Yong
 * @since 2021-08-12 15:12:35
 */
@Slf4j
@Service("CcassParticipantsHoldingDataTimerExecutor")
public class CcassParticipantsHoldingDataTimerExecutor extends IJobHandler {

    @Value("${ccass.holdings-url}")
    private String CCASS_HOLDINGS_URL;

    /**
     * Dubbo远程服务调用
     */
    @DubboReference
    private ICcassHoldingsInfoRPCProviderService iCcassHoldingsInfoRPCProviderService;

    /**
     * 交易日期格式
     */
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private CapitalInfoService capitalInfoService;

    @Override
    public void execute() throws Exception {
        try {
            log.info("-----开始执行定时任务同步CCASS数据信息任务-----");
            List<CapitalInfo> capitalInfos = capitalInfoService.getAllCapitalInfoSourceList();
            List<String> participantStrIDs = new ArrayList<>();

            //中國證券登記結算(香港)有限公司 CCASS ID: A00003
            participantStrIDs.add("1323");
            //中國證券登記結算(香港)有限公司 CCASS ID: A00004
            participantStrIDs.add("1456");
            //中國證券登記結算(香港)有限公司 CCASS ID: A00005
            participantStrIDs.add("1684");

            // 爬取参与者持仓信息
            for (String partId : participantStrIDs) {
                // 拼接URL
                String url = CCASS_HOLDINGS_URL
                        .replaceFirst("\\$\\[\\]", String.valueOf(partId))
                        .replaceFirst("\\$\\[\\]", simpleDateFormat.format(new Date()));

                CcassHoldingsCrawler ccassHoldingsCrawler = new CcassHoldingsCrawler("crawl", true);
                ccassHoldingsCrawler.addSeed(url);
                ccassHoldingsCrawler.setThreads(5);
                ccassHoldingsCrawler.setTopN(50);
                ccassHoldingsCrawler.setCapitalInfoList(capitalInfos);
                // 网页爬取深度为4
                ccassHoldingsCrawler.start(4);

                List<CcassHoldingsInfo> ccassHoldingsInfos = ccassHoldingsCrawler.getCcassHoldingsInfoList();
                log.info("---->当前参与者：{} ,持仓信息：{}", partId, ccassHoldingsInfos);

                if (ObjectUtils.isEmpty(ccassHoldingsInfos)) {
                    log.warn("------暂无参与者：{} ,持仓信息-------", partId);
                    break;
                }
                try {

                    CommonResult<Object> response = iCcassHoldingsInfoRPCProviderService.insertCcassHoldingsInfo(ccassHoldingsInfos);
                    if (ObjectUtils.isNotEmpty(response)) {
                        if (response.getCode().equals(200)) {
                            log.info("-----参与者：{} ,持仓信息写入ClickHouse成功!-------", partId);
                            log.info("-----返回数据包：" + response.getData());
                        } else {
                            log.warn("******参与者：{} ,持仓信息写入ClickHouse失败! 失败原因：{}******", partId, response);
                        }
                    } else {
                        log.warn("******-定时任务接口调用, 参与者：{} ,持仓信息写入ClickHouse响应返回为空!******", partId);
                    }
                } catch (Exception exception) {
                    log.error("**********参与者：{} ,持仓信息写入ClickHouse数据发生异常!***********", partId, exception);
                }
            }

            log.info("-----结束执行定时任务同步CCASS数据信息任务-----");
        } catch (Exception exception) {
            log.error("******定时任务同步CCASS信息异常 : {}", exception.getMessage());
            throw exception;
        }
    }
}
