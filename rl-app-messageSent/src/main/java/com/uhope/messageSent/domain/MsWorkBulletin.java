package com.uhope.messageSent.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 工作简报
 * @author  wanglijun
 */
@Table(name="ms_workbulletin")
public class MsWorkBulletin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String title;

    private String region;

    private String initiator;

    @Column(name="report_time")
    private String reportTime;

    private String content;

    @Column(name="accessory_url")
    private String accessoryURL;

    @Column(name="pdf_url")
    private String pdfURL;

    @Column(name="sent_state")
    private Integer sentState;

    @Column(name="accept_state")
    private Integer acceptState;

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccessoryURL() {
        return accessoryURL;
    }

    public void setAccessoryURL(String accessoryURL) {
        this.accessoryURL = accessoryURL;
    }

    public String getPdfURL() {
        return pdfURL;
    }

    public void setPdfURL(String pdfURL) {
        this.pdfURL = pdfURL;
    }

    public Integer getSentState() {
        return sentState;
    }

    public void setSentState(Integer sentState) {
        this.sentState = sentState;
    }

    public Integer getAcceptState() {
        return acceptState;
    }

    public void setAcceptState(Integer acceptState) {
        this.acceptState = acceptState;
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
        MsWorkBulletin other = (MsWorkBulletin) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
                && (this.getInitiator() == null ? other.getInitiator() == null : this.getInitiator().equals(other.getInitiator()))
                && (this.getReportTime() == null ? other.getReportTime() == null : this.getReportTime().equals(other.getReportTime()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getAccessoryURL() == null ? other.getAccessoryURL() == null : this.getAccessoryURL().equals(other.getAccessoryURL()))
                && (this.getPdfURL() == null ? other.getPdfURL() == null : this.getPdfURL().equals(other.getPdfURL()))
                && (this.getSentState() == null ? other.getSentState() == null : this.getSentState().equals(other.getSentState()))
                && (this.getAcceptState() == null ? other.getAcceptState() == null : this.getAcceptState().equals(other.getAcceptState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getInitiator() == null) ? 0 : getInitiator().hashCode());
        result = prime * result + ((getReportTime() == null) ? 0 : getReportTime().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getAccessoryURL() == null) ? 0 : getAccessoryURL().hashCode());
        result = prime * result + ((getPdfURL() == null) ? 0 : getPdfURL().hashCode());
        result = prime * result + ((getSentState() == null) ? 0 : getSentState().hashCode());
        result = prime * result + ((getAcceptState() == null) ? 0 : getAcceptState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MsWorkBulletin{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", initiator='").append(initiator).append('\'');
        sb.append(", reportTime='").append(reportTime).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", accessoryURL='").append(accessoryURL).append('\'');
        sb.append(", pdfURL='").append(pdfURL).append('\'');
        sb.append(", sentState=").append(sentState);
        sb.append(", acceptState=").append(acceptState);
        sb.append('}');
        return sb.toString();
    }
}
