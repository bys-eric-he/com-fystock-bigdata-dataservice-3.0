package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.TotalCountUserInfo;
import com.fystock.bigdata.cloud.model.TotalCountUserInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 10:40:28
 */
public class TotalCountUserInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param totalCountUserInfo
     * @return
     */
    public static TotalCountUserInfoModel toModel(TotalCountUserInfo totalCountUserInfo) {
        if (totalCountUserInfo == null) {
            return null;
        }

        TotalCountUserInfoModel totalCountUserInfoModel = new TotalCountUserInfoModel();
        totalCountUserInfoModel.setType(totalCountUserInfo.getType());
        totalCountUserInfoModel.setTotalCount(totalCountUserInfo.getTotalCount());
        totalCountUserInfoModel.setImportDateTime(totalCountUserInfo.getImportDateTime());

        return totalCountUserInfoModel;
    }
}
