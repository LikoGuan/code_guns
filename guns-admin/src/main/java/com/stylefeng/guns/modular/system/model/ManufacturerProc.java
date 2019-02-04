package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 生产商工艺表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-23
 */
@TableName("manufacturer_proc")
public class ManufacturerProc extends Model<ManufacturerProc> {

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
     * 工艺id
     */
    @TableField("proc_id")
    private Integer procId;


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

    public Integer getProcId() {
        return procId;
    }

    public void setProcId(Integer procId) {
        this.procId = procId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ManufacturerProc{" +
        "id=" + id +
        ", orgId=" + orgId +
        ", procId=" + procId +
        "}";
    }
}
