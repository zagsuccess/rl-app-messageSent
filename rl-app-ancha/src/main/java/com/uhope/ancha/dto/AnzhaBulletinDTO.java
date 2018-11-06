package com.uhope.ancha.dto;
import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.domain.Template;

/**
 * 模版表-DTO数据传输对象类
 * @author ChenBin on 2018/09/04
 */
public class AnzhaBulletinDTO extends AnzhaBulletin {
    private String schemeName;

    public AnzhaBulletinDTO() {
    }

    public AnzhaBulletinDTO(String title, String month, String content, String accessory, String status, String schemeid, String schemeName) {
        super(title, month, content, accessory, status, schemeid);
        this.schemeName = schemeName;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    @Override
    public String toString() {
        return "AnzhaBulletinDTO{" +
                "schemeName='" + schemeName + '\'' +
                '}';
    }
}
