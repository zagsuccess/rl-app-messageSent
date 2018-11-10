package com.uhope.inspection.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * traffic_list
 * @author 
 */
public class TrafficList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String region;

    private String accessory;

    private String inspectionId;

    private String trafficDate;

    private String trafficContent;

    private String oneregion;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getTrafficDate() {
        return trafficDate;
    }

    public void setTrafficDate(String trafficDate) {
        this.trafficDate = trafficDate;
    }

    public String getTrafficContent() {
        return trafficContent;
    }

    public void setTrafficContent(String trafficContent) {
        this.trafficContent = trafficContent;
    }

    public String getOneregion() {
        return oneregion;
    }

    public void setOneregion(String oneregion) {
        this.oneregion = oneregion;
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
        TrafficList other = (TrafficList) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getAccessory() == null ? other.getAccessory() == null : this.getAccessory().equals(other.getAccessory()))
            && (this.getInspectionId() == null ? other.getInspectionId() == null : this.getInspectionId().equals(other.getInspectionId()))
            && (this.getTrafficDate() == null ? other.getTrafficDate() == null : this.getTrafficDate().equals(other.getTrafficDate()))
            && (this.getTrafficContent() == null ? other.getTrafficContent() == null : this.getTrafficContent().equals(other.getTrafficContent()))
            && (this.getOneregion() == null ? other.getOneregion() == null : this.getOneregion().equals(other.getOneregion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getAccessory() == null) ? 0 : getAccessory().hashCode());
        result = prime * result + ((getInspectionId() == null) ? 0 : getInspectionId().hashCode());
        result = prime * result + ((getTrafficDate() == null) ? 0 : getTrafficDate().hashCode());
        result = prime * result + ((getTrafficContent() == null) ? 0 : getTrafficContent().hashCode());
        result = prime * result + ((getOneregion() == null) ? 0 : getOneregion().hashCode());
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
        sb.append(", accessory=").append(accessory);
        sb.append(", inspectionId=").append(inspectionId);
        sb.append(", trafficDate=").append(trafficDate);
        sb.append(", trafficContent=").append(trafficContent);
        sb.append(", oneregion=").append(oneregion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}