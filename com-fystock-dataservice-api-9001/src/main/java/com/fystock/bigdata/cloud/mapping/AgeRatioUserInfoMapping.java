package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.AgeRatioUserInfo;
import com.fystock.bigdata.cloud.model.AgeRatioUserInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 09:56:21
 */
public class AgeRatioUserInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param ageRatioUserInfo
     * @return
     */
    public static AgeRatioUserInfoModel toModel(AgeRatioUserInfo ageRatioUserInfo) {
        if (ageRatioUserInfo == null) {
            return null;
        }

        AgeRatioUserInfoModel ageRatioUserInfoModel = new AgeRatioUserInfoModel();
        ageRatioUserInfoModel.setAge(ageRatioUserInfo.getAge());
        ageRatioUserInfoModel.setTotalCount(ageRatioUserInfo.getTotalCount());
        ageRatioUserInfoModel.setAgeCount(ageRatioUserInfo.getAgeCount());
        ageRatioUserInfoModel.setRatio(ageRatioUserInfo.getRatio());
        ageRatioUserInfoModel.setImportDateTime(ageRatioUserInfo.getImportDateTime());
        return ageRatioUserInfoModel;
    }
}
