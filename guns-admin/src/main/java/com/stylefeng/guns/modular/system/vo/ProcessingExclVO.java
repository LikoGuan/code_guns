package com.stylefeng.guns.modular.system.vo;

/**
 * Created by likoguan on 22/10/18.
 */
public class ProcessingExclVO {
    /**
     * id
     */
    private Integer id;

    /**
     * 工艺A id
     */
    private Integer procAId;

    /**
     * 工艺A name
     */
    private String procAName;

    /**
     * 工艺B id
     */
    private Integer procBId;

    /**
     * 工艺B name
     */
    private String procBName;

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

    public Integer getProcAId() {
        return procAId;
    }

    public void setProcAId(Integer procAId) {
        this.procAId = procAId;
    }

    public String getProcAName() {
        return procAName;
    }

    public void setProcAName(String procAName) {
        this.procAName = procAName;
    }

    public Integer getProcBId() {
        return procBId;
    }

    public void setProcBId(Integer procBId) {
        this.procBId = procBId;
    }

    public String getProcBName() {
        return procBName;
    }

    public void setProcBName(String procBName) {
        this.procBName = procBName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
