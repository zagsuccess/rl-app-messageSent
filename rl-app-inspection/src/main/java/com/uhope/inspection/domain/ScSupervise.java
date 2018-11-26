package com.uhope.inspection.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * supervise
 * @author 
 */
@Data
public class ScSupervise implements Serializable {
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

    /**
     * 督查区联络员
     */
    private String supervisePerson;

    /**
     * 督查区联络员电话
     */
    private String personPhone;

    private static final long serialVersionUID = 1L;


}