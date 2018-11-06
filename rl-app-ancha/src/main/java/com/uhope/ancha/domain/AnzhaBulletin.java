package com.uhope.ancha.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class AnzhaBulletin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 通报标题
     */
    private String title;
    /**
     * 月份
     */
    private String month;
    /**
     * 通报内容
     */
    private String content;
    /**
     * 通报附件
     */
    private String accessory;
    /**
     * 0 待通报  1 已下发  2 已反馈  3 未按期 4 已结束  5  已复查
     */
    private String status;
    /**
     * 对应 scheme表的id
     **/
    private String schemeid;
    public AnzhaBulletin() {
        super();
    }

    public AnzhaBulletin(String title, String month, String content, String accessory, String status, String schemeid) {
        this.title = title;
        this.month = month;
        this.content = content;
        this.accessory = accessory;
        this.status = status;
        this.schemeid = schemeid;
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

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccessory() {
        return this.accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    @Override
    public String toString() {
        return "AnzhaBulletin{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", month='" + month + '\'' +
                ", content='" + content + '\'' +
                ", accessory='" + accessory + '\'' +
                ", status='" + status + '\'' +
                ", schemeid='" + schemeid + '\'' +
                '}';
    }
}
