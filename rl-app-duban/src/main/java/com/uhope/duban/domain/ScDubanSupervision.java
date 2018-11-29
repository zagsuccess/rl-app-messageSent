package com.uhope.duban.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class ScDubanSupervision implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 督办项目
     */
    private String project;
    /**
     * 督办类型
     */
    private String type;
    /**
     * 下发日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private java.util.Date issuedtime;
    /**
     * 期限日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private java.util.Date deadlinedate;
    /**
     * 督办原因
     */
    private String reason;
    /**
     * 督办对象id
     */
    private String objectid;
    /**
     * 督办对象名字
     */
    private String objectname;
    /**
     * 附件
     */
    private String assessory;
    /**
     * 附件描述
     */
    private String assessorydescribe;
    /**
     * 状态
     */
    private String status;
    public ScDubanSupervision() {
        super();
    }

    public ScDubanSupervision(String title, String project, String type, Date issuedtime, Date deadlinedate, String reason, String objectid, String objectname, String assessory, String assessorydescribe, String status) {
        this.title = title;
        this.project = project;
        this.type = type;
        this.issuedtime = issuedtime;
        this.deadlinedate = deadlinedate;
        this.reason = reason;
        this.objectid = objectid;
        this.objectname = objectname;
        this.assessory = assessory;
        this.assessorydescribe = assessorydescribe;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public java.util.Date getIssuedtime() {
        return this.issuedtime;
    }

    public void setIssuedtime(java.util.Date issuedtime) {
        this.issuedtime = issuedtime;
    }

    public java.util.Date getDeadlinedate() {
        return this.deadlinedate;
    }

    public void setDeadlinedate(java.util.Date deadlinedate) {
        this.deadlinedate = deadlinedate;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getObjectid() {
        return this.objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getObjectname() {
        return this.objectname;
    }

    public void setObjectname(String objectname) {
        this.objectname = objectname;
    }

    public String getAssessory() {
        return this.assessory;
    }

    public void setAssessory(String assessory) {
        this.assessory = assessory;
    }

    public String getAssessorydescribe() {
        return this.assessorydescribe;
    }

    public void setAssessorydescribe(String assessorydescribe) {
        this.assessorydescribe = assessorydescribe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ScDubanSupervision{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", project='" + project + '\'' +
                ", type='" + type + '\'' +
                ", issuedtime=" + issuedtime +
                ", deadlinedate=" + deadlinedate +
                ", reason='" + reason + '\'' +
                ", objectid='" + objectid + '\'' +
                ", objectname='" + objectname + '\'' +
                ", assessory='" + assessory + '\'' +
                ", assessorydescribe='" + assessorydescribe + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
