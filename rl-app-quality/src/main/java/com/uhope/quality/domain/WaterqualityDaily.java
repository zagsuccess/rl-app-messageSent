package com.uhope.quality.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author :shujihui
 * @Date : 2018/10/13
 * @Time : 14:45
 * Aim :日的数据
 */
@Document(collection="WaterqualityDaily")
public class WaterqualityDaily implements Serializable {
    @Id
    private String id;
    private String code;
    private String sectionname;
    private String riverName;
    @JSONField(format="yyyy-MM-dd")
    private Date statisticaltime;
    private Double wt;
    private Double totalphosphorus;
    private Double ammonium;
    private Double permanganate;
    private Double dissolvedoxygen;

    public WaterqualityDaily() {
    }

    public WaterqualityDaily(String id, String code, String sectionname, String riverName, Date statisticaltime, Double wt, Double totalphosphorus, Double ammonium, Double permanganate, Double dissolvedoxygen) {
        this.id = id;
        this.code = code;
        this.sectionname = sectionname;
        this.riverName = riverName;
        this.statisticaltime = statisticaltime;
        this.wt = wt;
        this.totalphosphorus = totalphosphorus;
        this.ammonium = ammonium;
        this.permanganate = permanganate;
        this.dissolvedoxygen = dissolvedoxygen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public Date getStatisticaltime() {
        return statisticaltime;
    }

    public void setStatisticaltime(Date statisticaltime) {
        this.statisticaltime = statisticaltime;
    }

    public Double getWt() {
        return wt;
    }

    public void setWt(Double wt) {
        this.wt = wt;
    }

    public Double getTotalphosphorus() {
        return totalphosphorus;
    }

    public void setTotalphosphorus(Double totalphosphorus) {
        this.totalphosphorus = totalphosphorus;
    }

    public Double getAmmonium() {
        return ammonium;
    }

    public void setAmmonium(Double ammonium) {
        this.ammonium = ammonium;
    }

    public Double getPermanganate() {
        return permanganate;
    }

    public void setPermanganate(Double permanganate) {
        this.permanganate = permanganate;
    }

    public Double getDissolvedoxygen() {
        return dissolvedoxygen;
    }

    public void setDissolvedoxygen(Double dissolvedoxygen) {
        this.dissolvedoxygen = dissolvedoxygen;
    }

    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    @Override
    public String toString() {
        return "WaterqualityDaily{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", sectionname='" + sectionname + '\'' +
                ", riverName='" + riverName + '\'' +
                ", statisticaltime=" + statisticaltime +
                ", wt=" + wt +
                ", totalphosphorus=" + totalphosphorus +
                ", ammonium=" + ammonium +
                ", permanganate=" + permanganate +
                ", dissolvedoxygen=" + dissolvedoxygen +
                '}';
    }
}
