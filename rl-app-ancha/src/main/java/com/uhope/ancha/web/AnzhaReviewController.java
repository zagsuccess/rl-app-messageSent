package com.uhope.ancha.web;

import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.domain.AnzhaReview;
import com.uhope.ancha.service.AnzhaBulletinService;
import com.uhope.ancha.service.AnzhaReviewService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.converter.client.Converter;
import com.uhope.uip.fm.config.FmConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author :shujihui
 * @Date : 2018/10/25
 * @Time : 21:46
 * Aim :
 */

@RestController
@RequestMapping("/v1/AnzhaReview")
public class AnzhaReviewController {
    @Autowired
    private AnzhaReviewService anzhaReviewService;
    @Autowired
    private AnzhaBulletinService anzhaBulletinService;
    @Autowired
    private Converter converter;

    @PostMapping("/add")
    public Result<AnzhaReview> add(@RequestParam String whether,
                                   @RequestParam String reviewTime,
                                   @RequestParam String bulletinid,
                                   @RequestParam String status,
                                   String describe, String filePath) throws ParseException {
        AnzhaReview anzhaReview = new AnzhaReview();
        anzhaReview.setId(UUID.randomUUID().toString().replaceAll("-",""));
        anzhaReview.setWhether(whether);
        anzhaReview.setReviewTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reviewTime));
        anzhaReview.setDescription(describe);
        anzhaReview.setAssessory(filePath);
        anzhaReview.setAssessoryyuan(filePath);
        String tempString="";
        if(filePath!= null && !"".equals(filePath)) {
            tempString = filePath.substring(filePath.lastIndexOf(".") + 1);
        }
        String url =filePath;
        if (tempString.contains("doc")){
            url = converter.startConverter(filePath);
        }
        anzhaReview.setAssessory(url);
        anzhaReview.setCreatetime(new Date());
        anzhaReview.setBulletinid(bulletinid);
        anzhaReviewService.insertAnzhaReview(anzhaReview);
        AnzhaBulletin anzhaBulletin = new AnzhaBulletin();
        anzhaBulletin.setStatus(status);
        anzhaBulletin.setId(bulletinid);
        anzhaBulletinService.update(anzhaBulletin);
        return ResponseMsgUtil.success(anzhaReview);
    }

    @GetMapping("/detail")
    public Result<AnzhaReview> detail(@RequestParam String id) {
        AnzhaReview anzhaReview = anzhaReviewService.selectOneById(id);
        anzhaReview.setAssessory(FmConfig.getAgentUrl() + anzhaReview.getAssessory());
        anzhaReview.setAssessoryyuan(FmConfig.getAgentUrl()+anzhaReview.getAssessoryyuan());
        return ResponseMsgUtil.success(anzhaReview);
    }
}
