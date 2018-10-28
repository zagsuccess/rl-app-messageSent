package com.uhope.water.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * exe_ass_patrol
 * @author 
 */
public class ExeAssPatrol implements Serializable {
    private String id;

    private String riverName;

    private String region;

    private Date patrolDate;

    private String riverQuestion;

    private String pointsType;

    private Integer buckleScores;

    private String patorPerson;

    private String location;

    private String photoUrl;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getPatrolDate() {
        return patrolDate;
    }

    public void setPatrolDate(Date patrolDate) {
        this.patrolDate = patrolDate;
    }

    public String getRiverQuestion() {
        return riverQuestion;
    }

    public void setRiverQuestion(String riverQuestion) {
        this.riverQuestion = riverQuestion;
    }

    public String getPointsType() {
        return pointsType;
    }

    public void setPointsType(String pointsType) {
        this.pointsType = pointsType;
    }

    public Integer getBuckleScores() {
        return buckleScores;
    }

    public void setBuckleScores(Integer buckleScores) {
        this.buckleScores = buckleScores;
    }

    public String getPatorPerson() {
        return patorPerson;
    }

    public void setPatorPerson(String patorPerson) {
        this.patorPerson = patorPerson;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
        ExeAssPatrol other = (ExeAssPatrol) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRiverName() == null ? other.getRiverName() == null : this.getRiverName().equals(other.getRiverName()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getPatrolDate() == null ? other.getPatrolDate() == null : this.getPatrolDate().equals(other.getPatrolDate()))
            && (this.getRiverQuestion() == null ? other.getRiverQuestion() == null : this.getRiverQuestion().equals(other.getRiverQuestion()))
            && (this.getPointsType() == null ? other.getPointsType() == null : this.getPointsType().equals(other.getPointsType()))
            && (this.getBuckleScores() == null ? other.getBuckleScores() == null : this.getBuckleScores().equals(other.getBuckleScores()))
            && (this.getPatorPerson() == null ? other.getPatorPerson() == null : this.getPatorPerson().equals(other.getPatorPerson()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getPhotoUrl() == null ? other.getPhotoUrl() == null : this.getPhotoUrl().equals(other.getPhotoUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRiverName() == null) ? 0 : getRiverName().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getPatrolDate() == null) ? 0 : getPatrolDate().hashCode());
        result = prime * result + ((getRiverQuestion() == null) ? 0 : getRiverQuestion().hashCode());
        result = prime * result + ((getPointsType() == null) ? 0 : getPointsType().hashCode());
        result = prime * result + ((getBuckleScores() == null) ? 0 : getBuckleScores().hashCode());
        result = prime * result + ((getPatorPerson() == null) ? 0 : getPatorPerson().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getPhotoUrl() == null) ? 0 : getPhotoUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", riverName=").append(riverName);
        sb.append(", region=").append(region);
        sb.append(", patrolDate=").append(patrolDate);
        sb.append(", riverQuestion=").append(riverQuestion);
        sb.append(", pointsType=").append(pointsType);
        sb.append(", buckleScores=").append(buckleScores);
        sb.append(", patorPerson=").append(patorPerson);
        sb.append(", location=").append(location);
        sb.append(", photoUrl=").append(photoUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}