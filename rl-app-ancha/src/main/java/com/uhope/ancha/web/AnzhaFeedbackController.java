package com.uhope.ancha.web;

import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.service.AnzhaBulletinService;
import com.uhope.ancha.service.AnzhaFeedbackService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.uip.fm.config.FmConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @Author :shujihui
 * @Date : 2018/10/25
 * @Time : 19:55
 * Aim :
 */

@RestController
@RequestMapping("/v1/AnzhaFeedback")
public class AnzhaFeedbackController {
    @Autowired
    private AnzhaFeedbackService anzhaFeedbackService;
    @Autowired
    private AnzhaBulletinService anzhaBulletinService;

    @PostMapping("/add")
    public Result<AnzhaFeedback> add(@RequestParam String whether,
                                     @RequestParam String feedbackTime,
                                     @RequestParam String bulletinid,
                                     @RequestParam String status,
                                     String describe, String filePath) throws ParseException {
        AnzhaFeedback anzhaFeedback = new AnzhaFeedback();
        anzhaFeedback.setId(UUID.randomUUID().toString().replaceAll("-",""));
        anzhaFeedback.setWhether(whether);
        anzhaFeedback.setFeedbackTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(feedbackTime));
        anzhaFeedback.setDescribe(describe);
        anzhaFeedback.setAssessory(filePath);
        anzhaFeedback.setBulletinid(bulletinid);
        anzhaFeedbackService.insertAnzhaFeedback(anzhaFeedback);
        AnzhaBulletin anzhaBulletin = new AnzhaBulletin();
        anzhaBulletin.setStatus(status);
        anzhaBulletin.setId(bulletinid);
        anzhaBulletinService.update(anzhaBulletin);
        return ResponseMsgUtil.success(anzhaFeedback);
    }

    @GetMapping("/detail")
    public Result<AnzhaFeedback> detail(@RequestParam String bulletinid) {
        AnzhaFeedback anzhaFeedback = anzhaFeedbackService.selectOneById(bulletinid);
        anzhaFeedback.setAssessory(FmConfig.getAgentUrl() + anzhaFeedback.getAssessory());
        return ResponseMsgUtil.success(anzhaFeedback);
    }

}
