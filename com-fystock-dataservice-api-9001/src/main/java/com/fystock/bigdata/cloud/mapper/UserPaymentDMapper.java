package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.UserPaymentDInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 付费用户数据信息【按天统计】
 *
 * @author He.Yong
 * @since 2021-09-14 10:13:15
 */
@Mapper
public interface UserPaymentDMapper {
    /**
     * 获取付费用户数据信息
     *
     * @return
     */
    UserPaymentDInfo findAllUserPaymentDInfo();

    /**
     * 根据导入日期获取付费用户数据信息
     *
     * @param importDateTime
     * @return
     */
    UserPaymentDInfo findAllUserPaymentDInfoByImportDateTime(@Param("importDateTime") String importDateTime);
}
