package com.uhope.ancha.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.domain.AnzhaScheme;
import com.uhope.ancha.domain.Template;
import com.uhope.ancha.dto.AnzhaBulletinDTO;
import com.uhope.ancha.dto.TemplateDTO;
import com.uhope.ancha.mapper.AnzhaBulletinMapper;
import com.uhope.ancha.mapper.AnzhaFeedbackMapper;
import com.uhope.ancha.mapper.TemplateMapper;
import com.uhope.ancha.service.AnzhaBulletinService;
import com.uhope.ancha.service.TemplateService;
import com.uhope.core.AbstractService;
import com.uhope.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class AnzhaBulletinServiceImpl extends AbstractService<AnzhaBulletin, AnzhaBulletinDTO, String> implements AnzhaBulletinService {
    @Resource
    private AnzhaBulletinMapper anzhaBulletinMapper;
    @Autowired
    protected Mapper<AnzhaScheme> mapper;
    @Resource
    private AnzhaFeedbackMapper anzhaFeedbackMapper;

    @Override
    public PageInfo<AnzhaBulletinDTO> list(Integer pageNumber, Integer pageSize, String month, String title, String status, String num,String regionid,String objectid) {
        PageHelper.startPage(pageNumber, pageSize);
        List<AnzhaBulletinDTO> list=anzhaBulletinMapper.selectList(month,title,status,num);
        for (AnzhaBulletinDTO anzhaBulletinDTO:list ) {
            if (anzhaBulletinDTO != null) {
                AnzhaScheme anzhaScheme = mapper.selectByPrimaryKey(anzhaBulletinDTO.getSchemeid());
                if (anzhaScheme != null) {
                    anzhaBulletinDTO.setSchemeName(anzhaScheme.getTitle());
                }
                String[] str="".equals(anzhaBulletinDTO.getFeedbackareaid()) ? null:anzhaBulletinDTO.getFeedbackareaid().split(",");
                if (str!=null && str.length>0){
                    anzhaBulletinDTO.setXunum(str.length);
                }
                int yicount=anzhaFeedbackMapper.selectcountByyinum(anzhaBulletinDTO.getId());
                anzhaBulletinDTO.setYinum(yicount);
                String sta = anzhaFeedbackMapper.selectHaveyi(anzhaBulletinDTO.getId(), objectid);
                String[] regions=anzhaBulletinDTO.getFeedbackareaid()== null ? null:anzhaBulletinDTO.getFeedbackareaid().split(",");
                if (regions !=null && regions.length>0){
                    for (String s:regions ) {
                        if(s.equals(regionid) && !"1".equals(sta)){
                            anzhaBulletinDTO.setOr("有");
                        }
                    }
                }

            }
        }
        PageInfo<AnzhaBulletinDTO> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<AnzhaBulletin> selectDeadlineUserh() {
        return anzhaBulletinMapper.selectDeadlineUserh();
    }
}
