package com.uhope.ancha.dto;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/30
 * @description:
 */
public class RegionDTO implements Serializable {

    /**
     *
     */
    private String regionId;

    /**
     *
     */
    private String regionName;

    /**
     * @return regionId
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * @param regionId
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    /**
     * @return regionName
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "RegionDTO{" +
                "regionId='" + regionId + '\'' +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
