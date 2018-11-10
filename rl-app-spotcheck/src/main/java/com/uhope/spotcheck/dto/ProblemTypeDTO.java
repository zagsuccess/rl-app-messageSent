package com.uhope.spotcheck.dto;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/28
 * @description:
 */
public class ProblemTypeDTO implements Serializable {
    /**
     * 问题类型id
     */
    private String problemTypeId;

    /**
     * 问题类型名称
     */
    private String problemTypeName;

    /**
     * 问题扣分方式
     */
    private String problemDecutionType;

    /**
     * 问题所扣分数
     */
    private String problemScore;

    /**
     * @return problemTypeId
     */
    public String getProblemTypeId() {
        return problemTypeId;
    }

    /**
     * @param problemTypeId
     */
    public void setProblemTypeId(String problemTypeId) {
        this.problemTypeId = problemTypeId;
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
     * @return problemDecutionType
     */
    public String getProblemDecutionType() {
        return problemDecutionType;
    }

    /**
     * @param problemDecutionType
     */
    public void setProblemDecutionType(String problemDecutionType) {
        this.problemDecutionType = problemDecutionType;
    }

    /**
     * @return problemScore
     */
    public String getProblemScore() {
        return problemScore;
    }

    /**
     * @param problemScore
     */
    public void setProblemScore(String problemScore) {
        this.problemScore = problemScore;
    }

    @Override
    public String toString() {
        return "ProblemTypeDTO{" +
                "problemTypeId='" + problemTypeId + '\'' +
                ", problemTypeName='" + problemTypeName + '\'' +
                ", problemDecutionType='" + problemDecutionType + '\'' +
                ", problemScore='" + problemScore + '\'' +
                '}';
    }
}
