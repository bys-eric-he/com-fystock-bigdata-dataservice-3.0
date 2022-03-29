package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.UserExceptionalCaseDInfo;
import com.fystock.bigdata.cloud.model.UserExceptionalCaseDModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-09-13 17:12:28
 */
public class UserExceptionalCaseDMapping {
    /**
     * Entity转Model
     *
     * @param userExceptionalCaseDInfo
     * @return
     */
    public static UserExceptionalCaseDModel toModel(UserExceptionalCaseDInfo userExceptionalCaseDInfo) {
        if (userExceptionalCaseDInfo == null) {
            return null;
        }

        UserExceptionalCaseDModel userExceptionalCaseDModel = new UserExceptionalCaseDModel();

        userExceptionalCaseDModel.setType(userExceptionalCaseDInfo.getType());
        userExceptionalCaseDModel.setTotalCount(userExceptionalCaseDInfo.getTotalCount());
        userExceptionalCaseDModel.setImportDateTime(userExceptionalCaseDInfo.getImportDateTime());

        return userExceptionalCaseDModel;
    }
}
