package com.uhope.supervise.domain;

import com.alibaba.fastjson.annotation.JSONField;
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

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    /**
     * id
     */
    private String id;

    /**
     * 期号
     */
    private String termNumber;

    /**
     * 行政区域
     */
    private String regionName;

    /**
     * 水域名称
     */
    private String riverName;

    /**
     * 区级河长
     */
    private String districtChairman;

    /**
     * 镇街级河长
     */
    private String townChairman;

    /**
     * 监督员姓名
     */
    private String supervisor;

    /**
     * 评价时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date evaluationDate;

    /**
     * 是否满意
     */
    private Integer isSatisfied;

    /**
     * 不满意原因
     */
    private String dissatisfiedReason;

    /**
     * 其他说明
     */
    private String otherReason;

    /**
     * 现场照片
     */
    private String problemPics;

    /**
     * 联系方式
     */
    private String contactType;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 具体位置
     */
    private String problemPosition;
}