package com.uhope.duban.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class DubanFeedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 反馈时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date feedbacktime;
    /**
     * 是否完成
     */
    private String whether;
    /**
     * 反馈描述
     */
    private String description;
    /**
     * 反馈附件
     */
    private String assessory;
    /**
     * 对应通报的id
     */
    private String supervisionid;
    /**
     * 0 回复    1  处理     2  核查
     */
    private String status;

    public DubanFeedback() {
        super();
    }

    public DubanFeedback(Date feedbacktime, String whether, String description, String assessory, String supervisionid, String status) {
        this.feedbacktime = feedbacktime;
        this.whether = whether;
        this.description = description;
        this.assessory = assessory;
        this.supervisionid = supervisionid;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public java.util.Date getFeedbacktime() {
        return this.feedbacktime;
    }

    public void setFeedbacktime(java.util.Date feedbacktime) {
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

    public String getSupervisionid() {
        return this.supervisionid;
    }

    public void setSupervisionid(String supervisionid) {
        this.supervisionid = supervisionid;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DubanFeedback{" +
                "id='" + id + '\'' +
                ", feedbacktime=" + feedbacktime +
                ", whether='" + whether + '\'' +
                ", description='" + description + '\'' +
                ", assessory='" + assessory + '\'' +
                ", supervisionid='" + supervisionid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
