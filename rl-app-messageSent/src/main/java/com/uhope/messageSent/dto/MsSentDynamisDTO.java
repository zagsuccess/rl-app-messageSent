package com.uhope.messageSent.dto;
import com.uhope.messageSent.domain.MsSentDynamis;

/**
 * 周动态报送表-DTO数据传输对象类
 * @author mengaoran on 2018/11/27
 */
public class MsSentDynamisDTO extends MsSentDynamis {
    /**
     * 下载地址
     */
    private String downurl;
    /**
     * 原文件名
     */
    private String ren;

    public MsSentDynamisDTO() {
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
