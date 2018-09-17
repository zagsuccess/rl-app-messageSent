package com.uhope.rl.resumption.constant;

public class constants {


    //处理中
    public static final String TACHE_ACTIVE = "A";

    /**
     * 河长助理派单后环节处理人的临时状态，待河长确认后改成A
     */
    public static final String TACHE_TEMP = "T";

    //处理结束
    public static final String TACHE_FINAL = "F";

    public static final String ROLE_HZ = "HZ";

    public static final String ROLE_HZZL = "HZZL";

    public static final String ROLE_LLY = "LLY";

    public static final String ROLE_GZRY = "GZRY";

    public static final String ROLE_ZNRY = "ZNRY";

    /**
     * 销案
     */
    public static final String EVENT_STATUS_X = "X";

    /**
     * 结案
     */
    public static final String EVENT_STATUS_Z = "Z";

    /**
     * 联络员待派单
     */
    public static final String EVENT_STATUS_B = "B";

    /**
     * 河长待派单
     */
    public static final String EVENT_STATUS_E = "E";

    /**
     * 河长待确认
     */
    public static final String EVENT_STATUS_H = "H";

    /**
     * 河长反馈求助
     */
    public static final String EVENT_STATUS_K = "K";

    /**
     * 联络员反馈求助
     */
    public static final String EVENT_STATUS_G = "G";

    /**
     * 工作人员待处理
     */
    public static final String EVENT_STATUS_F = "F";

    /**
     * 职能部门成员待处理
     */
    public static final String EVENT_STATUS_Y = "Y";

    /**
     * 职能部门处理完成
     */
    public static final String EVENT_STATUS_P = "P";

    /**
     * 河长助理结案
     */
    public static final String EVENT_STATUS_M = "M";

    /**
     * 事件上报
     */
    public static final String EVENT_STATUS_A = "A";

    /** */
    public static final String ISSUPERVISE_0 = "0";

    /**
     * 事件不公开
     */
    public static final String ISEXPOSURE_0 = "0";

    /**
     * 事件公开
     */
    public static final String ISEXPOSURE_1 = "1";

    /**
     * 任务类型A
     */
    public static final String TASKTYPE_TASK = "A";

    //事件来源
    /**
     * 事件来源--河长APP
     */
    public static final String EVENTRESOURCE_CHAIRMAN = "A";
    /**
     * 事件来源-电话上报
     */
    public static final String EVENTRESOURCE_PHONE = "B";
    /**
     * 事件来源-公众APP
     */
    public static final String EVENTRESOURCE_PUBLICMAN = "C";
    /**
     * 事件来源-微信号
     */
    public static final String EVENTRESOURCE_WECHAT = "D";

    /**
     * 事件来源--曝光台
     */
    public static final String EVENTRESOURCE_EXPOSURE = "F";

}
