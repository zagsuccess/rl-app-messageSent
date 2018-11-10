package com.uhope.ancha.dto;

import java.io.Serializable;

/**
 * 模版表-DTO数据传输对象类
 * @author ChenBin on 2018/09/04
 */
public class PersonnelDTO implements Serializable {
    private String id;
    private String name;

    public PersonnelDTO() {
    }

    public PersonnelDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonnelDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
