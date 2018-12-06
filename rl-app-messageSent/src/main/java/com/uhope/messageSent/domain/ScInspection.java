package com.uhope.messageSent.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * messageSent
 *
 * @author
 */
@Data
public class ScInspection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String title;

    private String renumber;

    private String printDate;

    private String inspectType;

    private String sentRegion;

    private String accessory;

    private Integer state;

    private String content;

    private Integer oneregion;

    private String sentUnit;

    private static final long serialVersionUID = 1L;
}