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

    public AnzhaFeedbackDTO(Date feedbackTime, String objectid, String whether, String describe, String assessory, String bulletinid, String status, String objectname) {
        super(feedbackTime, objectid, whether, describe, assessory, bulletinid, status);
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
