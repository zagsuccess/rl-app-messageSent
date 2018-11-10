package com.uhope.ancha.web;

import com.github.pagehelper.PageInfo;
import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.dto.AnzhaBulletinDTO;
import com.uhope.ancha.service.AnzhaBulletinService;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author :shujihui
 * @Date : 2018/10/25
 * @Time : 14:26
 * Aim :
 */

@RestController
@RequestMapping("/v1/AnzhaBulletin")
public class AnzhaBulletinController {
    @Autowired
    private AnzhaBulletinService anzhaBulletinService;


    //当暗查暗访计划创建完的时候就会创建一个通报
    /*@PostMapping("/add")
    public Result<AnzhaBulletin> add(@RequestParam String month) {
        AnzhaBulletin anzhaBulletin = new AnzhaBulletin();
        anzhaBulletin.setMonth(month);
        anzhaBulletin.setTitle(month+"月份通报");
        anzhaBulletin.setStatus("0");
        anzhaBulletinService.insert(anzhaBulletin);
        return ResponseMsgUtil.success(anzhaBulletin);
    }*/

    //查询是否这个月份是否创建过通报
    /*@GetMapping("/selectHave")
    public Result<String> selectHave(@RequestParam String month) {
        AnzhaBulletin anzhaBulletin = anzhaBulletinService.selectHave(month);
        String msg="没有";
        if (anzhaBulletin!=null){
            msg="有";
        }
        return ResponseMsgUtil.success(msg);
    }*/

    @GetMapping("/detail")
    public Result<AnzhaBulletin> detail(@RequestParam String id) {
        AnzhaBulletin anzhaBulletin = anzhaBulletinService.get(id);
        return ResponseMsgUtil.success(anzhaBulletin);
    }

    @PutMapping("/update")
    public Result<AnzhaBulletin> update(@RequestParam String id,
                                         String title,
                                         String month,
                                        String accessory,String content) {

        AnzhaBulletin anzhaBulletin = new AnzhaBulletin();
        anzhaBulletin.setId(id);
        anzhaBulletin.setTitle(title);
        anzhaBulletin.setMonth(month);
        anzhaBulletin.setAccessory(accessory);
        anzhaBulletin.setContent(content);
        anzhaBulletin.setStatus("1");
        anzhaBulletinService.update(anzhaBulletin);
        return ResponseMsgUtil.success(anzhaBulletin);
    }

    @GetMapping("/list")
    public Result<PageInfo<AnzhaBulletinDTO>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                   @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                   String month, String title, String status, String num) {
        return ResponseMsgUtil.success(anzhaBulletinService.list(pageNumber, pageSize, month, title,status,num));
    }
}
