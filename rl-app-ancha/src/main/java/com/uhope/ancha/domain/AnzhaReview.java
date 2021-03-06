package com.uhope.ancha.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class AnzhaReview implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 复查时间
     */
    private java.util.Date reviewTime;
    /**
     * 是否完成
     */
    private String whether;
    /**
     * 复查描述
     */
    private String description;
    /**
     * 原文件路径
     */
    private String assessoryyuan;
    /**
     * 复查附件
     */
    private String assessory;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 对应通报的id
     */
    private String bulletinid;
    public AnzhaReview() {
        super();
    }

    public AnzhaReview(Date reviewTime, String whether, String description, String assessoryyuan, String assessory, Date createtime, String bulletinid) {
        this.reviewTime = reviewTime;
        this.whether = whether;
        this.description = description;
        this.assessoryyuan = assessoryyuan;
        this.assessory = assessory;
        this.createtime = createtime;
        this.bulletinid = bulletinid;
    }

    public String getAssessoryyuan() {
        return assessoryyuan;
    }

    public void setAssessoryyuan(String assessoryyuan) {
        this.assessoryyuan = assessoryyuan;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public java.util.Date getReviewTime() {
        return this.reviewTime;
    }

    public void setReviewTime(java.util.Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getWhether() {
        return this.whether;
    }

    public void setWhether(String whether) {
        this.whether = whether;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssessory() {
        return this.assessory;
    }

    public void setAssessory(String assessory) {
        this.assessory = assessory;
    }

    public String getBulletinid() {
        return this.bulletinid;
    }

    public void setBulletinid(String bulletinid) {
        this.bulletinid = bulletinid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "AnzhaReview{" +
                "id='" + id + '\'' +
                ", reviewTime=" + reviewTime +
                ", whether='" + whether + '\'' +
                ", description='" + description + '\'' +
                ", assessoryyuan='" + assessoryyuan + '\'' +
                ", assessory='" + assessory + '\'' +
                ", createtime=" + createtime +
                ", bulletinid='" + bulletinid + '\'' +
                '}';
    }
}
