package com.uhope.ancha.web;

import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.dto.AnzhaFeedbackDTO;
import com.uhope.ancha.service.AnzhaBulletinService;
import com.uhope.ancha.service.AnzhaFeedbackService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.fm.config.FmConfig;
import com.uhope.uip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result<AnzhaFeedback> add(@RequestParam String whether,
                                     @RequestParam String feedbackTime,
                                     @RequestParam String bulletinid,
                                     String describe, String filePath,String objectid) throws ParseException {
        AnzhaFeedback anzhaFeedback = new AnzhaFeedback();
        anzhaFeedback.setId(UUID.randomUUID().toString().replaceAll("-",""));
        anzhaFeedback.setWhether(whether);
        anzhaFeedback.setFeedbacktime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(feedbackTime));
        anzhaFeedback.setDescription(describe);
        anzhaFeedback.setAssessory(filePath);
        anzhaFeedback.setBulletinid(bulletinid);
        anzhaFeedback.setObjectid(objectid);
        anzhaFeedback.setStatus("1");
        anzhaFeedbackService.insert(anzhaFeedback);
        return ResponseMsgUtil.success(anzhaFeedback);
    }

    @GetMapping("/detail")
    public Result<List<AnzhaFeedbackDTO>> detail(@RequestParam String bulletinid,
                                                 String objectid) {
        List<AnzhaFeedbackDTO> anzhaFeedback = anzhaFeedbackService.selectOneById(bulletinid,objectid);
        if (anzhaFeedback!=null && anzhaFeedback.size()>0 ){
            for (AnzhaFeedbackDTO anzhaFeedbackDTO:anzhaFeedback) {
                anzhaFeedbackDTO.setAssessory(FmConfig.getAgentUrl() + anzhaFeedbackDTO.getAssessory());
                Result<UserDTO> id = userService.getById(anzhaFeedbackDTO.getObjectid());
                if(id.getData()!=null){
                    anzhaFeedbackDTO.setObjectname(id.getData().getName());
                }
            }
        }
        return ResponseMsgUtil.success(anzhaFeedback);
    }

    @GetMapping("/selectHaveyi")
    public Result<String> selectHaveyi(@RequestParam String bulletinid,
                                       @RequestParam String objectid) {
        String s = anzhaFeedbackService.selectHaveyi(bulletinid, objectid);
        return ResponseMsgUtil.success(s);
    }





}
