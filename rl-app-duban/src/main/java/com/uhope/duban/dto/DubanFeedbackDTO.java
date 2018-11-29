package com.uhope.duban.dto;
import com.uhope.duban.domain.ScDubanFeedback;
import com.uhope.template.domain.Template;

import java.util.Date;

/**
 * 模版表-DTO数据传输对象类
 * @author ChenBin on 2018/09/04
 */
public class DubanFeedbackDTO extends ScDubanFeedback {
    private String objectname;

    public DubanFeedbackDTO() {
    }

    public DubanFeedbackDTO(Date feedbacktime, String objectid, String whetherlocale, String whether, String description, String assessory, String supervisionid, String status, Date createtime, String objectname) {
        super(feedbacktime, objectid, whetherlocale, whether, description, assessory, supervisionid, status, createtime);
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
        return "DubanFeedbackDTO{" +
                "objectname='" + objectname + '\'' +
                '}';
    }
}
