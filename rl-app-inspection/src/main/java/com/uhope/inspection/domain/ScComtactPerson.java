package com.uhope.inspection.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * comtact_person
 * @author 
 */
@Data
public class ScComtactPerson implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 职务
     */
    private String duty;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 督查表id
     */
    private String inspectionid;

    private static final long serialVersionUID = 1L;


}