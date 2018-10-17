package com.uhope.template.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * surface_water
 *
 * @author
 */
public class SewageDispose implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String title;

    private String issue;

    private Date createtime;

    private String createuser;

    private Integer status;

    private String remark;

    private static final long serialVersionUID = 1L;

    public SewageDispose() {
        super();
    }

    @Override
    public String toString() {
        return "SewageDispose{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", issue='" + issue + '\'' +
                ", createtime=" + createtime +
                ", createuser='" + createuser + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SewageDispose that = (SewageDispose) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(issue, that.issue) &&
                Objects.equals(createtime, that.createtime) &&
                Objects.equals(createuser, that.createuser) &&
                Objects.equals(status, that.status) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, issue, createtime, createuser, status, remark);
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

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public SewageDispose(String title, String issue, Date createtime, String createuser, Integer status, String remark) {
        this.title = title;
        this.issue = issue;
        this.createtime = createtime;
        this.createuser = createuser;
        this.status = status;
        this.remark = remark;
    }
}