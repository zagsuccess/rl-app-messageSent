package com.uhope.quality.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * surface_water
 *
 * @author
 */
public class WaterQuality implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String title;

    private String BATCHNumber;

    private String issue;

    private Date createtime;

    private String createuser;

    private Integer status;

    private String remark;

    private static final long serialVersionUID = 1L;

    public WaterQuality() {
        super();
    }

    public WaterQuality(String title, String BATCHNumber, String issue, Date createtime, String createuser, Integer status, String remark) {
        this.title = title;
        this.BATCHNumber = BATCHNumber;
        this.issue = issue;
        this.createtime = createtime;
        this.createuser = createuser;
        this.status = status;
        this.remark = remark;
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

    public String getBATCHNumber() {
        return BATCHNumber;
    }

    public void setBATCHNumber(String BATCHNumber) {
        this.BATCHNumber = BATCHNumber;
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

    @Override
    public String toString() {
        return "WaterQuality{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", BATCHNumber='" + BATCHNumber + '\'' +
                ", issue='" + issue + '\'' +
                ", createtime=" + createtime +
                ", createuser='" + createuser + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}