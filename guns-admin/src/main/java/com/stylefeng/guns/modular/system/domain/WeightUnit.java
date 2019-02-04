package com.stylefeng.guns.modular.system.domain;

/**
 * Created by likoguan on 24/10/18.
 */
public enum WeightUnit {
    Kilogram(1, "千克"),
    Gram(2, "克");

    private Integer id;

    private String desc;

    private WeightUnit(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static WeightUnit toWeightUnit(Integer id) {
        WeightUnit[] units = WeightUnit.values();
        for (WeightUnit unit : units) {
            if (unit.getId().equals(id)) {
                return unit;
            }
        }
        return null;
    }

    public static WeightUnit toWeightUnit(String desc) {
        WeightUnit[] units = WeightUnit.values();
        for (WeightUnit unit : units) {
            if (unit.getDesc().equals(desc)) {
                return unit;
            }
        }
        return null;
    }
}
