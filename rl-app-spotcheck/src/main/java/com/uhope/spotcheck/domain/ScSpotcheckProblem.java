package com.uhope.spotcheck.domain;

import com.google.common.base.Objects;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class ScSpotcheckProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String spotcheckId;

    private String problemTypeId;

    private String attach;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String creatorId;

    private String problemDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSpotcheckId() {
        return spotcheckId;
    }

    public void setSpotcheckId(String spotcheckId) {
        this.spotcheckId = spotcheckId == null ? null : spotcheckId.trim();
    }

    public String getProblemTypeId() {
        return problemTypeId;
    }

    public void setProblemTypeId(String problemTypeId) {
        this.problemTypeId = problemTypeId == null ? null : problemTypeId.trim();
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach == null ? null : attach.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc == null ? null : problemDesc.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScSpotcheckProblem that = (ScSpotcheckProblem) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(spotcheckId, that.spotcheckId) &&
                Objects.equal(problemTypeId, that.problemTypeId) &&
                Objects.equal(attach, that.attach) &&
                Objects.equal(createTime, that.createTime) &&
                Objects.equal(creatorId, that.creatorId) &&
                Objects.equal(problemDesc, that.problemDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, spotcheckId, problemTypeId, attach, createTime, creatorId, problemDesc);
    }

    @Override
    public String toString() {
        return "ScSpotcheckProblem{" +
                "id='" + id + '\'' +
                ", spotcheckId='" + spotcheckId + '\'' +
                ", problemTypeId='" + problemTypeId + '\'' +
                ", attach='" + attach + '\'' +
                ", createTime=" + createTime +
                ", creatorId='" + creatorId + '\'' +
                ", problemDesc='" + problemDesc + '\'' +
                '}';
    }
}