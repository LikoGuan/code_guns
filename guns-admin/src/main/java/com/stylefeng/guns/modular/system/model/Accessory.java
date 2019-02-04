package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 配件表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-24
 */
@TableName("accessory")
public class Accessory extends Model<Accessory> {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId(value = "accessory_id", type = IdType.AUTO)
    private Integer accessoryId;
    /**
     * 工艺id
     */
    @TableField("proc_id")
    private Integer procId;
    /**
     * 名称
     */
    private String name;
    /**
     * 配件描述
     */
    @TableField("accessory_desc")
    private String accessoryDesc;
    /**
     * 宽度
     */
    private BigDecimal width;
    /**
     * 长度
     */
    private BigDecimal length;
    /**
     * 厚度
     */
    private BigDecimal thickness;
    /**
     * 计量单位
     */
    @TableField("measure_unit")
    private Integer measureUnit;
    /**
     * 单位重量
     */
    @TableField("unit_weight")
    private Integer unitWeight;
    /**
     * 重量
     */
    private BigDecimal weight;
    /**
     * 添加时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 是否有效
     */
    @TableField("is_validate")
    private Integer isValidate;
    /**
     * 最后更新时间
     */
    @TableField("last_update")
    private Date lastUpdate;
    /**
     * 品牌id
     */
    @TableField("brand_id")
    private Integer brandId;
    /**
     * 生产商
     */
    @TableField("supplier_id")
    private Integer supplierId;
    /**
     * 缩略图
     */
    @TableField("material_thumb")
    private String materialThumb;
    /**
     * 备注
     */
    private String comment;


    public Integer getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Integer accessoryId) {
        this.accessoryId = accessoryId;
    }

    public Integer getProcId() {
        return procId;
    }

    public void setProcId(Integer procId) {
        this.procId = procId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessoryDesc() {
        return accessoryDesc;
    }

    public void setAccessoryDesc(String accessoryDesc) {
        this.accessoryDesc = accessoryDesc;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getThickness() {
        return thickness;
    }

    public void setThickness(BigDecimal thickness) {
        this.thickness = thickness;
    }

    public Integer getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(Integer measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Integer getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(Integer unitWeight) {
        this.unitWeight = unitWeight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsValidate() {
        return isValidate;
    }

    public void setIsValidate(Integer isValidate) {
        this.isValidate = isValidate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getMaterialThumb() {
        return materialThumb;
    }

    public void setMaterialThumb(String materialThumb) {
        this.materialThumb = materialThumb;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    protected Serializable pkVal() {
        return this.accessoryId;
    }

    @Override
    public String toString() {
        return "Accessory{" +
        "accessoryId=" + accessoryId +
        ", procId=" + procId +
        ", name=" + name +
        ", accessoryDesc=" + accessoryDesc +
        ", width=" + width +
        ", length=" + length +
        ", thickness=" + thickness +
        ", measureUnit=" + measureUnit +
        ", unitWeight=" + unitWeight +
        ", weight=" + weight +
        ", createTime=" + createTime +
        ", isValidate=" + isValidate +
        ", lastUpdate=" + lastUpdate +
        ", brandId=" + brandId +
        ", supplierId=" + supplierId +
        ", materialThumb=" + materialThumb +
        ", comment=" + comment +
        "}";
    }
}
