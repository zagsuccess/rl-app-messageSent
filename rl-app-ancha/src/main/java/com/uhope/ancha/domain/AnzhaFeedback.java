package com.uhope.ancha.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class AnzhaFeedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 反馈时间
     */
    private java.util.Date feedbackTime;
    /**
     * 是否完成
     */
    private String whether;
    /**
     * 反馈描述
     */
    private String describe;
    /**
     * 反馈附件
     */
    private String assessory;
    /**
     * 对应通报的id
     */
    private String bulletinid;
    public AnzhaFeedback() {
        super();
    }
    public AnzhaFeedback(String id,java.util.Date feedbackTime,String whether,String describe,String assessory,String bulletinid) {
        super();
        this.id = id;
        this.feedbackTime = feedbackTime;
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

    public java.util.Date getFeedbackTime() {
        return this.feedbackTime;
    }

    public void setFeedbackTime(java.util.Date feedbackTime) {
        this.feedbackTime = feedbackTime;
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
        return "AnzhaFeedback{" +
                "id='" + id + '\'' +
                ", feedbackTime=" + feedbackTime +
                ", whether='" + whether + '\'' +
                ", describe='" + describe + '\'' +
                ", assessory='" + assessory + '\'' +
                ", bulletinid='" + bulletinid + '\'' +
                '}';
    }
}
