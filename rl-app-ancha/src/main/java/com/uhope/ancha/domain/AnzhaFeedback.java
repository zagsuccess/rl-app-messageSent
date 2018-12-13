package com.uhope.ancha.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class AnzhaFeedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 反馈时间
     */
    private java.util.Date feedbacktime;
    /**
     * 需反馈区河长办的id
     */
    private String objectid;
    /**
     * 是否完成
     */
    private String whether;
    /**
     * 反馈描述
     */
    private String description;
    /**
     * 原文件路径
     */
    private String assessoryyuan;
    /**
     * 反馈附件
     */
    private String assessory;
    /**
     * 对应通报的id
     */
    private String bulletinid;
    private String status;
    public AnzhaFeedback() {
        super();
    }

    public AnzhaFeedback(Date feedbacktime, String objectid, String whether, String description, String assessoryyuan, String assessory, String bulletinid, String status) {
        this.feedbacktime = feedbacktime;
        this.objectid = objectid;
        this.whether = whether;
        this.description = description;
        this.assessoryyuan = assessoryyuan;
        this.assessory = assessory;
        this.bulletinid = bulletinid;
        this.status = status;
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

    public Date getFeedbacktime() {
        return feedbacktime;
    }

    public void setFeedbacktime(Date feedbacktime) {
        this.feedbacktime = feedbacktime;
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

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AnzhaFeedback{" +
                "id='" + id + '\'' +
                ", feedbacktime=" + feedbacktime +
                ", objectid='" + objectid + '\'' +
                ", whether='" + whether + '\'' +
                ", description='" + description + '\'' +
                ", assessoryyuan='" + assessoryyuan + '\'' +
                ", assessory='" + assessory + '\'' +
                ", bulletinid='" + bulletinid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
