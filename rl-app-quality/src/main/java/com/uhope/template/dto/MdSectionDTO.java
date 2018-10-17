package com.uhope.template.dto;

import java.io.Serializable;
import lombok.Data;
/**
 * @Author :shujihui
 * @Date : 2018/10/11
 * @Time : 19:59
 * Aim :
 */
@Data
public class MdSectionDTO implements Serializable {
    private String name;
    private String riverName;
    private MdSectionDTO mdSection;

   /* public MdSectionDTO() {
    }

    public MdSectionDTO(String name, String riverName, MdSectionDTO mdSection) {
        this.name = name;
        this.riverName = riverName;
        this.mdSection = mdSection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public MdSectionDTO getMdSection() {
        return mdSection;
    }

    public void setMdSection(MdSectionDTO mdSection) {
        this.mdSection = mdSection;
    }

    @Override
    public String toString() {
        return "MdSectionDTO{" +
                "name='" + name + '\'' +
                ", riverName='" + riverName + '\'' +
                ", mdSection=" + mdSection +
                '}';
    }*/
}
