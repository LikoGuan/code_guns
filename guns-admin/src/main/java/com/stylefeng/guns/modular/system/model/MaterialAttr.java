package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 材料属性表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-26
 */
@TableName("material_attr")
public class MaterialAttr extends Model<MaterialAttr> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "attr_id", type = IdType.AUTO)
    private Integer attrId;
    /**
     * 材料id
     */
    @TableField("material_id")
    private Integer materialId;
    /**
     * 属性名称
     */
    @TableField("material_attr")
    private String materialAttr;
    /**
     * 备注
     */
    private String comment;


    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialAttr() {
        return materialAttr;
    }

    public void setMaterialAttr(String materialAttr) {
        this.materialAttr = materialAttr;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    protected Serializable pkVal() {
        return this.attrId;
    }

    @Override
    public String toString() {
        return "MaterialAttr{" +
        "attrId=" + attrId +
        ", materialId=" + materialId +
        ", materialAttr=" + materialAttr +
        ", comment=" + comment +
        "}";
    }
}
