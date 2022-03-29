package com.fystock.bigdata.cloud.mapper.sqlserver;

import com.fystock.bigdata.cloud.entity.sqlserver.InduSourceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InduInfoSourceMapper {

    /**
     * 获取第三方行业和股票分类信息
     *
     * @return
     */
    List<InduSourceInfo> getAllInduInfoSourceList();
}
