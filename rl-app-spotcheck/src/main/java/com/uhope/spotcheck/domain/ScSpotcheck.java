package com.uhope.spotcheck.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.base.Objects;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class ScSpotcheck implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String title;

    private String regionCode;

    private String regionName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date checkDate;

    private String checkRiver;

    private Integer taskType;

    private String sendPersonId;

    private String sendPerson;

    private Integer status;

    private transient String creator;

    private transient Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckRiver() {
        return checkRiver;
    }

    public void setCheckRiver(String checkRiver) {
        this.checkRiver = checkRiver == null ? null : checkRiver.trim();
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getSendPerson() {
        return sendPerson;
    }

    public String getSendPersonId() {
        return sendPersonId;
    }

    public void setSendPersonId(String sendPersonId) {
        this.sendPersonId = sendPersonId;
    }

    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson == null ? null : sendPerson.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ScSpotcheck{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", regionName='" + regionName + '\'' +
                ", checkDate=" + checkDate +
                ", checkRiver='" + checkRiver + '\'' +
                ", taskType=" + taskType +
                ", sendPerson='" + sendPerson + '\'' +
                ", status=" + status +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        ScSpotcheck that = (ScSpotcheck) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(title, that.title) &&
                Objects.equal(regionCode, that.regionCode) &&
                Objects.equal(regionName, that.regionName) &&
                Objects.equal(checkDate, that.checkDate) &&
                Objects.equal(checkRiver, that.checkRiver) &&
                Objects.equal(taskType, that.taskType) &&
                Objects.equal(sendPerson, that.sendPerson) &&
                Objects.equal(status, that.status) &&
                Objects.equal(creator, that.creator) &&
                Objects.equal(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, regionCode, regionName, checkDate, checkRiver, taskType, sendPerson, status, creator, createTime);
    }
}