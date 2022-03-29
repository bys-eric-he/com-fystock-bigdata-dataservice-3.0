package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.RiskWarnInfo;
import com.fystock.bigdata.cloud.model.RiskWarnInfoModel;

/**
 * 实体层到模型层数据转换
 *
 * @author He.Yong
 * @since 2021-03-15 10:14:59
 */
public class RiskWarnInfoMapping {
    /**
     * Entity 转换成 Model
     *
     * @param riskWarnInfo
     * @return
     */
    public static RiskWarnInfoModel toModel(RiskWarnInfo riskWarnInfo) {
        if (riskWarnInfo == null) {
            return null;
        }

        RiskWarnInfoModel riskWarnInfoModel = new RiskWarnInfoModel();

        riskWarnInfoModel.setProjectId(riskWarnInfo.getProjectId());
        riskWarnInfoModel.setClientId(riskWarnInfo.getClientId());
        riskWarnInfoModel.setCount(riskWarnInfo.getCount());
        riskWarnInfoModel.setRiskDateTime(riskWarnInfo.getRiskDateTime());
        riskWarnInfoModel.setImportDateTime(riskWarnInfo.getImportDateTime());

        return riskWarnInfoModel;
    }
}
