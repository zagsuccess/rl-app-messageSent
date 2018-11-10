package com.uhope.inspection.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * problem_handle
 *
 * @author
 */
@Data
public class ScProblemHandle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String inspectionid;

    private String sentDate;

    private String accessory;

    private String description;

    private Integer state;

    private static final long serialVersionUID = 1L;


}