package com.uhope.ancha.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

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
    private String describe;
    /**
     * 复查附件
     */
    private String assessory;
    /**
     * 对应通报的id
     */
    private String bulletinid;
    public AnzhaReview() {
        super();
    }
    public AnzhaReview(String id,java.util.Date reviewTime,String whether,String describe,String assessory,String bulletinid) {
        super();
        this.id = id;
        this.reviewTime = reviewTime;
        this.whether = whether;
        this.describe = describe;
        this.assessory = assessory;
        this.bulletinid = bulletinid;
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

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    @Override
    public String toString() {
        return "AnzhaReview{" +
                "id='" + id + '\'' +
                ", reviewTime=" + reviewTime +
                ", whether='" + whether + '\'' +
                ", describe='" + describe + '\'' +
                ", assessory='" + assessory + '\'' +
                ", bulletinid='" + bulletinid + '\'' +
                '}';
    }
}
