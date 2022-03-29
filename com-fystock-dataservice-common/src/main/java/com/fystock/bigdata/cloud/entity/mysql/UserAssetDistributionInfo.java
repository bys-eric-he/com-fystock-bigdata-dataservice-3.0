package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户资产规模人数
 *
 * @author He.Yong
 * @since 2021-09-13 10:46:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAssetDistributionInfo extends BaseInfo {
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
    private short assetScale;

    /**
     * 规模人数
     */
    private long userCount;
}
