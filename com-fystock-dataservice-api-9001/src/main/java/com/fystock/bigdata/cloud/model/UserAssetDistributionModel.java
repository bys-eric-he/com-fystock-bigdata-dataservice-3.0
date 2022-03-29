package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户资产规模人数
 *
 * @author He.Yong
 * @since 2021-09-13 14:44:25
 */
@Data
@NoArgsConstructor
@ApiModel(value = "userAssetDistributionModel", description = "用户资产规模人数")
public class UserAssetDistributionModel extends BaseModel {
    /**
     * 资产规模
     * 0：AS=0
     * 1：0<AS<1w
     * 2：1w<=AS<5w
     * 3：5w<=AS<10w
     * 4：10w<=AS<20w
     * 5：20w<=AS<50w
     * 6：AS>=50w
     * 7：AS<0
     */
    @ApiModelProperty(value = "资产规模(" +
            "     * 0：AS=0\n" +
            "     * 1：0<AS<1w\n" +
            "     * 2：1w<=AS<5w\n" +
            "     * 3：5w<=AS<10w\n" +
            "     * 4：10w<=AS<20w\n" +
            "     * 5：20w<=AS<50w\n" +
            "     * 6：AS>=50w\n" +
            "     * 7：AS<0" +
            ")", name = "assetScale")
    private short assetScale;

    /**
     * 规模人数
     */
    @ApiModelProperty(value = "规模人数", name = "userCount")
    private long userCount;
}
