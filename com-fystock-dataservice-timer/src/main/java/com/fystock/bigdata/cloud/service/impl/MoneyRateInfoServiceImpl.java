package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.annotation.DataSource;
import com.fystock.bigdata.cloud.entity.mysql.MoneyRateInfo;
import com.fystock.bigdata.cloud.enums.DataSourceEnum;
import com.fystock.bigdata.cloud.mapper.msyql.MoneyRateInfoMapper;
import com.fystock.bigdata.cloud.service.MoneyRateInfoService;
import com.fystock.bigdata.cloud.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 货币汇率信息同步
 *
 * @author He.Yong
 * @since 2021-09-10 16:09:14
 */
@Slf4j
@Service
public class MoneyRateInfoServiceImpl implements MoneyRateInfoService {

    @Autowired
    private MoneyRateInfoMapper moneyRateInfoMapper;

    /**
     * 获取所有货币汇率信息
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceEnum.cubpMysql)
    public List<MoneyRateInfo> getAllMoneyRateInfoList() {
        List<MoneyRateInfo> moneyRateInfos = moneyRateInfoMapper.getAllMoneyRateInfoList();
        log.info("----从CUBP获取货币汇率MoneyRateInfo数据表信息,数据条数: {} 条!-----", moneyRateInfos.size());
        return moneyRateInfos;
    }

    /**
     * 清空所有货币汇率信息
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceEnum.bigdataMarketMysql)
    public int truncateMoneyRateInfo() {
        log.info("----清空货币汇率MoneyRateInfo数据表信息-----");
        int count = moneyRateInfoMapper.truncateMoneyRateInfo();
        log.info("----TRUNCATE TABLE方式,清空货币汇率MoneyRateInfo数据表信息完成,影响数: {} 行!-----", count);
        return count;
    }

    /**
     * 插入货币汇率信息
     *
     * @param moneyRateInfos
     * @return
     */
    @Override
    @DataSource(value = DataSourceEnum.bigdataMarketMysql)
    public int insertMoneyRateInfoList(List<MoneyRateInfo> moneyRateInfos) {
        log.info("----批量插入货币汇率MoneyRateInfo数据表信息-----");
        long beginTime = System.currentTimeMillis();

        int results = 0;
        int pages = 1;
        int pageSize = 1000;
        int totalSize = moneyRateInfos.size();
        try {
            while (PageUtil.startMoneyRateInfoPage(moneyRateInfos, pages, pageSize).size() > 0) {
                List<MoneyRateInfo> list = PageUtil.startMoneyRateInfoPage(moneyRateInfos, pages, pageSize);
                results += moneyRateInfoMapper.insertMoneyRateInfoBatch(list);
                log.info("----已插入第: {} 页  共: {} 条数据!----", pages, results);
                //如果当前插入的总数等于记录总数,则认为已经提取完所有数据
                if (results == totalSize) {
                    log.info("----数据分页插入完成! 共 : {} 页,  共: {} 条数据!----", pages, results);
                    break;
                }
                pages++;
            }
        } catch (Exception exception) {
            log.error("****批量插入货币汇率MoneyRateInfo数据信息异常! 异常信息：", exception);
        }
        long endTime = System.currentTimeMillis();
        log.info("----批量插入货币汇率MoneyRateInfo数据完成, 耗时 ：{}  毫秒, 影响行数: {} 行!----", (endTime - beginTime), results);
        return results;
    }
}
