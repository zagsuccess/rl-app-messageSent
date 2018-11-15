package com.uhope.bulletin.dto;

import com.uhope.bulletin.domain.Bulletin;

import java.util.Date;

/**
 * @author: StivenYang
 * @date: 2018/9/8
 * @description: 暗查暗访表-DTO数据传输对象
 */
public class BulletinDTO extends Bulletin {
    /**
     * 下载地址
     */
    private String downurl;
    /**
     * 原文件名
     */
    private String ren;

    public BulletinDTO() {
    }

    public BulletinDTO(String title, Integer type, String issuer, String post_time, String month, String year, String detail_url, Integer status, String attand_url, Date create_time, String detail, String downurl, String ren) {
        super(title, type, issuer, post_time, month, year, detail_url, status, attand_url, create_time, detail);
        this.downurl = downurl;
        this.ren = ren;
    }

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

    @Override
    public String toString() {
        return "BulletinDTO{" +
                "downurl='" + downurl + '\'' +
                ", ren='" + ren + '\'' +
                '}';
    }
}
