package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.DayActiveCountUserInfo;
import com.fystock.bigdata.cloud.model.DayActiveCountUserInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 10:11:14
 */
public class DayActiveCountUserInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param dayActiveCountUserInfo
     * @return
     */
    public static DayActiveCountUserInfoModel toModel(DayActiveCountUserInfo dayActiveCountUserInfo) {
        if (dayActiveCountUserInfo == null) {
            return null;
        }

        DayActiveCountUserInfoModel dayActiveCountUserInfoModel = new DayActiveCountUserInfoModel();
        dayActiveCountUserInfoModel.setCurrentDay(dayActiveCountUserInfo.getCurrentDay());
        dayActiveCountUserInfoModel.setCurrentDayCount(dayActiveCountUserInfo.getCurrentDayCount());
        dayActiveCountUserInfoModel.setImportDateTime(dayActiveCountUserInfo.getImportDateTime());
        return dayActiveCountUserInfoModel;
    }
}

