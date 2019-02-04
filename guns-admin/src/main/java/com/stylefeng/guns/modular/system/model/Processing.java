package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 工艺表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-22
 */
@TableName("processing")
public class Processing extends Model<Processing> {

    private static final long serialVersionUID = 1L;

    /**
     * 工艺id
     */
    @TableId(value = "proc_id", type = IdType.AUTO)
    private Integer procId;
    /**
     * 工艺名称
     */
    private String name;
    /**
     * 工艺描述
     */
    @TableField("proc_desc")
    private String procDesc;
    /**
     * 备注
     */
    private String comment;


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

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    protected Serializable pkVal() {
        return this.procId;
    }

    @Override
    public String toString() {
        return "Processing{" +
        "procId=" + procId +
        ", name=" + name +
        ", procDesc=" + procDesc +
        ", comment=" + comment +
        "}";
    }
}
