package com.uhope.ancha.service;

import com.github.pagehelper.PageInfo;
import com.uhope.ancha.domain.AnzhaInvestigations;
import com.uhope.ancha.dto.AnzhaInvestigationsDTO;
import com.uhope.ancha.dto.PersonnelDTO;
import com.uhope.ancha.dto.RegionDTO;
import com.uhope.core.Service;

import java.util.List;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface AnzhaInvestigationsService extends Service<AnzhaInvestigations, AnzhaInvestigationsDTO, String> {

    public List<RegionDTO> districtlist();

    List<String> findRiverByRegion(String regionid);

    public PageInfo<AnzhaInvestigations> list(Integer pageNumber, Integer pageSize, String schemeid,String date, String region, String status);

    /*public List<AnzhaInvestigations> listMonth(String month);*/

    public String selectReachName(String r);

    public String selectRole(String id);

    public String selectRoleId(String rolename);

    RegionDTO selectRegionName(String s);
}
