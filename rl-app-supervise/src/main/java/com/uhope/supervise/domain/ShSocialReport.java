package com.uhope.supervise.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/25
 * @description: 检查结果-最终传输类
 */
@Data
public class ShSocialReport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date reportDate;

    private String regionName;

    private String riverName;

    private String reportor;

    private String contactType;

    private String reportProblem;

    private String problemAttant;

    private String processingStatus;

    private String reportEvaluate;

    private String reportSource;

    private String problemPosition;

    private String proposedTreatment;

    private String processingResults;

    private transient Date createTime;

    private transient String creator;
}