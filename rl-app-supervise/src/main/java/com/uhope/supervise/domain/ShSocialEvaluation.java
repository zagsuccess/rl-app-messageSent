package com.uhope.supervise.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ShSocialEvaluation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String termNumber;

    private String regionName;

    private String riverName;

    private String supervisor;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date evaluationDate;

    private Integer isSatisfied;

    private String dissatisfiedReason;

    private String otherReason;

    private String problemPics;

    private String problemPosition;

    private String contactType;

    private transient String creator;

    private transient Date createTime;
}