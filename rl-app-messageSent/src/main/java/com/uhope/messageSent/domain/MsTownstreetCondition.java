package com.uhope.messageSent.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ms_townstreet_condition")
public class MsTownstreetCondition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    @Column(name = "administration_region")
    private String administrationRegion;

    @Column(name = "administration_level")
    private String administrationLevel;

    @Column(name = "information_system")
    private Integer informationSystem;

    @Column(name = "sent_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date sentTime;

    @Column(name = "execute_circumstance")
    private String executeCircumstance;

    private String remark;

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
     * @return administration_level
     */
    public String getAdministrationLevel() {
        return administrationLevel;
    }

    /**
     * @param administrationLevel
     */
    public void setAdministrationLevel(String administrationLevel) {
        this.administrationLevel = administrationLevel;
    }

    /**
     * @return information_system
     */
    public Integer getInformationSystem() {
        return informationSystem;
    }

    /**
     * @param informationSystem
     */
    public void setInformationSystem(Integer informationSystem) {
        this.informationSystem = informationSystem;
    }

    /**
     * @return sent_time
     */
    public Date getSentTime() {
        return sentTime;
    }

    /**
     * @param sentTime
     */
    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    /**
     * @return execute_circumstance
     */
    public String getExecuteCircumstance() {
        return executeCircumstance;
    }

    /**
     * @param executeCircumstance
     */
    public void setExecuteCircumstance(String executeCircumstance) {
        this.executeCircumstance = executeCircumstance;
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
        MsTownstreetCondition other = (MsTownstreetCondition) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAdministrationRegion() == null ? other.getAdministrationRegion() == null : this.getAdministrationRegion().equals(other.getAdministrationRegion()))
                && (this.getAdministrationLevel() == null ? other.getAdministrationLevel() == null : this.getAdministrationLevel().equals(other.getAdministrationLevel()))
                && (this.getInformationSystem() == null ? other.getInformationSystem() == null : this.getInformationSystem().equals(other.getInformationSystem()))
                && (this.getSentTime() == null ? other.getSentTime() == null : this.getSentTime().equals(other.getSentTime()))
                && (this.getExecuteCircumstance() == null ? other.getExecuteCircumstance() == null : this.getExecuteCircumstance().equals(other.getExecuteCircumstance()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
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
        result = prime * result + ((getAdministrationLevel() == null) ? 0 : getAdministrationLevel().hashCode());
        result = prime * result + ((getInformationSystem() == null) ? 0 : getInformationSystem().hashCode());
        result = prime * result + ((getSentTime() == null) ? 0 : getSentTime().hashCode());
        result = prime * result + ((getExecuteCircumstance() == null) ? 0 : getExecuteCircumstance().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
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
        sb.append(", administrationLevel=").append(administrationLevel);
        sb.append(", informationSystem=").append(informationSystem);
        sb.append(", sentTime=").append(sentTime);
        sb.append(", executeCircumstance=").append(executeCircumstance);
        sb.append(", remark=").append(remark);
        sb.append(", sentState=").append(sentState);
        sb.append(", accessoryURL='").append(accessoryURL);
        sb.append(", pdfURL='").append(pdfURL);                           // .append('\'')
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
