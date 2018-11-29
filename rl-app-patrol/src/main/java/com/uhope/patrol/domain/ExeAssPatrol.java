package com.uhope.patrol.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * exe_ass_patrol
 * @author 
 */
public class ExeAssPatrol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String riverName;

    private String region;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date patrolDate;

    private String riverQuestion;

    private String pointsType;

    private double buckleScores;

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

    public double getBuckleScores() {
        return buckleScores;
    }

    public void setBuckleScores(double buckleScores) {
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


}