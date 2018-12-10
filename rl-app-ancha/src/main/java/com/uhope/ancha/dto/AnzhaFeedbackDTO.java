package com.uhope.ancha.dto;
import com.uhope.ancha.domain.AnzhaFeedback;

import java.util.Date;

/**
 * 模版表-DTO数据传输对象类
 * @author ChenBin on 2018/09/04
 */
public class AnzhaFeedbackDTO extends AnzhaFeedback {
    private String objectname;

    public AnzhaFeedbackDTO() {
    }

    public AnzhaFeedbackDTO(String objectname) {
        this.objectname = objectname;
    }

    public AnzhaFeedbackDTO(Date feedbacktime, String objectid, String whether, String description, String assessoryyuan, String assessory, String bulletinid, String status, String objectname) {
        super(feedbacktime, objectid, whether, description, assessoryyuan, assessory, bulletinid, status);
        this.objectname = objectname;
    }

    public String getObjectname() {
        return objectname;
    }

    public void setObjectname(String objectname) {
        this.objectname = objectname;
    }

    @Override
    public String toString() {
        return "AnzhaFeedbackDTO{" +
                "objectname='" + objectname + '\'' +
                '}';
    }
}
