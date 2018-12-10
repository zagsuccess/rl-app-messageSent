package com.uhope.ancha.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class AnzhaScheme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 方案标题
     */
    private String title;
    /**
     * 期号
     */
    private String issue;
    /**
     * 创建人
     */
    private String createuser;
    /**
     * 内容
     */
    private String content;
    /**
     * 原文件路径
     */
    private String assessoryyuan;
    /**
     * 附件
     */
    private String assessory;
    private String bulletinid;
    public AnzhaScheme() {
        super();
    }

    public AnzhaScheme(String title, String issue, String createuser, String content, String assessoryyuan, String assessory, String bulletinid) {
        this.title = title;
        this.issue = issue;
        this.createuser = createuser;
        this.content = content;
        this.assessoryyuan = assessoryyuan;
        this.assessory = assessory;
        this.bulletinid = bulletinid;
    }

    public String getAssessoryyuan() {
        return assessoryyuan;
    }

    public void setAssessoryyuan(String assessoryyuan) {
        this.assessoryyuan = assessoryyuan;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssue() {
        return this.issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getCreateuser() {
        return this.createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAssessory() {
        return this.assessory;
    }

    public void setAssessory(String assessory) {
        this.assessory = assessory;
    }

    public String getBulletinid() {
        return bulletinid;
    }

    public void setBulletinid(String bulletinid) {
        this.bulletinid = bulletinid;
    }

    @Override
    public String toString() {
        return "AnzhaScheme{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", issue='" + issue + '\'' +
                ", createuser='" + createuser + '\'' +
                ", content='" + content + '\'' +
                ", assessoryyuan='" + assessoryyuan + '\'' +
                ", assessory='" + assessory + '\'' +
                ", bulletinid='" + bulletinid + '\'' +
                '}';
    }
}
