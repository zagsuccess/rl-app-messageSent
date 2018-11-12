package com.uhope.ancha.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class AnzhaStandingBook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 约谈日期
     */
    private java.util.Date turnarounddate;
    /**
     * 约谈内容
     */
    private String content;
    /**
     * 约谈对象
     */
    private String object;
    /**
     * 姓名
     */
    private String name;
    /**
     * 职务
     */
    private String duty;
    /**
     * 问责类型
     */
    private String accountabilitytype;
    /**
     * 附件
     */
    private String accessory;
    /**
     * 备注
     */
    private String remark;
    /**
     * 对应通报的id
     */
    private String bulletinid;
    public AnzhaStandingBook() {
        super();
    }

    public AnzhaStandingBook(Date turnarounddate, String content, String object, String name, String duty, String accountabilitytype, String accessory, String remark, String bulletinid) {
        this.turnarounddate = turnarounddate;
        this.content = content;
        this.object = object;
        this.name = name;
        this.duty = duty;
        this.accountabilitytype = accountabilitytype;
        this.accessory = accessory;
        this.remark = remark;
        this.bulletinid = bulletinid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public java.util.Date getTurnarounddate() {
        return this.turnarounddate;
    }

    public void setTurnarounddate(java.util.Date turnarounddate) {
        this.turnarounddate = turnarounddate;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getObject() {
        return this.object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return this.duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getAccountabilitytype() {
        return this.accountabilitytype;
    }

    public void setAccountabilitytype(String accountabilitytype) {
        this.accountabilitytype = accountabilitytype;
    }

    public String getAccessory() {
        return this.accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBulletinid() {
        return bulletinid;
    }

    public void setBulletinid(String bulletinid) {
        this.bulletinid = bulletinid;
    }

    @Override
    public String toString() {
        return "AnzhaStandingBook{" +
                "id='" + id + '\'' +
                ", turnarounddate=" + turnarounddate +
                ", content='" + content + '\'' +
                ", object='" + object + '\'' +
                ", name='" + name + '\'' +
                ", duty='" + duty + '\'' +
                ", accountabilitytype='" + accountabilitytype + '\'' +
                ", accessory='" + accessory + '\'' +
                ", remark='" + remark + '\'' +
                ", bulletinid='" + bulletinid + '\'' +
                '}';
    }
}
