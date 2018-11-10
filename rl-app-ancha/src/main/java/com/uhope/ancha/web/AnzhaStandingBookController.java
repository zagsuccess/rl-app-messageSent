package com.uhope.ancha.web;

import com.uhope.ancha.domain.AnzhaReport;
import com.uhope.ancha.domain.AnzhaStandingBook;
import com.uhope.ancha.service.AnzhaStandingBookService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.apache.ibatis.javassist.CtClass.version;

/**
 * @Author :shujihui
 * @Date : 2018/10/30
 * @Time : 15:14
 * Aim :
 */

@RestController
@RequestMapping("/v1/AnzhaStandingBook")
public class AnzhaStandingBookController {
    @Autowired
    private AnzhaStandingBookService anzhaStandingBookService;

    @PostMapping("/add")
    public Result<AnzhaStandingBook> add(@RequestParam String bulletinid,
                                         @RequestParam String turnarounddate,
                                         @RequestParam String name,
                                         String content,String object,
                                         String duty,String accountabilitytype,
                                         String accessory,String remark) throws ParseException {
        AnzhaStandingBook anzhaStandingBook = new AnzhaStandingBook();
        anzhaStandingBook.setBulletinid(bulletinid);
        anzhaStandingBook.setTurnarounddate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(turnarounddate));
        anzhaStandingBook.setName(name);
        anzhaStandingBook.setContent(content);
        anzhaStandingBook.setObject(object);
        anzhaStandingBook.setDuty(duty);
        anzhaStandingBook.setAccountabilitytype(accountabilitytype);
        anzhaStandingBook.setAccessory(accessory);
        anzhaStandingBook.setRemark(remark);
        anzhaStandingBookService.insert(anzhaStandingBook);
        return ResponseMsgUtil.success(anzhaStandingBook);
    }

    @GetMapping("/detail")
    public Result<AnzhaStandingBook> detail(@RequestParam String id) {
        AnzhaStandingBook anzhaStandingBook = anzhaStandingBookService.get(id);
        return ResponseMsgUtil.success(anzhaStandingBook);
    }

    @PutMapping("/update")
    public Result<AnzhaStandingBook> update(@RequestParam String id,
                                            @RequestParam String turnarounddate,
                                            @RequestParam String name,
                                            String content,String object,
                                            String duty,String accountabilitytype,
                                            String accessory,String remark) throws ParseException {
        AnzhaStandingBook anzhaStandingBook = new AnzhaStandingBook();
        anzhaStandingBook.setId(id);
        anzhaStandingBook.setTurnarounddate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(turnarounddate));
        anzhaStandingBook.setName(name);
        anzhaStandingBook.setContent(content);
        anzhaStandingBook.setObject(object);
        anzhaStandingBook.setDuty(duty);
        anzhaStandingBook.setAccountabilitytype(accountabilitytype);
        anzhaStandingBook.setAccessory(accessory);
        anzhaStandingBook.setRemark(remark);
        anzhaStandingBookService.update(anzhaStandingBook);
        return ResponseMsgUtil.success(anzhaStandingBook);
    }

    @GetMapping("/list")
    public Result<List<AnzhaStandingBook>> list(@RequestParam String bulletinid ) {

        return ResponseMsgUtil.success(anzhaStandingBookService.list(bulletinid));
    }
}
