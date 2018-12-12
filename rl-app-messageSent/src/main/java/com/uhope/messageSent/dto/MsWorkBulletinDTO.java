package com.uhope.messageSent.dto;

import com.uhope.messageSent.domain.MsWorkBulletin;

/**
 * 工作简报表-DTO数据传输对象类
 * @author  wanglijun
 */
public class MsWorkBulletinDTO extends MsWorkBulletin {

    public MsWorkBulletinDTO() {

    }

    /**
     * 下载地址
     */
    private String downurl;
    /**
     * 原文件名
     */
    private String ren;

    /**
     * 机构号对应的机构名称
     */
    private String regionName;

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public String getRen() {
        return ren;
    }

    public void setRen(String ren) {
        this.ren = ren;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
