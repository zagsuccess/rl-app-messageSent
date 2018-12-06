package com.uhope.messageSent.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "ms_sent_reports")
public class MsSentReports implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    @Column(name = "report_id")
    private String reportId;

    private String title;

    private String region;

    private String deadline;

    private String initiator;

    @Column(name = "accessory_url")
    private String accessoryUrl;

    @Column(name = "sent_state")
    private Integer sentState;

    @Column(name = "begin_time")
    private String beginTime;

    @Column(name = "accept_state")
    private Integer acceptState;

    @Column(name = "reply_state")
    private Integer replyState;

    @Column(name = "brief_description")
    private String briefDescription;

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
     * @return report_id
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * @param reportId
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return deadline
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * @param deadline
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * @return initiator
     */
    public String getInitiator() {
        return initiator;
    }

    /**
     * @param initiator
     */
    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    /**
     * @return accessory_url
     */
    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    /**
     * @param accessoryUrl
     */
    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
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
     * @return accept_state
     */
    public Integer getAcceptState() {
        return acceptState;
    }

    /**
     * @param acceptState
     */
    public void setAcceptState(Integer acceptState) {
        this.acceptState = acceptState;
    }

    /**
     * @return brief_description
     */
    public String getBriefDescription() {
        return briefDescription;
    }

    /**
     * @param briefDescription
     */
    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
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
        MsSentReports other = (MsSentReports) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getReportId() == null ? other.getReportId() == null : this.getReportId().equals(other.getReportId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getDeadline() == null ? other.getDeadline() == null : this.getDeadline().equals(other.getDeadline()))
            && (this.getInitiator() == null ? other.getInitiator() == null : this.getInitiator().equals(other.getInitiator()))
            && (this.getAccessoryUrl() == null ? other.getAccessoryUrl() == null : this.getAccessoryUrl().equals(other.getAccessoryUrl()))
            && (this.getSentState() == null ? other.getSentState() == null : this.getSentState().equals(other.getSentState()))
            && (this.getAcceptState() == null ? other.getAcceptState() == null : this.getAcceptState().equals(other.getAcceptState()))
            && (this.getBriefDescription() == null ? other.getBriefDescription() == null : this.getBriefDescription().equals(other.getBriefDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getReportId() == null) ? 0 : getReportId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getDeadline() == null) ? 0 : getDeadline().hashCode());
        result = prime * result + ((getInitiator() == null) ? 0 : getInitiator().hashCode());
        result = prime * result + ((getAccessoryUrl() == null) ? 0 : getAccessoryUrl().hashCode());
        result = prime * result + ((getSentState() == null) ? 0 : getSentState().hashCode());
        result = prime * result + ((getAcceptState() == null) ? 0 : getAcceptState().hashCode());
        result = prime * result + ((getBriefDescription() == null) ? 0 : getBriefDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reportId=").append(reportId);
        sb.append(", title=").append(title);
        sb.append(", region=").append(region);
        sb.append(", deadline=").append(deadline);
        sb.append(", initiator=").append(initiator);
        sb.append(", accessoryUrl=").append(accessoryUrl);
        sb.append(", sentState=").append(sentState);
        sb.append(", acceptState=").append(acceptState);
        sb.append(", briefDescription=").append(briefDescription);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getReplyState() {
        return replyState;
    }

    public void setReplyState(Integer replyState) {
        this.replyState = replyState;
    }
}