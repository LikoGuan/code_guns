package com.stylefeng.guns.modular.system.domain;

/**
 * Created by likoguan on 24/10/18.
 */
public enum MeasureUnit {
    Meter(1, "米"),
    Decimeter(2, "分米"),
    Centimeter(3, "厘米"),
    Millimeter(4, "毫米");

    private Integer id;

    private String desc;

    private MeasureUnit(Integer id, String desc) {
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

    public static MeasureUnit toMeasureUnit(Integer id) {
        MeasureUnit[] units = MeasureUnit.values();
        for (MeasureUnit unit : units) {
            if (unit.getId().equals(id)) {
                return unit;
            }
        }
        return null;
    }

    public static MeasureUnit toMeasureUnit(String desc) {
        MeasureUnit[] units = MeasureUnit.values();
        for (MeasureUnit unit : units) {
            if (unit.getDesc().equals(desc)) {
                return unit;
            }
        }
        return null;
    }
}
