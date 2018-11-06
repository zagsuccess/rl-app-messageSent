package com.uhope.statistic.utils;

import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.WaterQualityDTO;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description: 区域地表水环境质量考核成绩
 */
public class WaterCalc {

    private WaterCalc() {

    }

    /**
     * 高锰酸盐扣分
     *
     * @param permanganate 高锰酸钾浓度
     * @return 扣分结果
     */
    public static Double calcPermanganate(Double permanganate) {
        Double result = getaDouble(permanganate);
        return result;
    }

    /**
     * 氨氮扣分
     *
     * @param AN
     * @return 扣分结果
     */
    public static Double calcAN(Double AN) {
        Double result = getaDouble(AN);
        return result;
    }

    /**
     * 总磷扣分
     *
     * @param phosphorus
     * @return 扣分结果
     */
    public static Double calcPhosphorus(Double phosphorus) {
        Double result = getaDouble(phosphorus);
        return result;
    }

    /**
     * 溶解氧扣分
     *
     * @param oxygen 溶解氧浓度
     * @return 溶解氧扣分
     */
    public static Double calcOxygen(Double oxygen) {
        Double result = 0D;
        if (oxygen >= 1.0) {
            result = 0D;
        } else if (oxygen > 0.8 && oxygen <= 1.0) {
            result = 2D;
        } else if (oxygen > 0.6 && oxygen <= 0.8) {
            result = 4D;
        } else if (oxygen > 0.4 && oxygen <= 0.6) {
            result = 6D;
        } else if (oxygen > 0.2 && oxygen <= 0.4) {
            result = 10D;
        } else if (oxygen <= 0.2) {
            result = 15D;
        }
        return result;
    }

    /**
     * 计算河湖水生态环境质量考核成绩 最终结果
     * @param waterQualityDTO 水质表
     * @return 计算结果表实体
     */
    public static RiverStatisticDTO calcResult(WaterQualityDTO waterQualityDTO) {
        Double an = calcAN(waterQualityDTO.getAN());
        Double oxygen = calcOxygen(waterQualityDTO.getOxygen());
        Double permanganate = calcPermanganate(waterQualityDTO.getPermanganate());
        Double phosphorus = calcPhosphorus(waterQualityDTO.getPhosphorus());
        RiverStatisticDTO riverStatisticDTO = new RiverStatisticDTO();
        //此处统计河湖水质成绩
        riverStatisticDTO.setRiverScore(60D-an-oxygen-permanganate-phosphorus+20D+10D+10D);
        //此处统计违规扣分
        riverStatisticDTO.setDeductionScore(0.0);
        //此处统计河湖堤岸水面环境卫生成绩
        riverStatisticDTO.setHygieneScore(100.00);
        //此处统计河湖岸线管理成绩
        riverStatisticDTO.setShorelineScore(100.00);
        riverStatisticDTO.setRegionName(waterQualityDTO.getRegionName());
        Double result = riverStatisticDTO.getRiverScore()*0.7 + riverStatisticDTO.getHygieneScore()*0.15 + riverStatisticDTO.getShorelineScore()*0.15;
        riverStatisticDTO.setResultScore(result);
        return riverStatisticDTO;
    }

    private static Double getaDouble(Double permanganate) {
        Double result = 0D;
        if (permanganate > 1.8) {
            result = 15D;
        } else if (permanganate > 1.6 && permanganate <= 1.8) {
            result = 10D;
        } else if (permanganate > 1.4 && permanganate <= 1.6) {
            result = 6D;
        } else if (permanganate > 1.2 && permanganate <= 1.4) {
            result = 4D;
        } else if (permanganate > 1.0 && permanganate <= 1.2) {
            result = 2D;
        }
        return result;
    }
}
