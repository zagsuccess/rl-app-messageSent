package com.uhope.messageSent.dto;
import com.uhope.messageSent.domain.MsWorkReports;

/**
 * 工作简报表-DTO数据传输对象类
 * @author mengaoran on 2018/11/27
 */
public class MsWorkReportsDTO extends MsWorkReports {
    /**
     * 下载地址
     */
    private String downurl;
    /**
     * 原文件名
     */
    private String ren;

    public MsWorkReportsDTO() {
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

}
