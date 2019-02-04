package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 生产商材料表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-25
 */
@TableName("manufacturer_mat")
public class ManufacturerMat extends Model<ManufacturerMat> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 组织id
     */
    @TableField("org_id")
    private Integer orgId;
    /**
     * 材料id
     */
    @TableField("material_id")
    private Integer materialId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ManufacturerMat{" +
        "id=" + id +
        ", orgId=" + orgId +
        ", materialId=" + materialId +
        "}";
    }
}
