package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-10
 */
@TableName("manufacturer_rank")
public class ManufacturerRank extends Model<ManufacturerRank> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 生产商编号
     */
    @TableField("manufacturer_id")
    private Integer manufacturerId;
    /**
     * 名称
     */
    @TableField("rank_name")
    private String rankName;
    /**
     * 制造商等级
     */
    private Integer rank;
    /**
     * 生产商类别Id
     */
    @TableField("type_id")
    private Integer typeId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ManufacturerRank{" +
        "id=" + id +
        ", manufacturerId=" + manufacturerId +
        ", rankName=" + rankName +
        ", rank=" + rank +
        ", typeId=" + typeId +
        "}";
    }
}
