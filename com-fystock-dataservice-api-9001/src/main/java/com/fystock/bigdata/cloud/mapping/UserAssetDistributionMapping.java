package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.UserAssetDistributionInfo;
import com.fystock.bigdata.cloud.model.UserAssetDistributionModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-09-13 15:37:28
 */
public class UserAssetDistributionMapping {
    /**
     * Entity 转Model
     *
     * @param userAssetDistributionInfo
     * @return
     */
    public static UserAssetDistributionModel toModel(UserAssetDistributionInfo userAssetDistributionInfo) {

        if (userAssetDistributionInfo == null) {
            return null;
        }

        UserAssetDistributionModel userAssetDistributionModel = new UserAssetDistributionModel();

        userAssetDistributionModel.setUserCount(userAssetDistributionInfo.getUserCount());
        userAssetDistributionModel.setAssetScale(userAssetDistributionInfo.getAssetScale());
        userAssetDistributionModel.setImportDateTime(userAssetDistributionInfo.getImportDateTime());

        return userAssetDistributionModel;
    }
}
