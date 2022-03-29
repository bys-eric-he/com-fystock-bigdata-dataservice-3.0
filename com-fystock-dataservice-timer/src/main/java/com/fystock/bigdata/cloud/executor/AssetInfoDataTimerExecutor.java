package com.fystock.bigdata.cloud.executor;

import com.fystock.bigdata.cloud.entity.mysql.AssetInfo;
import com.fystock.bigdata.cloud.service.AssetInfoService;
import com.xxl.job.core.handler.IJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时同步AssetInfo股票信息
 *
 * @author He.Yong
 * @since 2021-04-12 09:14:15
 */
@Slf4j
@Service("AssetInfoDataTimerExecutor")
public class AssetInfoDataTimerExecutor extends IJobHandler {

    @Autowired
    private AssetInfoService assetInfoService;

    @Override
    public void execute() throws Exception {
        try {
            log.info("-----开始执行定时同步AssetInfo股票信息任务-----");
            List<AssetInfo> assetInfos = assetInfoService.getAllAssetInfoSourceList();
            if (assetInfos.size() <= 0) {
                log.info("-----没有新的AssetInfo股票信息,结束同步操作-----");
                return;
            }

            log.info("-----开始清除旧的AssetInfo股票信息数据-----");
            int number = assetInfoService.truncateAssetInfoTable();
            log.info("-----TRUNCATE TABLE方式,清除旧的AssetInfo股票信息： {} 条!-----", number);
            log.info("-----开始批量插入新的AssetInfo股票信息数据-----");
            int rows = assetInfoService.insertAssetInfoBatch(assetInfos);
            log.info("-----批量插入新的AssetInfo股票信息: {} 条! 结束同步操作-----", rows);
        } catch (Exception exception) {
            log.error("-----执行定时同步AssetInfo股票信息任务发生异常! 异常信息: ", exception);
            throw exception;
        }
    }
}
