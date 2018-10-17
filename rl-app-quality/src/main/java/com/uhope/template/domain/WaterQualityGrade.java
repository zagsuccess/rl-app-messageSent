package com.uhope.template.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class WaterQualityGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 编号
     */
    private String code;
    /**
     * 客户原来的编号
     */
    private String oldCode;
    /**
     * 断面名称
     */
    private String name;
    /**
     * 河流
     */
    private String riverName;
    /**
     * 采样日期
     */
    @JSONField(format="yyyy-MM-dd")
    private Date samplingTime;
    /**
     * 水温
     */
    private Double water_temperature;
    /**
     * 总磷
     */
    private Double total_phosphorus;
    /**
     * 氨氮
     */
    private Double ammonia_nitrogen;
    /**
     * 高锰酸盐指数
     */
    private Double permanganate_index;
    /**
     * 溶解氧
     */
    private Double DO;
    private String parentId;
    public WaterQualityGrade() {
        super();
    }

    public WaterQualityGrade(String code, String oldCode, String name, String riverName, Date samplingTime, Double water_temperature, Double total_phosphorus, Double ammonia_nitrogen, Double permanganate_index, Double DO, String parentId) {
        this.code = code;
        this.oldCode = oldCode;
        this.name = name;
        this.riverName = riverName;
        this.samplingTime = samplingTime;
        this.water_temperature = water_temperature;
        this.total_phosphorus = total_phosphorus;
        this.ammonia_nitrogen = ammonia_nitrogen;
        this.permanganate_index = permanganate_index;
        this.DO = DO;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public Date getSamplingTime() {
        return samplingTime;
    }

    public void setSamplingTime(Date samplingTime) {
        this.samplingTime = samplingTime;
    }

    public Double getWater_temperature() {
        return water_temperature;
    }

    public void setWater_temperature(Double water_temperature) {
        this.water_temperature = water_temperature;
    }

    public Double getTotal_phosphorus() {
        return total_phosphorus;
    }

    public void setTotal_phosphorus(Double total_phosphorus) {
        this.total_phosphorus = total_phosphorus;
    }

    public Double getAmmonia_nitrogen() {
        return ammonia_nitrogen;
    }

    public void setAmmonia_nitrogen(Double ammonia_nitrogen) {
        this.ammonia_nitrogen = ammonia_nitrogen;
    }

    public Double getDO() {
        return DO;
    }

    public void setDO(Double DO) {
        this.DO = DO;
    }

    public Double getPermanganate_index() {
        return permanganate_index;
    }

    public void setPermanganate_index(Double permanganate_index) {
        this.permanganate_index = permanganate_index;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    @Override
    public String toString() {
        return "WaterQualityGrade{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", oldCode='" + oldCode + '\'' +
                ", name='" + name + '\'' +
                ", riverName='" + riverName + '\'' +
                ", samplingTime='" + samplingTime + '\'' +
                ", water_temperature=" + water_temperature +
                ", total_phosphorus=" + total_phosphorus +
                ", ammonia_nitrogen=" + ammonia_nitrogen +
                ", permanganate_index=" + permanganate_index +
                ", DO=" + DO +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
