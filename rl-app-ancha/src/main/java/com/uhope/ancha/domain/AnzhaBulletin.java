package com.uhope.ancha.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class AnzhaBulletin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 通报标题
     */
    private String title;
    /**
     * 下发时间
     */
    private Date issuetime;
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
     * 需反馈区域id
     */
    private String feedbackareaid;
    /**
     * 需反馈区域名字
     */
    private String feedbackareaname;
    /**
     * 需反馈时间
     */
    private Date deadlinetime;
    /**
     * 对应 scheme表的id
     **/
    private String schemeid;
    public AnzhaBulletin() {
        super();
    }

    public AnzhaBulletin(String title, Date issuetime, String month, String content, String accessory, String status, String feedbackareaid, String feedbackareaname, Date deadlinetime, String schemeid) {
        this.title = title;
        this.issuetime = issuetime;
        this.month = month;
        this.content = content;
        this.accessory = accessory;
        this.status = status;
        this.feedbackareaid = feedbackareaid;
        this.feedbackareaname = feedbackareaname;
        this.deadlinetime = deadlinetime;
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

    public String getFeedbackareaid() {
        return feedbackareaid;
    }

    public void setFeedbackareaid(String feedbackareaid) {
        this.feedbackareaid = feedbackareaid;
    }

    public String getFeedbackareaname() {
        return feedbackareaname;
    }

    public void setFeedbackareaname(String feedbackareaname) {
        this.feedbackareaname = feedbackareaname;
    }

    public Date getDeadlinetime() {
        return deadlinetime;
    }

    public void setDeadlinetime(Date deadlinetime) {
        this.deadlinetime = deadlinetime;
    }

    public Date getIssuetime() {
        return issuetime;
    }

    public void setIssuetime(Date issuetime) {
        this.issuetime = issuetime;
    }

    @Override
    public String toString() {
        return "AnzhaBulletin{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", issuetime=" + issuetime +
                ", month='" + month + '\'' +
                ", content='" + content + '\'' +
                ", accessory='" + accessory + '\'' +
                ", status='" + status + '\'' +
                ", feedbackareaid='" + feedbackareaid + '\'' +
                ", feedbackareaname='" + feedbackareaname + '\'' +
                ", deadlinetime=" + deadlinetime +
                ", schemeid='" + schemeid + '\'' +
                '}';
    }
}
