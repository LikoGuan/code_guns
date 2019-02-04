package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 供应商表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-22
 */
@TableName("suppliers")
public class Suppliers extends Model<Suppliers> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "suppliers_id", type = IdType.AUTO)
    private Integer suppliersId;
    /**
     * 供应商名称
     */
    @TableField("suppliers_name")
    private String suppliersName;
    /**
     * 供应商描述
     */
    @TableField("suppliers_desc")
    private String suppliersDesc;
    /**
     * 有效
     */
    @TableField("is_validate")
    private Integer isValidate;


    public Integer getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Integer suppliersId) {
        this.suppliersId = suppliersId;
    }

    public String getSuppliersName() {
        return suppliersName;
    }

    public void setSuppliersName(String suppliersName) {
        this.suppliersName = suppliersName;
    }

    public String getSuppliersDesc() {
        return suppliersDesc;
    }

    public void setSuppliersDesc(String suppliersDesc) {
        this.suppliersDesc = suppliersDesc;
    }

    public Integer getIsValidate() {
        return isValidate;
    }

    public void setIsValidate(Integer isValidate) {
        this.isValidate = isValidate;
    }

    @Override
    protected Serializable pkVal() {
        return this.suppliersId;
    }

    @Override
    public String toString() {
        return "Suppliers{" +
        "suppliersId=" + suppliersId +
        ", suppliersName=" + suppliersName +
        ", suppliersDesc=" + suppliersDesc +
        ", isValidate=" + isValidate +
        "}";
    }
}
