package com.uhope.spotcheck.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class ScSpotcheckProblem implements Serializable {
    /**
     * 抽查问题id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    /**
     * 巡查区域
     */
    private String spotcheckRegion;

    /**
     * 巡查河道
     */
    private String spotcheckRiver;

    /**
     * 抽查id
     */
    private String spotcheckId;

    /**
     * 问题扣分
     */
    private String problemDeduction;

    /**
     * 抽查问题类型名称
     */
    private String problemTypeName;

    /**
     * 问题附件字符串
     */
    private String attach;

    /**
     * 问题创建时间
     */
    private transient Date createTime;

    /**
     * 创建人id
     */
    private transient String creatorId;

    /**
     * 问题描述
     */
    private String problemDesc;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return spotcheckId
     */
    public String getSpotcheckId() {
        return spotcheckId;
    }

    /**
     * @param spotcheckId
     */
    public void setSpotcheckId(String spotcheckId) {
        this.spotcheckId = spotcheckId;
    }

    /**
     * @return problemTypeName
     */
    public String getProblemTypeName() {
        return problemTypeName;
    }

    /**
     * @param problemTypeName
     */
    public void setProblemTypeName(String problemTypeName) {
        this.problemTypeName = problemTypeName;
    }

    /**
     * @return attach
     */
    public String getAttach() {
        return attach;
    }

    /**
     * @param attach
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return creatorId
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * @param creatorId
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return problemDesc
     */
    public String getProblemDesc() {
        return problemDesc;
    }

    /**
     * @param problemDesc
     */
    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    /**
     * @return spotcheckRegion
     */
    public String getSpotcheckRegion() {
        return spotcheckRegion;
    }

    /**
     * @param spotcheckRegion
     */
    public void setSpotcheckRegion(String spotcheckRegion) {
        this.spotcheckRegion = spotcheckRegion;
    }

    /**
     * @return spotcheckRiver
     */
    public String getSpotcheckRiver() {
        return spotcheckRiver;
    }

    /**
     * @param spotcheckRiver
     */
    public void setSpotcheckRiver(String spotcheckRiver) {
        this.spotcheckRiver = spotcheckRiver;
    }

    /**
     * @return problemDeduction
     */
    public String getProblemDeduction() {
        return problemDeduction;
    }

    /**
     * @param problemDeduction
     */
    public void setProblemDeduction(String problemDeduction) {
        this.problemDeduction = problemDeduction;
    }

    @Override
    public String toString() {
        return "ScSpotcheckProblem{" +
                "id='" + id + '\'' +
                ", spotcheckRegion='" + spotcheckRegion + '\'' +
                ", spotcheckRiver='" + spotcheckRiver + '\'' +
                ", spotcheckId='" + spotcheckId + '\'' +
                ", problemDeduction='" + problemDeduction + '\'' +
                ", problemTypeName='" + problemTypeName + '\'' +
                ", attach='" + attach + '\'' +
                ", createTime=" + createTime +
                ", creatorId='" + creatorId + '\'' +
                ", problemDesc='" + problemDesc + '\'' +
                '}';
    }
}