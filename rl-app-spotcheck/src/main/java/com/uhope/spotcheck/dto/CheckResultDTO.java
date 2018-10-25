package com.uhope.spotcheck.dto;

import com.uhope.spotcheck.domain.ScSpotcheckProblem;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/25
 * @description:
 */
public class CheckResultDTO implements Serializable {

    //扣分结果
    private Integer deductionResult;
    //检查时间
    private Date checkDate;
    //上报人
    private String sendPerson;
    //问题list
    private List<ScSpotcheckProblem> problemList;

    /**
     * @return deductionResult
     */
    public Integer getDeductionResult() {
        return deductionResult;
    }

    /**
     * @param deductionResult
     */
    public void setDeductionResult(Integer deductionResult) {
        this.deductionResult = deductionResult;
    }

    /**
     * @return checkDate
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * @param checkDate
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * @return sendPerson
     */
    public String getSendPerson() {
        return sendPerson;
    }

    /**
     * @param sendPerson
     */
    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
    }

    /**
     * @return problemList
     */
    public List<ScSpotcheckProblem> getProblemList() {
        return problemList;
    }

    /**
     * @param problemList
     */
    public void setProblemList(List<ScSpotcheckProblem> problemList) {
        this.problemList = problemList;
    }

    @Override
    public String toString() {
        return "CheckResultDTO{" +
                "deductionResult=" + deductionResult +
                ", checkDate=" + checkDate +
                ", sendPerson='" + sendPerson + '\'' +
                ", problemList=" + problemList +
                '}';
    }
}
