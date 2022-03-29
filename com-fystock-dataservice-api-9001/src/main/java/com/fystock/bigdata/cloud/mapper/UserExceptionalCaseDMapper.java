package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.UserExceptionalCaseDInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 各类异常用户数【按天统计】
 *
 * @author He.Yong
 * @since 2021-09-13 16:16:10
 */
@Mapper
public interface UserExceptionalCaseDMapper {
    /**
     * 获取各类异常用户数
     *
     * @return
     */
    List<UserExceptionalCaseDInfo> findAllUserExceptionalCaseDInfo();

    /**
     * 根据导入日期获取各类异常用户数
     *
     * @param importDateTime
     * @return
     */
    List<UserExceptionalCaseDInfo> findAllUserExceptionalCaseDInfoByImportDateTime(@Param("importDateTime") String importDateTime);
}
