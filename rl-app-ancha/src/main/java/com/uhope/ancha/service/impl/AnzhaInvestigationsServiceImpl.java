package com.uhope.ancha.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.uhope.ancha.domain.AnzhaInvestigations;
import com.uhope.ancha.dto.AnzhaInvestigationsDTO;
import com.uhope.ancha.dto.PersonnelDTO;
import com.uhope.ancha.dto.RegionDTO;
import com.uhope.ancha.mapper.AnzhaInvestigationsMapper;
import com.uhope.ancha.mapper.TemplateMapper;
import com.uhope.ancha.service.AnzhaInvestigationsService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class AnzhaInvestigationsServiceImpl extends AbstractService<AnzhaInvestigations, AnzhaInvestigationsDTO, String> implements AnzhaInvestigationsService {
    @Resource
    private AnzhaInvestigationsMapper anzhaInvestigationsMapper;

    @Override
    public List<RegionDTO> districtlist() {
        return anzhaInvestigationsMapper.districtlist();
    }

    @Override
    public PageInfo<AnzhaInvestigations> list(Integer pageNumber, Integer pageSize, String schemeid,String date, String region, String status,String regionid) {
        PageHelper.startPage(pageNumber, pageSize);
        List<AnzhaInvestigations> list=anzhaInvestigationsMapper.selectList(schemeid,date,region,status);
        List<AnzhaInvestigations> anzhaInvestigations=new ArrayList<>();
        if(regionid==null || "".equals(regionid)){
            PageInfo<AnzhaInvestigations> pageInfo = new PageInfo(list);
            return pageInfo;
        }
        if(list!=null && list.size()>0 ){
            for (AnzhaInvestigations anzhaInvestigations1:list) {
                 if(anzhaInvestigations1.getRegionid().contains(regionid)){
                     anzhaInvestigations.add(anzhaInvestigations1);
                 }
            }
        }
        PageInfo<AnzhaInvestigations> pageInfo = new PageInfo(anzhaInvestigations);
        return pageInfo;
    }

    /*@Override
    public List<AnzhaInvestigations> listMonth(String month) {
        List<AnzhaInvestigations> list=anzhaInvestigationsMapper.selectListMonth(month);
        for (AnzhaInvestigations anzhaInvestigations:list) {
            String[] reachs=anzhaInvestigations.getReachname().split(",");
            String reachName="";
            for (String r:reachs) {
                reachName+=anzhaInvestigationsMapper.selectReachName(r)+",";
            }
            anzhaInvestigations.setReachname(reachName);
        }
        return list;
    }*/

    @Override
    public List<String> findRiverByRegion(String regionid) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("regionid", regionid);
        return anzhaInvestigationsMapper.findRiverByRegion(regionid);
    }

    @Override
    public String selectReachName(String r) {
        return anzhaInvestigationsMapper.selectReachName(r);
    }

    @Override
    public String selectRole(String id) {
        return anzhaInvestigationsMapper.selectRole(id);
    }

    @Override
    public String selectRoleId(String rolename) {
        return anzhaInvestigationsMapper.selectRoleId(rolename);
    }

    @Override
    public RegionDTO selectRegionName(String s) {
        return anzhaInvestigationsMapper.selectRegionName(s);
    }
}
