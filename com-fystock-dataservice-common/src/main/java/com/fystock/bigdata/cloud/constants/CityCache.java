package com.fystock.bigdata.cloud.constants;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fystock.bigdata.cloud.utils.FileUtil;

import java.util.HashMap;
import java.util.Objects;

/**
 * 从Json文件中加载城市码表
 *
 * @author He.Yong
 * @since 2021-03-15 11:21:00
 */
public class CityCache {
    private static final String CITY_JSON_PATH = "CityCode.json";

    private static HashMap<String, CityDto> cityCodeCache;

    static {
        String s = FileUtil.readResourceFile(CITY_JSON_PATH);
        cityCodeCache = JSON.parseObject(s, new TypeReference<HashMap<String, CityDto>>() {
        });
    }

    /**
     * 根据城市名称获取城市编码
     *
     * @param cityName
     * @return
     */
    public static String getCityCode(String cityName) {
        CityDto cityDto = cityCodeCache.get(cityName);
        if (Objects.nonNull(cityDto)) {
            return cityDto.getCityCode();
        }
        return "";
    }

    /**
     * 根据城市名称获取省份名称
     *
     * @param cityName
     * @return
     */
    public static String getProvinceName(String cityName) {
        CityDto cityDto = cityCodeCache.get(cityName);
        if (Objects.nonNull(cityDto)) {
            return cityDto.getProvince();
        }
        return "";
    }

    /**
     * 根据省份名称获取省份编码
     *
     * @param provinceName
     * @return
     */
    public static String getProvinceCode(String provinceName) {
        CityDto cityDto = cityCodeCache.get(provinceName);
        if (Objects.nonNull(cityDto)) {
            return cityDto.getCityCode();
        }
        return "";
    }
}
