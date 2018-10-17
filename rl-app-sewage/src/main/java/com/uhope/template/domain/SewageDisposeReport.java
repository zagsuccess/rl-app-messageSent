package com.uhope.template.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * surface_water_grade
 *
 * @author
 */
public class SewageDisposeReport implements Serializable {
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
    private String oldcode;
    /**
     * 污水处理厂的名字
     */
    private String name;
    /**
     * 所在的行政区
     */
    private String region;
    /**
     * 考核区
     */
    private String assess;
    /**
     * 是否达标，0达标  1不达标
     */
    private Integer status;
    /**
     * 不达标的原因
     */
    private String reason;
    private String parentid;
    public SewageDisposeReport() {
        super();
    }
    public SewageDisposeReport(String id,String code,String oldcode,String name,String region,String assess,Integer status,String reason,String parentid) {
        super();
        this.id = id;
        this.code = code;
        this.oldcode = oldcode;
        this.name = name;
        this.region = region;
        this.assess = assess;
        this.status = status;
        this.reason = reason;
        this.parentid = parentid;
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOldcode() {
        return this.oldcode;
    }

    public void setOldcode(String oldcode) {
        this.oldcode = oldcode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAssess() {
        return this.assess;
    }

    public void setAssess(String assess) {
        this.assess = assess;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getParentid() {
        return this.parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "SewageDisposeReport{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", oldcode='" + oldcode + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", assess='" + assess + '\'' +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                ", parentid='" + parentid + '\'' +
                '}';
    }
}