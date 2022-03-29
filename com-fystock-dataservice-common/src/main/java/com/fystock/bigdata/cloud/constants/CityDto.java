package com.fystock.bigdata.cloud.constants;

import lombok.Data;

/**
 * 城市信息中间对象
 *
 * @author He.Yong
 * @since 2021-03-15 15:33:00
 */
@Data
public class CityDto {
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 所属省份
     */
    private String province;
}
