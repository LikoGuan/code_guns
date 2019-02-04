package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 互斥工艺表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-22
 */
@TableName("processing_excl")
public class ProcessingExcl extends Model<ProcessingExcl> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 工艺A
     */
    @TableField("procA_id")
    private Integer procaId;
    /**
     * 工艺B
     */
    @TableField("procB_id")
    private Integer procbId;
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

    public Integer getProcaId() {
        return procaId;
    }

    public void setProcaId(Integer procaId) {
        this.procaId = procaId;
    }

    public Integer getProcbId() {
        return procbId;
    }

    public void setProcbId(Integer procbId) {
        this.procbId = procbId;
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
        return "ProcessingExcl{" +
        "id=" + id +
        ", procaId=" + procaId +
        ", procbId=" + procbId +
        ", comment=" + comment +
        "}";
    }
}
