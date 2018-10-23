package com.uhope.assessment.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class IllegalXize implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 评分规则
     */
    private String gradeillegal;
    /**
     * 对应考核评分表的评分类型
     */
    private String gradetype;
    /**
     * 评分细则
     */
    private String gradedetailed;
    /**
     * 评分方式
     */
    private String gradeway;
    /**
     * 扣分
     */
    private String deductMarks;
    /**
     * 处理时限
     */
    private String processLimitted;
    /**
     * 级别
     */
    private Integer level;
    private String parentid;
    public IllegalXize() {
        super();
    }

    public IllegalXize(String gradeillegal, String gradetype, String gradedetailed, String gradeway, String deductMarks, String processLimitted, Integer level, String parentid) {
        this.gradeillegal = gradeillegal;
        this.gradetype = gradetype;
        this.gradedetailed = gradedetailed;
        this.gradeway = gradeway;
        this.deductMarks = deductMarks;
        this.processLimitted = processLimitted;
        this.level = level;
        this.parentid = parentid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGradeillegal() {
        return this.gradeillegal;
    }

    public void setGradeillegal(String gradeillegal) {
        this.gradeillegal = gradeillegal;
    }

    public String getGradetype() {
        return this.gradetype;
    }

    public void setGradetype(String gradetype) {
        this.gradetype = gradetype;
    }

    public String getGradedetailed() {
        return this.gradedetailed;
    }

    public void setGradedetailed(String gradedetailed) {
        this.gradedetailed = gradedetailed;
    }

    public String getGradeway() {
        return this.gradeway;
    }

    public void setGradeway(String gradeway) {
        this.gradeway = gradeway;
    }

    public String getDeductMarks() {
        return this.deductMarks;
    }

    public void setDeductMarks(String deductMarks) {
        this.deductMarks = deductMarks;
    }

    public String getProcessLimitted() {
        return this.processLimitted;
    }

    public void setProcessLimitted(String processLimitted) {
        this.processLimitted = processLimitted;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentid() {
        return this.parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "IllegalXize{" +
                "id='" + id + '\'' +
                ", gradeillegal='" + gradeillegal + '\'' +
                ", gradetype='" + gradetype + '\'' +
                ", gradedetailed='" + gradedetailed + '\'' +
                ", gradeway='" + gradeway + '\'' +
                ", deductMarks='" + deductMarks + '\'' +
                ", processLimitted='" + processLimitted + '\'' +
                ", level=" + level +
                ", parentid='" + parentid + '\'' +
                '}';
    }
}
