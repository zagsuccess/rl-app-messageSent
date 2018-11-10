package com.uhope.inspection.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * supervise
 * @author 
 */
public class Supervise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    /**
     * 组长
     */
    private String groupLeader;

    /**
     * 分组
     */
    private String grouping;

    /**
     * 督查区
     */
    private String area;

    /**
     * 联络员
     */
    private String contact;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 组成部门
     */
    private String department;

    /**
     * 督查表id
     */
    private String inspectionid;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        Supervise other = (Supervise) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGroupLeader() == null ? other.getGroupLeader() == null : this.getGroupLeader().equals(other.getGroupLeader()))
            && (this.getGrouping() == null ? other.getGrouping() == null : this.getGrouping().equals(other.getGrouping()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getDepartment() == null ? other.getDepartment() == null : this.getDepartment().equals(other.getDepartment()))
            && (this.getInspectionid() == null ? other.getInspectionid() == null : this.getInspectionid().equals(other.getInspectionid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGroupLeader() == null) ? 0 : getGroupLeader().hashCode());
        result = prime * result + ((getGrouping() == null) ? 0 : getGrouping().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getDepartment() == null) ? 0 : getDepartment().hashCode());
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
        sb.append(", groupLeader=").append(groupLeader);
        sb.append(", grouping=").append(grouping);
        sb.append(", area=").append(area);
        sb.append(", contact=").append(contact);
        sb.append(", phone=").append(phone);
        sb.append(", department=").append(department);
        sb.append(", inspectionid=").append(inspectionid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}