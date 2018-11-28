package com.uhope.duban.utils;

import java.util.List;

/**
 * @Author :shujihui
 * @Date : 2018/11/28
 * @Time : 20:31
 * Aim :假分页工具类
 */

public class PageUtil {

    /**
     *
     * @param list 原列表
     * @param currentPage 当前页
     * @param maxNum 页大小
     * @return
     */
    public static List getPageList(List list, int currentPage, int maxNum){
        int pageNum = list.size()/maxNum+1;
        int fromIndex = 0; // 从哪里开始截取
        int toIndex = 0; // 截取几个
        if (list == null || list.size() == 0){
            return null;
        }
        // 当前页小于或等于总页数时执行
        if (currentPage <= pageNum && currentPage != 0) {
            fromIndex = (currentPage - 1) * maxNum;

            if (currentPage == pageNum) {
                toIndex = list.size();

            } else {
                toIndex = currentPage * maxNum;
            }

        }
        return list.subList(fromIndex, toIndex);
    }

}
