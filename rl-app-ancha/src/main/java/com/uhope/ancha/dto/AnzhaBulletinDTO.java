package com.uhope.ancha.dto;
import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.domain.Template;

import java.util.Date;

/**
 * 模版表-DTO数据传输对象类
 * @author ChenBin on 2018/09/04
 */
public class AnzhaBulletinDTO extends AnzhaBulletin {
    private String schemeName;
    /**
     * 是否有反馈
     */
    private String or;
    /**
     * 需反馈区数量
     */
    private int xunum;
    /**
     * 已反馈区数量
     */
    private int yinum;

    public AnzhaBulletinDTO() {
    }

    public AnzhaBulletinDTO(String schemeName, String or, int xunum, int yinum) {
        this.schemeName = schemeName;
        this.or = or;
        this.xunum = xunum;
        this.yinum = yinum;
    }


    public AnzhaBulletinDTO(String title, Date issuetime, String month, String content, String assessoryyuan, String accessory, String status, String feedbackareaid, String feedbackareaname, Date deadlinetime, String schemeid, String schemeName, String or, int xunum, int yinum) {
        super(title, issuetime, month, content, assessoryyuan, accessory, status, feedbackareaid, feedbackareaname, deadlinetime, schemeid);
        this.schemeName = schemeName;
        this.or = or;
        this.xunum = xunum;
        this.yinum = yinum;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getOr() {
        return or;
    }

    public void setOr(String or) {
        this.or = or;
    }

    public int getXunum() {
        return xunum;
    }

    public void setXunum(int xunum) {
        this.xunum = xunum;
    }

    public int getYinum() {
        return yinum;
    }

    public void setYinum(int yinum) {
        this.yinum = yinum;
    }

    @Override
    public String toString() {
        return "AnzhaBulletinDTO{" +
                "schemeName='" + schemeName + '\'' +
                ", or='" + or + '\'' +
                ", xunum=" + xunum +
                ", yinum=" + yinum +
                '}';
    }
}
