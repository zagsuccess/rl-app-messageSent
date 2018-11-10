package com.uhope.inspection.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * traffic_list
 * @author 
 */
@Data
public class ScTrafficList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String region;

    private String accessory;

    private String inspectionId;

    private String trafficDate;

    private String trafficContent;

    private Integer oneregion;

    private static final long serialVersionUID = 1L;


}