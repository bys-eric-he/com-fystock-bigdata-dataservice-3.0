package com.fystock.bigdata.cloud.entity.mysql;


import com.fystock.bigdata.cloud.entity.sqlserver.InduSourceInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 股票行业分类详细信息
 *
 * @author He.Yong
 * @since 2021-06-05 11:23:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InduDataInfo {
    /**
     * 行业ID
     */
    private int induId;
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
     * 行业三级代码
     */
    private String induCodeC;
    /**
     * 行业三级名称
     */
    private String induNameC;

    /**
     * 行业四级代码
     */
    private String induCodeD;
    /**
     * 行业四级名称
     */
    private String induNameD;

    /**
     * 变动日期
     */
    private Date changeDate;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 扩展日期
     */
    private Date extTime;

    /**
     * 状态
     */
    private boolean isStatus;

    /**
     * 是否龙头股 0 表示非龙头股,1表示龙头股
     */
    private boolean isFaucet;

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
        if (object instanceof InduSourceInfo) {
            InduSourceInfo induInfoSourceEntity = (InduSourceInfo) object;
            return this.getCorpCode().equalsIgnoreCase(induInfoSourceEntity.getCorpCode())
                    && this.getInduCodeA().equalsIgnoreCase(induInfoSourceEntity.getInduCodeA())
                    && this.getInduCodeB().equalsIgnoreCase(induInfoSourceEntity.getInduCodeB());
        }
        return super.equals(object);
    }
}