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

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    /**
     * 举报时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date reportDate;

    /**
     * 问题处理超时时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date overTime;

    /**
     * 区域名称
     */
    private String regionName;

    /**
     * 河段名
     */
    private String riverName;

    /**
     * 举报人姓名
     */
    private String reportor;

    /**
     * 联系方式
     */
    private String contactType;

    /**
     * 举报问题
     */
    private String reportProblem;

    /**
     * 问题附件
     */
    private String problemAttant;

    /**
     * 处理状态
     */
    private String processingStatus;

    /**
     * 举报满意度评价
     * 1：满意
     * 2：不满意
     */
    private String reportEvaluate;

    /**
     * 举报来源
     */
    private String reportSource;

    /**
     * 问题上报位置
     */
    private String problemPosition;

    /**
     * 处理反馈意见
     */
    private String proposedTreatment;

    /**
     * 处理结果
     */
    private String processingResults;

    /**
     * 创建时间
     */
    private transient Date createTime;

    /**
     * 创建人id
     */
    private transient String creator;
}