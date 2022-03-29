package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.User;
import com.fystock.bigdata.cloud.model.UserModel;

public class UserMapping {
    /**
     * Entity 转换成 Model
     *
     * @param user
     * @return
     */
    public static UserModel toModel(User user) {
        if (user == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUserName(user.getUserName());
        userModel.setPassWord("***");
        userModel.setRole(user.getRole());

        return userModel;
    }
}
