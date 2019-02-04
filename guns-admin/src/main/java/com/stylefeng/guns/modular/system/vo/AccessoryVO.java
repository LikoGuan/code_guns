package com.stylefeng.guns.modular.system.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by likoguan on 24/10/18.
 */
public class AccessoryVO {
    /**
     * 品牌id
     */
    private Integer accessoryId;
    /**
     * 工艺
     */
    private String procName;
    /**
     * 名称
     */
    private String name;
    /**
     * 配件描述
     */
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
    private String measureUnit;
    /**
     * 单位重量
     */
    private String unitWeight;
    /**
     * 重量
     */
    private BigDecimal weight;
    /**
     * 添加时间
     */
    private Date createTime;
    /**
     * 是否有效
     */
    private Integer isValidate;
    /**
     * 最后更新时间
     */
    private Date lastUpdate;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 生产商
     */
    private String supplier;
    /**
     * 缩略图
     */
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

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
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

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(String unitWeight) {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
}
