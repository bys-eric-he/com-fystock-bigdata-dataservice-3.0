package com.fystock.bigdata.cloud.mapper.msyql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fystock.bigdata.cloud.entity.mysql.InduDataInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InduInfoDataMapper extends BaseMapper<InduDataInfo> {

    /**
     * 获取本地数据库中的行业股票分类信息
     *
     * @param induSystemMark
     * @return
     */
    List<InduDataInfo> getAllInduInfoDataList(@Param("induSystemMark") Integer induSystemMark);

    /**
     * 批量插入行业股票分类信息
     *
     * @param infoDataEntityList
     * @return
     */
    int insertBatchInduInfoDataList(List<InduDataInfo> infoDataEntityList);

    /**
     * 批量删除行业股票分类信息
     *
     * @param ids
     * @return
     */
    int deleteBatchInduInfoDataByIds(List<Integer> ids);
}
