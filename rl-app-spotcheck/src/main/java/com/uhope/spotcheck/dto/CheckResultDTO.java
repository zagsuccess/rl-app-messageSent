package com.uhope.spotcheck.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.uhope.spotcheck.domain.ScSpotcheckProblem;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/25
 * @description: 检查结果-最终传输类
 */
public class CheckResultDTO implements Serializable {

    /**
     * 检查时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date checkDate;

    /**
     * 上报人
     */
    private String sendPerson;
    /**
     * 问题list
     */
    private List<ScSpotcheckProblem> problemList;

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
                ", checkDate=" + checkDate +
                ", sendPerson='" + sendPerson + '\'' +
                ", problemList=" + problemList +
                '}';
    }
}
