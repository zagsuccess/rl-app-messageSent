package com.uhope.statistic.utils;

import com.uhope.statistic.dto.KValueDTO;

import java.util.List;
import java.util.Map;

/**
 * @author: Yang Jiaheng
 * @date: 18/12/13
 * @description: 水质考核评分工具类
 */
public class WaterQualityStatisticUtil {

    private WaterQualityStatisticUtil(){

    }

    /**
     * 根据当前水质等级和目标水质等级计算对应的水质得分系数f值
     * @return 河段得分系数f
     */
    public static Double calcF(Integer nowLevel, Integer targetLvel){
        if (nowLevel - targetLvel <= 0) {
            //水质级别达到河段水功能区划目标
            return 1.0D;
        }else if (nowLevel - targetLvel == 1){
            //水质级别 比 河段水功能区划目标 低1级
            return 0.9D;
        }else if (nowLevel - targetLvel == 2){
            //水质级别 比 河段水功能区划目标 低2级
            return 0.8D;
        }else if (nowLevel - targetLvel == 3){
            //水质级别 比 河段水功能区划目标 低3级
            return 0.7D;
        }else{
            //最低0.6
            return 0.6D;
        }
    }

    /**
     * 根据f值和n值计算最后的N值
     * @return 河段考核最终得分N
     */
    public static KValueDTO calcN(KValueDTO kValueDTO){
        //拿到溶解氧、高锰酸盐、氨氮、总磷的f值
        Double fTp = kValueDTO.getFTp();
        Double fCodmn = kValueDTO.getFCodmn();
        Double fDo = kValueDTO.getFDo();
        Double fNh4n = kValueDTO.getFNh4n();
        //计算出平均f值
        Double f = fTp+fCodmn+fDo+fNh4n;

        //拿到溶解氧、高锰酸盐、氨氮、总磷的n值
        Double nCodmn = kValueDTO.getNCodmn();
        Double nDo = kValueDTO.getNDo();
        Double nNh4n = kValueDTO.getNNh4n();
        Double nTp = kValueDTO.getNTp();
        //计算出平均n值
        Double n = nCodmn+nDo+nNh4n+nTp;

        //根据平均f值和平均n值计算最终得分
        kValueDTO.setResult(f*n);

        return kValueDTO;
    }
}
