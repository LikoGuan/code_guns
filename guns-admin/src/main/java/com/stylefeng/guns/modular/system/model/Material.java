package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 材料表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-28
 */
public class Material extends Model<Material> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 主分类id
     */
    @TableField("cat_id")
    private Integer catId;
    /**
     * 材料名称
     */
    @TableField("material_name")
    private String materialName;
    /**
     * 计量单位
     */
    @TableField("measure_unit")
    private Integer measureUnit;
    /**
     * 关键词
     */
    private String keywords;
    /**
     * 描述
     */
    @TableField("material_desc")
    private String materialDesc;
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
     * 单位重量
     */
    @TableField("unit_weight")
    private Integer unitWeight;
    /**
     * 包装方式id
     */
    @TableField("pack_id")
    private Integer packId;
    /**
     * 是否实物
     */
    @TableField("is_real")
    private Integer isReal;
    /**
     * 是否为普通商品
     */
    @TableField("is_merchandise")
    private Integer isMerchandise;
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
     * 品牌ID
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(Integer measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
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

    public Integer getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(Integer unitWeight) {
        this.unitWeight = unitWeight;
    }

    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }

    public Integer getIsReal() {
        return isReal;
    }

    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

    public Integer getIsMerchandise() {
        return isMerchandise;
    }

    public void setIsMerchandise(Integer isMerchandise) {
        this.isMerchandise = isMerchandise;
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
        return this.id;
    }

    @Override
    public String toString() {
        return "Material{" +
        "id=" + id +
        ", catId=" + catId +
        ", materialName=" + materialName +
        ", measureUnit=" + measureUnit +
        ", keywords=" + keywords +
        ", materialDesc=" + materialDesc +
        ", width=" + width +
        ", length=" + length +
        ", thickness=" + thickness +
        ", unitWeight=" + unitWeight +
        ", packId=" + packId +
        ", isReal=" + isReal +
        ", isMerchandise=" + isMerchandise +
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
