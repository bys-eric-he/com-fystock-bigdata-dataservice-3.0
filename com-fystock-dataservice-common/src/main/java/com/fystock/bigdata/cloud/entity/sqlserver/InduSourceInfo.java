package com.fystock.bigdata.cloud.entity.sqlserver;

import com.fystock.bigdata.cloud.entity.mysql.InduDataInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * SELECT
 * A.SECCODE AS CORP_CODE,
 * '201' AS INDU_SYS_MARK,
 * 'SHKIndustryCode' AS INDU_SYS_NAME,
 * A.F002V AS INDU_CODE_A,
 * B.F001V AS INDU_NAME_A,
 * A.F003V AS INDU_CODE_B,
 * C.F001V AS INDU_NAME_B,
 * A.Modified_Date AS CHANGE_DATE
 * FROM XNHKS0401 AS A
 * <p>
 * INNER JOIN XNHK0004 AS B
 * ON A.F002V = B.CODE
 * INNER JOIN XNHK0005 AS C
 * ON A.F003V = C.CODE
 * 股票行业分类第三方数据提供
 *
 * @author He.Yong
 * @since 2021-06-11 13:33:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InduSourceInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 股票代码+市场
     */
    private String corpCode;
    /**
     * 行业分类体系标识
     */
    private int induSysMark;
    /**
     * 行业分类体系名
     */
    private String induSysName;
    /**
     * 行业一级代码
     */
    private String induCodeA;
    /**
     * 行业一级名称
     */
    private String induNameA;
    /**
     * 行业二级代码
     */
    private String induCodeB;
    /**
     * 行业二级名称
     */
    private String induNameB;

    /**
     * 变动日期
     */
    private Date changeDate;

    /**
     * 判断集合中是否包含具体某个对象
     * 覆写 这个类的 equals 方法，然后用ArrayList.contains(obj)
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof InduDataInfo) {
            InduDataInfo induInfoDataEntity = (InduDataInfo) object;
            return this.getCorpCode().equalsIgnoreCase(induInfoDataEntity.getCorpCode())
                    && this.getInduCodeA().equalsIgnoreCase(induInfoDataEntity.getInduCodeA())
                    && this.getInduCodeB().equalsIgnoreCase(induInfoDataEntity.getInduCodeB());
        }
        return super.equals(object);
    }

}