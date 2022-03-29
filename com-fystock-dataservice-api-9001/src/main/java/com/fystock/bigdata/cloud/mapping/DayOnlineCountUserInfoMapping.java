package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.DayOnlineCountUserInfo;
import com.fystock.bigdata.cloud.model.DayOnlineCountUserInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 10:15:47
 */
public class DayOnlineCountUserInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param dayOnlineCountUserInfo
     * @return
     */
    public static DayOnlineCountUserInfoModel toModel(DayOnlineCountUserInfo dayOnlineCountUserInfo) {
        if (dayOnlineCountUserInfo == null) {
            return null;
        }

        DayOnlineCountUserInfoModel dayOnlineCountUserInfoModel = new DayOnlineCountUserInfoModel();
        dayOnlineCountUserInfoModel.setCurrentDay(dayOnlineCountUserInfo.getCurrentDay());
        dayOnlineCountUserInfoModel.setCurrentDayCount(dayOnlineCountUserInfo.getCurrentDayCount());
        dayOnlineCountUserInfoModel.setImportDateTime(dayOnlineCountUserInfo.getImportDateTime());
        return dayOnlineCountUserInfoModel;
    }
}

