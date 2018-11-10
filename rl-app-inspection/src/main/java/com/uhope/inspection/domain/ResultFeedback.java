package com.uhope.inspection.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * result_feedback
 * @author 
 */
public class ResultFeedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String whether;

    private String accessory;

    private String handleDate;

    private String inspectionid;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWhether() {
        return whether;
    }

    public void setWhether(String whether) {
        this.whether = whether;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public String getInspectionid() {
        return inspectionid;
    }

    public void setInspectionid(String inspectionid) {
        this.inspectionid = inspectionid;
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
        ResultFeedback other = (ResultFeedback) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWhether() == null ? other.getWhether() == null : this.getWhether().equals(other.getWhether()))
            && (this.getAccessory() == null ? other.getAccessory() == null : this.getAccessory().equals(other.getAccessory()))
            && (this.getHandleDate() == null ? other.getHandleDate() == null : this.getHandleDate().equals(other.getHandleDate()))
            && (this.getInspectionid() == null ? other.getInspectionid() == null : this.getInspectionid().equals(other.getInspectionid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWhether() == null) ? 0 : getWhether().hashCode());
        result = prime * result + ((getAccessory() == null) ? 0 : getAccessory().hashCode());
        result = prime * result + ((getHandleDate() == null) ? 0 : getHandleDate().hashCode());
        result = prime * result + ((getInspectionid() == null) ? 0 : getInspectionid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", whether=").append(whether);
        sb.append(", accessory=").append(accessory);
        sb.append(", handleDate=").append(handleDate);
        sb.append(", inspectionid=").append(inspectionid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}