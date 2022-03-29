package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.constants.CityCache;
import com.fystock.bigdata.cloud.entity.mysql.CityCountRatioUserInfo;
import com.fystock.bigdata.cloud.model.CityCountRatioUserInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 10:06:21
 */
public class CityCountRatioUserInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param cityCountRatioUserInfo
     * @return
     */
    public static CityCountRatioUserInfoModel toModel(CityCountRatioUserInfo cityCountRatioUserInfo) {
        if (cityCountRatioUserInfo == null) {
            return null;
        }

        CityCountRatioUserInfoModel cityCountRatioUserInfoModel = new CityCountRatioUserInfoModel();
        cityCountRatioUserInfoModel.setCity(cityCountRatioUserInfo.getCity());
        cityCountRatioUserInfoModel.setCityCode(CityCache.getCityCode(cityCountRatioUserInfo.getCity()));
        cityCountRatioUserInfoModel.setProvince(CityCache.getProvinceName(cityCountRatioUserInfo.getCity()));
        cityCountRatioUserInfoModel.setProvinceCode(CityCache.getProvinceCode(cityCountRatioUserInfoModel.getProvince()));
        cityCountRatioUserInfoModel.setCityUserCount(cityCountRatioUserInfo.getCityUserCount());
        cityCountRatioUserInfoModel.setTotalUserCount(cityCountRatioUserInfo.getTotalUserCount());
        cityCountRatioUserInfoModel.setLastCityId(cityCountRatioUserInfo.getLastCityId());
        cityCountRatioUserInfoModel.setRatio(cityCountRatioUserInfo.getRatio());
        cityCountRatioUserInfoModel.setImportDateTime(cityCountRatioUserInfo.getImportDateTime());
        return cityCountRatioUserInfoModel;
    }
}
