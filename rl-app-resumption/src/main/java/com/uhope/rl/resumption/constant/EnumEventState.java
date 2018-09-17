package com.uhope.rl.resumption.constant;

/**
 * 事件相关状态
 * Created by uhope-phl on 2016/5/26.
 */
public enum EnumEventState {
    A, // 事件上报(基础河长)
    B, // 待受理件 (值班员)
    C, // 待核实(基层河长)
    D, // 确认核实(确认核实人员/值班员)
    E, // 待派单(值班员)
    F, // 待处理(职能部门)
    G, // 待处理反馈/已处理(值班员)
    H, // 待审核(基层河长)
    I, // 待结案(值班员)
    J, // 河长办审核
    L, // 河长办处理
    M, // 河长助理处理
    N, // 委办局处理
    Z, // 已结案
    T, // 事务锁定状态 用于锁定修改事务
    X // 无效事件 (值班员)
}
