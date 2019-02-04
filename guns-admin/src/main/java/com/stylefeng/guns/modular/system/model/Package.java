package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 包装表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-25
 */
@TableName("package")
public class Package extends Model<Package> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "pack_id", type = IdType.AUTO)
    private Integer packId;
    /**
     * 包装名称
     */
    @TableField("pack_name")
    private String packName;
    /**
     * 图片
     */
    @TableField("pack_img")
    private String packImg;
    /**
     * 计量单位
     */
    @TableField("measure_unit")
    private Integer measureUnit;
    /**
     * 单位费用
     */
    @TableField("unit_fee")
    private BigDecimal unitFee;
    /**
     * 包装限制
     */
    @TableField("package_limitation")
    private BigDecimal packageLimitation;
    /**
     * 描述
     */
    @TableField("pack_desc")
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

    public Integer getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(Integer measureUnit) {
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

    @Override
    protected Serializable pkVal() {
        return this.packId;
    }

    @Override
    public String toString() {
        return "Package{" +
        "packId=" + packId +
        ", packName=" + packName +
        ", packImg=" + packImg +
        ", measureUnit=" + measureUnit +
        ", unitFee=" + unitFee +
        ", packageLimitation=" + packageLimitation +
        ", packDesc=" + packDesc +
        "}";
    }
}
