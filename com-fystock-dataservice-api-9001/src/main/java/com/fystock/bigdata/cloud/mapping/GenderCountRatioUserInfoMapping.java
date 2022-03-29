package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.GenderCountRatioUserInfo;
import com.fystock.bigdata.cloud.model.GenderCountRatioUserInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 10:26:58
 */
public class GenderCountRatioUserInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param genderCountRatioUserInfo
     * @return
     */
    public static GenderCountRatioUserInfoModel toModel(GenderCountRatioUserInfo genderCountRatioUserInfo) {
        if (genderCountRatioUserInfo == null) {
            return null;
        }

        GenderCountRatioUserInfoModel genderCountRatioUserInfoModel = new GenderCountRatioUserInfoModel();
        genderCountRatioUserInfoModel.setGender(genderCountRatioUserInfo.getGender());
        genderCountRatioUserInfoModel.setGenderUserCount(genderCountRatioUserInfo.getGenderUserCount());
        genderCountRatioUserInfoModel.setTotalUserCount(genderCountRatioUserInfo.getTotalUserCount());
        genderCountRatioUserInfoModel.setRatio(genderCountRatioUserInfo.getRatio());
        genderCountRatioUserInfoModel.setImportDateTime(genderCountRatioUserInfo.getImportDateTime());

        return genderCountRatioUserInfoModel;
    }
}

