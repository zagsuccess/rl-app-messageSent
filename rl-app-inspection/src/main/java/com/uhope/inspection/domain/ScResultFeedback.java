package com.uhope.inspection.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * result_feedback
 * @author 
 */
@Data
public class ScResultFeedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private Integer whether;

    private String accessory;

    private String handleDate;

    private String inspectionid;

    private static final long serialVersionUID = 1L;


}