package com.uhope.sewage.utils;

/**
 * @Author :shujihui
 * @Date : 2018/10/12
 * @Time : 10:03
 * Aim :
 */

public class ExcelUtil {
    public static boolean isExcel2003(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
