package com.uhope.inspection.utils;

import com.uhope.uip.dto.UserDTO;

import java.util.List;

/**
 * @author: zhoujiawen
 * @date:2018/9/5 15:25
 * @description: 主要用于拼接SQL用
 */
public class SqlUtils {

    public static String genUserTbByUserDto(List<UserDTO> userList){
        if(userList == null || userList.size() == 0){
            return "select 1";
        }

        int size = userList.size();
        StringBuilder sb = new StringBuilder();
        String select = " SELECT ";
        String union = " UNION ALL ";
        for(int index = 0;index < size;index++){
            UserDTO user = userList.get(index);
            String userId = user.getId();
            Double sortOrder = user.getSortOrder();
            Long regionId = user.getRegionId();

            sb.append(select).append("'").append(userId).append("'").append(" AS userId,").append(regionId).append(" AS regionId");
            //.append(sortOrder).append(" AS sortOrder ");
            if(index < size - 1){
                sb.append(union);
            }
        }

        return sb.toString();
    }

    public static String genUserIdTbByUserDtos(List<UserDTO> userList){
        if(userList == null || userList.size() == 0){
            return "select 1";
        }

        int size = userList.size();
        StringBuilder sb = new StringBuilder();
        String select = " SELECT ";
        String union = " UNION ";
        for(int index = 0;index < size;index++){
            UserDTO user = userList.get(index);
            String userId = user.getId();
            sb.append(select).append("'").append(userId).append("'").append(" AS userId ");
            if(index < size - 1){
                sb.append(union);
            }
        }

        return sb.toString();
    }

    public static String genIdTbByUserIdList(List<String> list){
        if(list == null || list.size() == 0){
            return "select 1";
        }
        int size = list.size();
        StringBuilder sb = new StringBuilder();
        String select = " SELECT ";
        String union = " UNION ";
        for(int index = 0;index < size;index++){
            String userId = list.get(index);
            sb.append(select).append("'").append(userId).append("'").append(" AS userId ");
            if(index < size - 1){
                sb.append(union);
            }
        }

        return sb.toString();
    }
}
