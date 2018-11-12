package com.uhope.ancha.web;

import com.github.pagehelper.PageInfo;
import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.domain.AnzhaScheme;
import com.uhope.ancha.dto.AnzhaSchemeDTO;
import com.uhope.ancha.service.AnzhaBulletinService;
import com.uhope.ancha.service.AnzhaSchemeService;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author :shujihui
 * @Date : 2018/10/29
 * @Time : 17:20
 * Aim :
 */

@RestController
@RequestMapping("/v1/AnzhaScheme")
public class AnzhaSchemeController {
    @Autowired
    private AnzhaSchemeService anzhaSchemeService;

    @Autowired
    private AnzhaBulletinService anzhaBulletinService;

    @PostMapping("/add")
    public Result<AnzhaScheme> add(@RequestParam String title,
                                   @RequestParam String issue,
                                   @RequestParam String createuser,
                                   String content, String assessory) {
        AnzhaBulletin anzhaBulletin = new AnzhaBulletin();
        anzhaBulletin.setId(UUID.randomUUID().toString().replaceAll("-",""));
        anzhaBulletin.setMonth(issue);
        anzhaBulletin.setTitle(issue+"月份通报");
        anzhaBulletin.setStatus("0");
        anzhaBulletinService.insert(anzhaBulletin);
        AnzhaScheme anzhaScheme = new AnzhaScheme();
        anzhaScheme.setTitle(title);
        anzhaScheme.setCreateuser(createuser);
        anzhaScheme.setIssue(issue);
        anzhaScheme.setContent(content);
        anzhaScheme.setAssessory(assessory);
        anzhaScheme.setBulletinid(anzhaBulletin.getId());
        anzhaSchemeService.insert(anzhaScheme);
        anzhaBulletin.setSchemeid(anzhaScheme.getId());
        anzhaBulletinService.update(anzhaBulletin);
        return ResponseMsgUtil.success(anzhaScheme);
    }

    @GetMapping("/detail")
    public Result<AnzhaScheme> detail(@RequestParam String id) {
        AnzhaScheme anzhaScheme = anzhaSchemeService.get(id);
        return ResponseMsgUtil.success(anzhaScheme);
    }

    @PutMapping("/update")
    public Result<AnzhaScheme> update(@RequestParam String id,
                                      @RequestParam String title,
                                      @RequestParam String issue,
                                      @RequestParam String createuser,
                                      String content, String assessory) {

        AnzhaScheme anzhaScheme = new AnzhaScheme();
        anzhaScheme.setId(id);
        anzhaScheme.setTitle(title);
        anzhaScheme.setCreateuser(createuser);
        anzhaScheme.setIssue(issue);
        anzhaScheme.setContent(content);
        anzhaScheme.setAssessory(assessory);
        anzhaSchemeService.update(anzhaScheme);
        return ResponseMsgUtil.success(anzhaScheme);
    }

    //查询是否这个月份是否创建过方案
    @GetMapping("/selectHave")
    public Result<String> selectHave(@RequestParam String issue) {
        AnzhaScheme anzhaScheme = anzhaSchemeService.selectHave(issue);
        String msg="没有";
        if (anzhaScheme!=null){
            msg="有";
        }
        return ResponseMsgUtil.success(msg);
    }

    @GetMapping("/list")
    public Result<PageInfo<AnzhaSchemeDTO>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                 @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                 String title, String issue) {
        return ResponseMsgUtil.success(anzhaSchemeService.list(pageNumber, pageSize, title,issue));
    }


}
