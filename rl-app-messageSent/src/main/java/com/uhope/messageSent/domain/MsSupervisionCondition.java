package com.uhope.messageSent.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ms_supervision_condition")
public class MsSupervisionCondition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    @Column(name = "administration_region")
    private String administrationRegion;

    @Column(name = "supervise_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date superviseTime;

    @Column(name = "supervise_way")
    private String superviseWay;

    @Column(name = "supervise_content")
    private String superviseContent;

    @Column(name = "rectify_state")
    private Integer rectifyState;

    @Column(name = "lead_name")
    private String leadName;

    @Column(name = "sent_state")
    private Integer sentState;

    @Column(name="accessory_url")
    private String accessoryURL;

    @Column(name="pdf_url")
    private String pdfURL;

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
     * @return administration_region
     */
    public String getAdministrationRegion() {
        return administrationRegion;
    }

    /**
     * @param administrationRegion
     */
    public void setAdministrationRegion(String administrationRegion) {
        this.administrationRegion = administrationRegion;
    }

    /**
     * @return supervise_time
     */
    public Date getSuperviseTime() {
        return superviseTime;
    }

    /**
     * @param superviseTime
     */
    public void setSuperviseTime(Date superviseTime) {
        this.superviseTime = superviseTime;
    }

    /**
     * @return supervise_way
     */
    public String getSuperviseWay() {
        return superviseWay;
    }

    /**
     * @param superviseWay
     */
    public void setSuperviseWay(String superviseWay) {
        this.superviseWay = superviseWay;
    }

    /**
     * @return supervise_content
     */
    public String getSuperviseContent() {
        return superviseContent;
    }

    /**
     * @param superviseContent
     */
    public void setSuperviseContent(String superviseContent) {
        this.superviseContent = superviseContent;
    }

    /**
     * @return rectify_state
     */
    public Integer getRectifyState() {
        return rectifyState;
    }

    /**
     * @param rectifyState
     */
    public void setRectifyState(Integer rectifyState) {
        this.rectifyState = rectifyState;
    }

    /**
     * @return lead_name
     */
    public String getLeadName() {
        return leadName;
    }

    /**
     * @param leadName
     */
    public void setLeadName(String leadName) {
        this.leadName = leadName;
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
     * @return accessory_url
     */
    public String getAccessoryURL() {
        return accessoryURL;
    }

    /**
     * @param accessoryURL
     */
    public void setAccessoryURL(String accessoryURL) {
        this.accessoryURL = accessoryURL;
    }

    /**
     * @return pdf_url
     */
    public String getPdfURL() {
        return pdfURL;
    }

    /**
     * @param pdfURL
     */
    public void setPdfURL(String pdfURL) {
        this.pdfURL = pdfURL;
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
        MsSupervisionCondition other = (MsSupervisionCondition) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAdministrationRegion() == null ? other.getAdministrationRegion() == null : this.getAdministrationRegion().equals(other.getAdministrationRegion()))
                && (this.getSuperviseTime() == null ? other.getSuperviseTime() == null : this.getSuperviseTime().equals(other.getSuperviseTime()))
                && (this.getSuperviseWay() == null ? other.getSuperviseWay() == null : this.getSuperviseWay().equals(other.getSuperviseWay()))
                && (this.getSuperviseContent() == null ? other.getSuperviseContent() == null : this.getSuperviseContent().equals(other.getSuperviseContent()))
                && (this.getRectifyState() == null ? other.getRectifyState() == null : this.getRectifyState().equals(other.getRectifyState()))
                && (this.getLeadName() == null ? other.getLeadName() == null : this.getLeadName().equals(other.getLeadName()))
                && (this.getSentState() == null ? other.getSentState() == null : this.getSentState().equals(other.getSentState()))
                && (this.getAccessoryURL() == null ? other.getAccessoryURL() == null : this.getAccessoryURL().equals(other.getAccessoryURL()))
                && (this.getPdfURL() == null ? other.getPdfURL() == null : this.getPdfURL().equals(other.getPdfURL()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAdministrationRegion() == null) ? 0 : getAdministrationRegion().hashCode());
        result = prime * result + ((getSuperviseTime() == null) ? 0 : getSuperviseTime().hashCode());
        result = prime * result + ((getSuperviseWay() == null) ? 0 : getSuperviseWay().hashCode());
        result = prime * result + ((getSuperviseContent() == null) ? 0 : getSuperviseContent().hashCode());
        result = prime * result + ((getRectifyState() == null) ? 0 : getRectifyState().hashCode());
        result = prime * result + ((getLeadName() == null) ? 0 : getLeadName().hashCode());
        result = prime * result + ((getSentState() == null) ? 0 : getSentState().hashCode());
        result = prime * result + ((getAccessoryURL() == null) ? 0 : getAccessoryURL().hashCode());
        result = prime * result + ((getPdfURL() == null) ? 0 : getPdfURL().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", administrationRegion=").append(administrationRegion);
        sb.append(", superviseTime=").append(superviseTime);
        sb.append(", superviseWay=").append(superviseWay);
        sb.append(", superviseContent=").append(superviseContent);
        sb.append(", rectifyState=").append(rectifyState);
        sb.append(", leadName=").append(leadName);
        sb.append(", sentState=").append(sentState);
        sb.append(", accessoryURL='").append(accessoryURL);
        sb.append(", pdfURL='").append(pdfURL);                           // .append('\'')
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
