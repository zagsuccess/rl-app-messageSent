package com.uhope.ancha.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class AnzhaReport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 上报人
     */
    private String issuer;
    /**
     * 上报时间
     */
    private java.util.Date date;
    /**
     * 上报问题类型
     */
    private String peoblemType;
    /**
     * 上传照片
     */
    private String image;
    /**
     * 处理时限
     */
    private String processLimited;
    /**
     * 上报问题描述
     */
    private String peoblemDescription;
    /**
     * 0 上报问题  1 检查合格
     */
    private String status;
    /**
     * 暗查暗访表的id
     */
    private String anzhaid;
    /**
     * 事件的id
     */
    private String incidentid;
    public AnzhaReport() {
        super();
    }

    public AnzhaReport(String issuer, Date date, String peoblemType, String image, String processLimited, String peoblemDescription, String status, String anzhaid, String incidentid) {
        this.issuer = issuer;
        this.date = date;
        this.peoblemType = peoblemType;
        this.image = image;
        this.processLimited = processLimited;
        this.peoblemDescription = peoblemDescription;
        this.status = status;
        this.anzhaid = anzhaid;
        this.incidentid = incidentid;
    }

    public String getProcessLimited() {
        return processLimited;
    }

    public void setProcessLimited(String processLimited) {
        this.processLimited = processLimited;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssuer() {
        return this.issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getPeoblemType() {
        return this.peoblemType;
    }

    public void setPeoblemType(String peoblemType) {
        this.peoblemType = peoblemType;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPeoblemDescription() {
        return this.peoblemDescription;
    }

    public void setPeoblemDescription(String peoblemDescription) {
        this.peoblemDescription = peoblemDescription;
    }

    public String getAnzhaid() {
        return this.anzhaid;
    }

    public void setAnzhaid(String anzhaid) {
        this.anzhaid = anzhaid;
    }

    public String getIncidentid() {
        return this.incidentid;
    }

    public void setIncidentid(String incidentid) {
        this.incidentid = incidentid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AnzhaReport{" +
                "id='" + id + '\'' +
                ", issuer='" + issuer + '\'' +
                ", date=" + date +
                ", peoblemType='" + peoblemType + '\'' +
                ", image='" + image + '\'' +
                ", processLimited='" + processLimited + '\'' +
                ", peoblemDescription='" + peoblemDescription + '\'' +
                ", status='" + status + '\'' +
                ", anzhaid='" + anzhaid + '\'' +
                ", incidentid='" + incidentid + '\'' +
                '}';
    }
}
