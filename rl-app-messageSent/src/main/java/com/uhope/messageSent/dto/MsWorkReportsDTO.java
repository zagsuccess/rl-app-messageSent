package com.uhope.messageSent.dto;
import com.uhope.messageSent.domain.MsWorkReports;

import java.util.List;
import java.util.Map;

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
    /**
     * 机构号对应的机构名称
     */
    private String regionName;


    /**
     *  文件的 预览地址与下载地址对应
     */
    private List<Map<String, String>> fileList;

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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<Map<String, String>> getFileList() {
        return fileList;
    }

    public void setFileList(List<Map<String, String>> fileList) {
        this.fileList = fileList;
    }


}
