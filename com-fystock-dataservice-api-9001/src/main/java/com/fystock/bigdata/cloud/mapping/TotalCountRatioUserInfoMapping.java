package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.TotalCountRatioUserInfo;
import com.fystock.bigdata.cloud.model.TotalCountRatioUserInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 10:36:31
 */
public class TotalCountRatioUserInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param totalCountRatioUserInfo
     * @return
     */
    public static TotalCountRatioUserInfoModel toModel(TotalCountRatioUserInfo totalCountRatioUserInfo) {
        if (totalCountRatioUserInfo == null) {
            return null;
        }

        TotalCountRatioUserInfoModel totalCountRatioUserInfoModel = new TotalCountRatioUserInfoModel();
        totalCountRatioUserInfoModel.setType(totalCountRatioUserInfo.getType());
        totalCountRatioUserInfoModel.setTotalCount(totalCountRatioUserInfo.getTotalCount());
        totalCountRatioUserInfoModel.setRegisterTotalCount(totalCountRatioUserInfo.getRegisterTotalCount());
        totalCountRatioUserInfoModel.setRatio(totalCountRatioUserInfo.getRatio());
        totalCountRatioUserInfoModel.setImportDateTime(totalCountRatioUserInfo.getImportDateTime());

        return totalCountRatioUserInfoModel;
    }
}
