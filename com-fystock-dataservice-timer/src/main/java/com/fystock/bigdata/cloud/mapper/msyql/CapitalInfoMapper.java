package com.fystock.bigdata.cloud.mapper.msyql;

import com.fystock.bigdata.cloud.entity.mysql.CapitalInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CapitalInfoMapper {
    /**
     * 获取所有股本信息
     *
     * @return
     */
    List<CapitalInfo> getAllCapitalInfoSourceList();

    /**
     * 清空表数据
     *
     * @return
     */
    int truncateCapitalInfoTable();

    /**
     * 批量插入股本信息
     *
     * @param capitalInfoList
     * @return
     */
    int insertCapitalInfoBatch(List<CapitalInfo> capitalInfoList);
}
