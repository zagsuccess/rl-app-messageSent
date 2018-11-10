package com.uhope.duban.dto;

import java.io.Serializable;

/**
 * @Author :shujihui
 * @Date : 2018/11/7
 * @Time : 13:23
 * Aim :
 */

public class DeadlineDTO implements Serializable {
    private String id;
    private String objectid;

    public DeadlineDTO() {
    }

    public DeadlineDTO(String id, String objectid) {
        this.id = id;
        this.objectid = objectid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    @Override
    public String toString() {
        return "DeadlineDTO{" +
                "id='" + id + '\'' +
                ", objectid='" + objectid + '\'' +
                '}';
    }
}
