package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.UserPaymentDInfo;
import com.fystock.bigdata.cloud.model.UserPaymentDModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-09-14 10:09:11
 */
public class UserPaymentDMapping {

    /**
     * Entity转Model
     *
     * @param userPaymentDInfo
     * @return
     */
    public static UserPaymentDModel toModel(UserPaymentDInfo userPaymentDInfo) {
        if (userPaymentDInfo == null) {
            return null;
        }

        UserPaymentDModel userPaymentDModel = new UserPaymentDModel();
        userPaymentDModel.setDayHunterSum(userPaymentDInfo.getDayHunterSum());
        userPaymentDModel.setDayHunterCount(userPaymentDInfo.getDayHunterCount());
        userPaymentDModel.setDayHunterTotalSum(userPaymentDInfo.getDayHunterTotalSum());
        userPaymentDModel.setDayHunterTotalCount(userPaymentDInfo.getDayHunterTotalCount());
        userPaymentDModel.setDayVipSum(userPaymentDInfo.getDayVipSum());
        userPaymentDModel.setDayVipCount(userPaymentDInfo.getDayVipCount());
        userPaymentDModel.setDayVipTotalSum(userPaymentDInfo.getDayVipTotalSum());
        userPaymentDModel.setDayVipTotalCount(userPaymentDInfo.getDayVipTotalCount());
        userPaymentDModel.setImportDateTime(userPaymentDInfo.getImportDateTime());

        return userPaymentDModel;
    }
}
