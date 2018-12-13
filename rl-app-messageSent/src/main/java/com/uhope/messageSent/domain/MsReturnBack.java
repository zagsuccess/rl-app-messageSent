package com.uhope.messageSent.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "ms_returnback")
public class MsReturnBack implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;


    private String objid;


    private String reason;


    @Column(name = "create_person")
    private String createPerson;

    @Column(name = "create_time")
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
