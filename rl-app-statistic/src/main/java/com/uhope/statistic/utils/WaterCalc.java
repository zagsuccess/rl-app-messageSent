package com.uhope.statistic.utils;

import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.WaterQualityDTO;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description: 水质扣分工具类
 */
public class WaterCalc {

    private WaterCalc() {

    }

    /**
     * 高锰酸盐扣分
     *
     * @param permanganate 高锰酸钾浓度
     * @return
     */
    public static Double calcPermanganate(Double permanganate) {
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

    /**
     * 氨氮扣分
     *
     * @param AN
     * @return
     */
    public static Double calcAN(Double AN) {
        Double result = 0D;
        if (AN > 1.8) {
            result = 15D;
        } else if (AN > 1.6 && AN <= 1.8) {
            result = 10D;
        } else if (AN > 1.4 && AN <= 1.6) {
            result = 6D;
        } else if (AN > 1.2 && AN <= 1.4) {
            result = 4D;
        } else if (AN > 1.0 && AN <= 1.2) {
            result = 2D;
        }
        return result;
    }

    /**
     * 总磷扣分
     *
     * @param phosphorus
     * @return
     */
    public static Double calcPhosphorus(Double phosphorus) {
        Double result = 0D;
        if (phosphorus > 1.8) {
            result = 15D;
        } else if (phosphorus > 1.6 && phosphorus <= 1.8) {
            result = 10D;
        } else if (phosphorus > 1.4 && phosphorus <= 1.6) {
            result = 6D;
        } else if (phosphorus > 1.2 && phosphorus <= 1.4) {
            result = 4D;
        } else if (phosphorus > 1.0 && phosphorus <= 1.2) {
            result = 2D;
        }
        return result;
    }

    /**
     * 溶解氧扣分
     *
     * @param oxygen
     * @return
     */
    public static Double calcOxygen(Double oxygen) {
        Double result = 0D;
        if (oxygen > 1.8) {
            result = 15D;
        } else if (oxygen > 1.6 && oxygen <= 1.8) {
            result = 10D;
        } else if (oxygen > 1.4 && oxygen <= 1.6) {
            result = 6D;
        } else if (oxygen > 1.2 && oxygen <= 1.4) {
            result = 4D;
        } else if (oxygen > 1.0 && oxygen <= 1.2) {
            result = 2D;
        }
        return result;
    }

    public static RiverStatisticDTO calcResult(WaterQualityDTO waterQualityDTO) {
        Double an = calcAN(waterQualityDTO.getAN());
        Double oxygen = calcOxygen(waterQualityDTO.getOxygen());
        Double permanganate = calcPermanganate(waterQualityDTO.getPermanganate());
        Double phosphorus = calcPhosphorus(waterQualityDTO.getPhosphorus());
        RiverStatisticDTO riverStatisticDTO = new RiverStatisticDTO();
        riverStatisticDTO.setRiverScore(60D-an-oxygen-permanganate-phosphorus);
        riverStatisticDTO.setDeductionScore(0.0);
        riverStatisticDTO.setHygieneScore(100.00);
        riverStatisticDTO.setShorelineScore(100.00);
        riverStatisticDTO.setRegionName(waterQualityDTO.getRegionName());
        Double result = riverStatisticDTO.getRiverScore()*0.7 + riverStatisticDTO.getHygieneScore()*0.15 + riverStatisticDTO.getShorelineScore()*0.15;
        riverStatisticDTO.setResultScore(result);
        return riverStatisticDTO;
    }
}
