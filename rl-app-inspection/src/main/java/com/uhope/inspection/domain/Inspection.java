package com.uhope.inspection.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * inspection
 *
 * @author
 */
public class Inspection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String title;

    private String renumber;

    private String printDate;

    private String inspectType;

    private String sentRegion;

    private String accessory;

    private String state;

    private String content;

    private String oneregion;

    private static final long serialVersionUID = 1L;

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

    public String getRenumber() {
        return renumber;
    }

    public void setRenumber(String renumber) {
        this.renumber = renumber;
    }

    public String getPrintDate() {
        return printDate;
    }

    public void setPrintDate(String printDate) {
        this.printDate = printDate;
    }

    public String getInspectType() {
        return inspectType;
    }

    public void setInspectType(String inspectType) {
        this.inspectType = inspectType;
    }

    public String getSentRegion() {
        return sentRegion;
    }

    public void setSentRegion(String sentRegion) {
        this.sentRegion = sentRegion;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        Inspection other = (Inspection) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getRenumber() == null ? other.getRenumber() == null : this.getRenumber().equals(other.getRenumber()))
                && (this.getPrintDate() == null ? other.getPrintDate() == null : this.getPrintDate().equals(other.getPrintDate()))
                && (this.getInspectType() == null ? other.getInspectType() == null : this.getInspectType().equals(other.getInspectType()))
                && (this.getSentRegion() == null ? other.getSentRegion() == null : this.getSentRegion().equals(other.getSentRegion()))
                && (this.getAccessory() == null ? other.getAccessory() == null : this.getAccessory().equals(other.getAccessory()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getOneregion() == null ? other.getOneregion() == null : this.getOneregion().equals(other.getOneregion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getRenumber() == null) ? 0 : getRenumber().hashCode());
        result = prime * result + ((getPrintDate() == null) ? 0 : getPrintDate().hashCode());
        result = prime * result + ((getInspectType() == null) ? 0 : getInspectType().hashCode());
        result = prime * result + ((getSentRegion() == null) ? 0 : getSentRegion().hashCode());
        result = prime * result + ((getAccessory() == null) ? 0 : getAccessory().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
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
        sb.append(", title=").append(title);
        sb.append(", renumber=").append(renumber);
        sb.append(", printDate=").append(printDate);
        sb.append(", inspectType=").append(inspectType);
        sb.append(", sentRegion=").append(sentRegion);
        sb.append(", accessory=").append(accessory);
        sb.append(", state=").append(state);
        sb.append(", content=").append(content);
        sb.append(", oneregion=").append(oneregion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOneregion() {
        return oneregion;
    }

    public void setOneregion(String oneregion) {
        this.oneregion = oneregion;
    }
}