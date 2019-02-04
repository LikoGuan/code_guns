package com.stylefeng.guns.modular.system.vo;

import java.math.BigDecimal;

/**
 * Created by likoguan on 25/10/18.
 */
public class PackageVO {
    private Integer packId;

    private String packName;

    private String packImg;

    private String measureUnit;

    private BigDecimal unitFee;

    private BigDecimal packageLimitation;

    private String packDesc;

    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getPackImg() {
        return packImg;
    }

    public void setPackImg(String packImg) {
        this.packImg = packImg;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public BigDecimal getUnitFee() {
        return unitFee;
    }

    public void setUnitFee(BigDecimal unitFee) {
        this.unitFee = unitFee;
    }

    public BigDecimal getPackageLimitation() {
        return packageLimitation;
    }

    public void setPackageLimitation(BigDecimal packageLimitation) {
        this.packageLimitation = packageLimitation;
    }

    public String getPackDesc() {
        return packDesc;
    }

    public void setPackDesc(String packDesc) {
        this.packDesc = packDesc;
    }
}
