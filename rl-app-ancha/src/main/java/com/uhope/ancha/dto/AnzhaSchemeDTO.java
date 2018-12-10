package com.uhope.ancha.dto;
import com.uhope.ancha.domain.AnzhaScheme;
import com.uhope.ancha.domain.Template;

/**
 * 模版表-DTO数据传输对象类
 * @author ChenBin on 2018/09/04
 */

public class AnzhaSchemeDTO extends AnzhaScheme {
    private String bulletinName;

    public AnzhaSchemeDTO() {
    }

    public AnzhaSchemeDTO(String bulletinName) {
        this.bulletinName = bulletinName;
    }

    public AnzhaSchemeDTO(String title, String issue, String createuser, String content, String assessoryyuan, String assessory, String bulletinid, String bulletinName) {
        super(title, issue, createuser, content, assessoryyuan, assessory, bulletinid);
        this.bulletinName = bulletinName;
    }

    public String getBulletinName() {
        return bulletinName;
    }

    public void setBulletinName(String bulletinName) {
        this.bulletinName = bulletinName;
    }

    @Override
    public String toString() {
        return "AnzhaSchemeDTO{" +
                "bulletinName='" + bulletinName + '\'' +
                '}';
    }
}
