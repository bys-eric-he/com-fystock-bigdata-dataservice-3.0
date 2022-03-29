package com.fystock.bigdata.cloud.mapper.msyql;

import com.fystock.bigdata.cloud.entity.mysql.MoneyRateInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 货币汇率信息
 *
 * @author He.Yong
 * @since 2021-09-10 14:40:30
 */
@Mapper
public interface MoneyRateInfoMapper {

    /**
     * 获取所有货币汇率信息
     *
     * @return
     */
    List<MoneyRateInfo> getAllMoneyRateInfoList();

    /**
     * 清空所有货币汇率信息
     *
     * @return
     */
    int truncateMoneyRateInfo();

    /**
     * 插入货币汇率信息
     *
     * @param moneyRateInfos
     * @return
     */
    int insertMoneyRateInfoBatch(List<MoneyRateInfo> moneyRateInfos);
}
