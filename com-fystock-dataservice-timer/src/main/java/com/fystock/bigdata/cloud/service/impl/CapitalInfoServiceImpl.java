package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.annotation.DataSource;
import com.fystock.bigdata.cloud.entity.mysql.CapitalInfo;
import com.fystock.bigdata.cloud.enums.DataSourceEnum;
import com.fystock.bigdata.cloud.mapper.msyql.CapitalInfoMapper;
import com.fystock.bigdata.cloud.service.CapitalInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股本信息
 *
 * @author He.Yong
 * @since 2021-07-16 15:24:36
 */
@Slf4j
@Service
public class CapitalInfoServiceImpl implements CapitalInfoService {

    @Autowired
    private CapitalInfoMapper capitalInfoMapper;

    /**
     * 获取所有股本信息
     *
     * @return
     */
    @Override
    @DataSource(DataSourceEnum.sunlineF10Mysql)
    public List<CapitalInfo> getAllCapitalInfoSourceList() {
        List<CapitalInfo> capitalInfos = capitalInfoMapper.getAllCapitalInfoSourceList();
        int count = capitalInfos.size();
        log.info("----从F10获取到股本信息数据: {} 条!----", count);
        return capitalInfos;
    }

    /**
     * 清空表数据
     *
     * @return
     */
    @Override
    @DataSource(DataSourceEnum.bigdataMarketMysql)
    public int truncateCapitalInfoTable() {
        log.info("----清空股本CapitalInfo数据表信息-----");
        int count = capitalInfoMapper.truncateCapitalInfoTable();
        log.info("----TRUNCATE TABLE方式,清空股本CapitalInfo数据表信息完成,影响数: {} 行!-----", count);
        return count;
    }

    /**
     * 批量插入股本信息
     *
     * @param capitalInfoList
     * @return
     */
    @Override
    @DataSource(DataSourceEnum.bigdataMarketMysql)
    public int insertCapitalInfoBatch(List<CapitalInfo> capitalInfoList) {
        log.info("----批量插入股本CapitalInfo数据表信息-----");
        long beginTime = System.currentTimeMillis();
        int count = capitalInfoMapper.insertCapitalInfoBatch(capitalInfoList);
        long endTime = System.currentTimeMillis();
        log.info("----批量插入股本CapitalInfo信息完成, 耗时 ：{}  毫秒, 影响行数: {} 行!----", (endTime - beginTime), count);
        return count;
    }
}
