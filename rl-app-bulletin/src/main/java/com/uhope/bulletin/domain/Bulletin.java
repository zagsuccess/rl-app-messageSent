package com.uhope.bulletin.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author: StivenYang
 * @date: 2018/9/8
 * @description: 信息管理表-接口实现类
 */
public class Bulletin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     *标题
     */
    private String title;
    /**
     * 类型
     */
    private Integer type;
    /**
     *发布人
     */
    private String issuer;
    /**
     *发布时间
     */
	private String post_time;
    /**
     *月份
     */
    private String month;
    /**
     *年份
     */
    private String year;
    /**
     *静态页面url
     */
    private String detail_url;
    /**
     *状态
     */
    private Integer status;
    /**
     *附件url
     */
    private String attand_url;
    /**
     *创建时间
     */
    private Date create_time;
    /**
     *描述
     */
    private String detail;

    public Bulletin() {
        super();
    }

    public Bulletin(String title, Integer type, String issuer, String post_time, String month, String year, String detail_url, Integer status, String attand_url, Date create_time, String detail) {
        this.title = title;
        this.type = type;
        this.issuer = issuer;
        this.post_time = post_time;
        this.month = month;
        this.year = year;
        this.detail_url = detail_url;
        this.status = status;
        this.attand_url = attand_url;
        this.create_time = create_time;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAttand_url() {
        return attand_url;
    }

    public void setAttand_url(String attand_url) {
        this.attand_url = attand_url;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Bulletin{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", issuer='" + issuer + '\'' +
                ", post_time='" + post_time + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", detail_url='" + detail_url + '\'' +
                ", status=" + status +
                ", attand_url='" + attand_url + '\'' +
                ", create_time=" + create_time +
                ", detail='" + detail + '\'' +
                '}';
    }
}