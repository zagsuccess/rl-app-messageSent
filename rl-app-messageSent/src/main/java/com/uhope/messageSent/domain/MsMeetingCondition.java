package com.uhope.messageSent.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ms_meeting_condition")
public class MsMeetingCondition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String region;

    @Column(name = "compere_role")
    private String compereRole;

    @Column(name = "compere_name")
    private String compereName;

    private String duty;

    private String category;

    @Column(name = "meeting_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date meetingTime;

    private String topic;

    @Column(name = "sent_state")
    private Integer sentState;

    private String content;

    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return compere_role
     */
    public String getCompereRole() {
        return compereRole;
    }

    /**
     * @param compereRole
     */
    public void setCompereRole(String compereRole) {
        this.compereRole = compereRole;
    }

    /**
     * @return compere_name
     */
    public String getCompereName() {
        return compereName;
    }

    /**
     * @param compereName
     */
    public void setCompereName(String compereName) {
        this.compereName = compereName;
    }

    /**
     * @return duty
     */
    public String getDuty() {
        return duty;
    }

    /**
     * @param duty
     */
    public void setDuty(String duty) {
        this.duty = duty;
    }

    /**
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return meeting_time
     */
    public Date getMeetingTime() {
        return meetingTime;
    }

    /**
     * @param meetingTime
     */
    public void setMeetingTime(Date meetingTime) {
        this.meetingTime = meetingTime;
    }

    /**
     * @return topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return sent_state
     */
    public Integer getSentState() {
        return sentState;
    }

    /**
     * @param sentState
     */
    public void setSentState(Integer sentState) {
        this.sentState = sentState;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MsMeetingCondition other = (MsMeetingCondition) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getCompereRole() == null ? other.getCompereRole() == null : this.getCompereRole().equals(other.getCompereRole()))
            && (this.getCompereName() == null ? other.getCompereName() == null : this.getCompereName().equals(other.getCompereName()))
            && (this.getDuty() == null ? other.getDuty() == null : this.getDuty().equals(other.getDuty()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getMeetingTime() == null ? other.getMeetingTime() == null : this.getMeetingTime().equals(other.getMeetingTime()))
            && (this.getTopic() == null ? other.getTopic() == null : this.getTopic().equals(other.getTopic()))
            && (this.getSentState() == null ? other.getSentState() == null : this.getSentState().equals(other.getSentState()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getCompereRole() == null) ? 0 : getCompereRole().hashCode());
        result = prime * result + ((getCompereName() == null) ? 0 : getCompereName().hashCode());
        result = prime * result + ((getDuty() == null) ? 0 : getDuty().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getMeetingTime() == null) ? 0 : getMeetingTime().hashCode());
        result = prime * result + ((getTopic() == null) ? 0 : getTopic().hashCode());
        result = prime * result + ((getSentState() == null) ? 0 : getSentState().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", region=").append(region);
        sb.append(", compereRole=").append(compereRole);
        sb.append(", compereName=").append(compereName);
        sb.append(", duty=").append(duty);
        sb.append(", category=").append(category);
        sb.append(", meetingTime=").append(meetingTime);
        sb.append(", topic=").append(topic);
        sb.append(", sentState=").append(sentState);
        sb.append(", content=").append(content);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}