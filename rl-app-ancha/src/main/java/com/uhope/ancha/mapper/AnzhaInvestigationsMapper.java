package com.uhope.ancha.mapper;

import com.uhope.ancha.domain.AnzhaInvestigations;
import com.uhope.ancha.dto.PersonnelDTO;
import com.uhope.ancha.dto.RegionDTO;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface AnzhaInvestigationsMapper extends Mapper<AnzhaInvestigations> {
    List<RegionDTO> districtlist();


    List<AnzhaInvestigations> selectList(@Param("schemeid") String schemeid,@Param("d") String date, @Param("region") String region, @Param("status") String status);

    List<AnzhaInvestigations> selectListMonth(@Param("month") String month);

    String selectReachName(@Param("r")String r);

    String selectRole(@Param("id")String id);

    String selectRoleId(@Param("rolename")String rolename);

    List<String> findRiverByRegion(String regionid);

    RegionDTO selectRegionName(String s);
}