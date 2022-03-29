package com.fystock.bigdata.cloud.service.impl;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fystock.bigdata.cloud.annotation.DataSource;
import com.fystock.bigdata.cloud.entity.mysql.InduDataInfo;
import com.fystock.bigdata.cloud.entity.sqlserver.InduSourceInfo;
import com.fystock.bigdata.cloud.enums.DataSourceEnum;
import com.fystock.bigdata.cloud.mapper.msyql.InduInfoDataMapper;
import com.fystock.bigdata.cloud.mapper.sqlserver.InduInfoSourceMapper;
import com.fystock.bigdata.cloud.service.InduInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 行业股票分类业务操作
 */
@Slf4j
@Service
public class InduInfoServiceImpl extends ServiceImpl<InduInfoDataMapper, InduDataInfo> implements InduInfoService {

    @Autowired
    private InduInfoDataMapper dataMapper;

    @Autowired
    private InduInfoSourceMapper sourceMapper;

    /**
     * 获取当前所有行业股票分类列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceEnum.bigdataMarketMysql)
    public List<InduDataInfo> getAllInduInfoDataList() {
        /*QueryWrapper<InduInfoDataEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("indu_sys_mark", 201);
        List<InduInfoDataEntity> induInfoDataEntities = dataMapper.selectList(queryWrapper);*/
        List<InduDataInfo> induInfoDataEntities = dataMapper.getAllInduInfoDataList(201);
        log.info("-->获取到本地行业体系为 201 的股票数量: {} 条!", induInfoDataEntities.size());
        /*
        try {
            log.info("-->具体内容: {}", MapperUtil.obj2json(induInfoDataEntities));
        } catch (Exception exception) {
            log.error("****>获取第三方所有行业股票分类列表List转换JSON失败!", exception);
        }*/

        return induInfoDataEntities;
    }

    /**
     * 获取第三方所有行业股票分类列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceEnum.f10SqlServer)
    public List<InduSourceInfo> getAllInduInfoSourceList() {
        List<InduSourceInfo> induInfoSourceEntities = sourceMapper.getAllInduInfoSourceList();
        log.info("-->获取到第三方行业股票数量: {} 条!", induInfoSourceEntities.size());

        /*
        try {
            log.info("-->具体内容: {}", MapperUtil.obj2json(induInfoSourceEntities));
        } catch (Exception exception) {
            log.error("****>第三方行业股票分类列表, 从List转换JSON失败!", exception);
        }*/

        return induInfoSourceEntities;
    }

    /**
     * 批量更新或保存第三方行业和股票分类到本地
     *
     * @param induInfoDataEntities
     * @param induInfoSourceEntities
     * @return
     */
    @Override
    @DataSource(value = DataSourceEnum.bigdataMarketMysql)
    public boolean saveOrUpdateInduInfo(List<InduDataInfo> induInfoDataEntities, List<InduSourceInfo> induInfoSourceEntities) {
        boolean result = false;

        //如果第三方数据为空则直接终止更新操作
        if (induInfoSourceEntities.size() == 0) {
            log.warn("*******第三方数据为空!*********");
            return false;
        }

        List<InduDataInfo> addDatas = new ArrayList<>();
        List<Integer> delDataIds = new ArrayList<>();

        //如果本地数据为空则直接全量保存第三方数据
        if (induInfoDataEntities.size() == 0) {
            log.info("----------本地数据为空,执行全量保存!------------");
            for (InduSourceInfo induInfoSourceEntity : induInfoSourceEntities) {
                InduDataInfo dataEntity = new InduDataInfo();
                dataEntity.setCorpCode(induInfoSourceEntity.getCorpCode().toUpperCase());
                dataEntity.setChangeDate(induInfoSourceEntity.getChangeDate() != null ? induInfoSourceEntity.getChangeDate() : new Date());
                dataEntity.setCreateTime(new Date());
                dataEntity.setUpdateTime(new Date());
                dataEntity.setInduCodeA(induInfoSourceEntity.getInduCodeA());
                dataEntity.setInduNameA(induInfoSourceEntity.getInduNameA());
                dataEntity.setInduCodeB(induInfoSourceEntity.getInduCodeB());
                dataEntity.setInduNameB(induInfoSourceEntity.getInduNameB());

                dataEntity.setInduSysMark(induInfoSourceEntity.getInduSysMark());
                dataEntity.setInduSysName(induInfoSourceEntity.getInduSysName());
                dataEntity.setExtTime(new Date());
                dataEntity.setStatus(true);
                dataEntity.setFaucet(false);

                addDatas.add(dataEntity);
            }
        } else {
            log.info("----------本地数据不为空,执行增量保存!------------");
            //如果本地数据不为空,从第三方数据中对比本地是否有这条数据,如果没有就新增
            for (InduSourceInfo induInfoSourceEntity : induInfoSourceEntities) {
                if (!induInfoDataEntities.contains(induInfoSourceEntity)) {
                    InduDataInfo dataEntity = new InduDataInfo();
                    dataEntity.setCorpCode(induInfoSourceEntity.getCorpCode().toUpperCase());
                    dataEntity.setChangeDate(induInfoSourceEntity.getChangeDate() != null ? induInfoSourceEntity.getChangeDate() : new Date());
                    dataEntity.setCreateTime(new Date());
                    dataEntity.setUpdateTime(new Date());
                    dataEntity.setInduCodeA(induInfoSourceEntity.getInduCodeA());
                    dataEntity.setInduNameA(induInfoSourceEntity.getInduNameA());
                    dataEntity.setInduCodeB(induInfoSourceEntity.getInduCodeB());
                    dataEntity.setInduNameB(induInfoSourceEntity.getInduNameB());

                    dataEntity.setInduSysMark(induInfoSourceEntity.getInduSysMark());
                    dataEntity.setInduSysName(induInfoSourceEntity.getInduSysName());
                    dataEntity.setExtTime(new Date());
                    dataEntity.setStatus(true);
                    dataEntity.setFaucet(false);

                    addDatas.add(dataEntity);
                }
            }
        }

        //批量保存新增的行业及股票
        if (addDatas.size() > 0) {
            int count = dataMapper.insertBatchInduInfoDataList(addDatas);
            result = (count == addDatas.size());
            log.info("+++++++批量保存新增的行业及股票数据: {} 条, {}!+++++++", addDatas.size(), result ? "成功" : "失败");
        } else {
            log.info("////////////---本地和第三方数据没有差异,跳过【保存】操作---////////////////");
        }

        //对比第三方是否有这条数据,如果没有就从本地删除
        for (InduDataInfo induInfoDataEntity : induInfoDataEntities) {
            if (!induInfoSourceEntities.contains(induInfoDataEntity)) {
                delDataIds.add(induInfoDataEntity.getInduId());
            }
        }

        //批量删除本地在第三方库中不存在的行业及股票
        if (delDataIds.size() > 0) {
            int count = dataMapper.deleteBatchInduInfoDataByIds(delDataIds);
            result = (delDataIds.size() == count);
            log.info("+++++++删除本地数据: {} 条 ,{} ! Ids ：{} +++++++", delDataIds.size(), result ? "成功" : "失败", delDataIds);
        } else {
            log.info("////////////---本地和第三方数据没有差异,跳过【删除】操作---////////////////");
        }

        return result;
    }
}
