package com.uhope.bulletin.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author: StivenYang
 * @date: 2018/9/8
 * @description: 暗查暗访表-接口实现类
 */
public class Bulletin implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
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
	private String postTime;
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
    private String detailUrl;
    /**
     *状态
     */
    private Integer status;
    /**
     *附件url
     */
    private String attandUrl;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *描述
     */
    private String detail;

    public Bulletin() {
        super();
    }

    @Override
    public String toString() {
        return "Bulletin{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", issuer='" + issuer + '\'' +
                ", postTime='" + postTime + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", status=" + status +
                ", attandUrl='" + attandUrl + '\'' +
                ", createTime=" + createTime +
                ", detail='" + detail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Bulletin bulletin = (Bulletin) o;
        return Objects.equals(id, bulletin.id) &&
                Objects.equals(title, bulletin.title) &&
                Objects.equals(type, bulletin.type) &&
                Objects.equals(issuer, bulletin.issuer) &&
                Objects.equals(postTime, bulletin.postTime) &&
                Objects.equals(month, bulletin.month) &&
                Objects.equals(year, bulletin.year) &&
                Objects.equals(detailUrl, bulletin.detailUrl) &&
                Objects.equals(status, bulletin.status) &&
                Objects.equals(attandUrl, bulletin.attandUrl) &&
                Objects.equals(createTime, bulletin.createTime) &&
                Objects.equals(detail, bulletin.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, issuer, postTime, month, year, detailUrl, status, attandUrl, createTime, detail);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
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

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAttandUrl() {
        return attandUrl;
    }

    public void setAttandUrl(String attandUrl) {
        this.attandUrl = attandUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Bulletin(String title, Integer type, String issuer, String postTime, String month, String year, String detailUrl, Integer status, String attandUrl, Date createTime, String detail) {
        this.title = title;
        this.type = type;
        this.issuer = issuer;
        this.postTime = postTime;
        this.month = month;
        this.year = year;
        this.detailUrl = detailUrl;
        this.status = status;
        this.attandUrl = attandUrl;
        this.createTime = createTime;
        this.detail = detail;
    }
}