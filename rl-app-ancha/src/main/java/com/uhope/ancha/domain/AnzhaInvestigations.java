package com.uhope.ancha.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class AnzhaInvestigations implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 检查日期
     */
    private String date;

    /**
     * 对应区域的id
     */
    private String regionid;
    /**
     * 对应区域的名字
     */
    private String regionname;
    /**
     * 对应河流的名字
     */
    private String reachname;
    /**
     * 坐标的位置
     */
    private String coordinate;
    /**
     * 带队领导
     */
    private String leader;
    /**
     * 下发人员
     */
    private String personnel;
    /**
     * 0 已下发 1 检查中 2 已通报 3 已处理 4 未按期
     */
    private String status;
    /**
     * 对应 scheme表的id
     **/
    private String schemeid;
    public AnzhaInvestigations() {
        super();
    }

    public AnzhaInvestigations(String title, String date, String regionid, String regionname, String reachname, String coordinate, String leader, String personnel, String status, String schemeid) {
        this.title = title;
        this.date = date;
        this.regionid = regionid;
        this.regionname = regionname;
        this.reachname = reachname;
        this.coordinate = coordinate;
        this.leader = leader;
        this.personnel = personnel;
        this.status = status;
        this.schemeid = schemeid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLeader() {
        return this.leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPersonnel() {
        return this.personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getReachname() {
        return reachname;
    }

    public void setReachname(String reachname) {
        this.reachname = reachname;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "AnzhaInvestigations{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", regionid='" + regionid + '\'' +
                ", regionname='" + regionname + '\'' +
                ", reachname='" + reachname + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", leader='" + leader + '\'' +
                ", personnel='" + personnel + '\'' +
                ", status='" + status + '\'' +
                ", schemeid='" + schemeid + '\'' +
                '}';
    }
}
