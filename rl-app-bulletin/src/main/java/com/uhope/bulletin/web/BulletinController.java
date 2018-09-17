package com.uhope.bulletin.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.bulletin.domain.Bulletin;
import com.uhope.bulletin.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author: StivenYang
 * @date: 2018/9/8
 * @description: 暗查暗访表-Controller类
 */
@RestController
@RequestMapping("/v1/bulletin")
public class BulletinController {

    @Autowired
    private BulletinService bulletinService;

    @Autowired
    private FileManagerClient fileManagerClient;



    @GetMapping("/search")
    public Result<PageInfo<Bulletin>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                           @RequestParam(defaultValue = "")Integer type,
                                           @RequestParam(defaultValue = "")String year,
                                           @RequestParam(defaultValue = "")String month) throws ParseException {
        return ResponseMsgUtil.success(bulletinService.list(pageNumber,pageSize,type,year,month));
    }




    @PostMapping("/add")
    public Result<Bulletin> add(@RequestParam Integer type,
                                @RequestParam String title,
                                @RequestParam String detail,
                                @RequestParam String postTime,
                                @RequestParam String month,
                                @RequestParam String year,
                                @RequestParam String issuer,
                                @RequestParam (value= "file", required = false)MultipartFile file,
                                @RequestParam (defaultValue = "")String fileName) throws Exception{
        Bulletin bulletin=new Bulletin();
        if (file!=null){
            byte[] data=file.getBytes();
            Result<FileItem> files=fileManagerClient.upload(data,fileName);
            bulletin.setAttandUrl(files.getData().getVirtualPath());
        }
        bulletin.setDetail(detail);
        bulletin.setPostTime(postTime);
        bulletin.setTitle(title);
        bulletin.setYear(year);
        bulletin.setMonth(month);
        bulletin.setIssuer(issuer);
        bulletin.setType(type);
        bulletin.setCreateTime(new Date());
        bulletinService.insert(bulletin);
        return ResponseMsgUtil.success(bulletin);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        bulletinService.delete(id);
        return ResponseMsgUtil.success();
    }

    @PutMapping("/update")
    public Result<Bulletin> update(@RequestParam Integer id,
                                   @RequestParam Integer type,
                                   @RequestParam String title,
                                   @RequestParam String issuer,
                                   @RequestParam String postTime,
                                   @RequestParam String year,
                                   @RequestParam String month,
                                   @RequestParam String detail
                                   ){
        Bulletin bulletin=new Bulletin();
        bulletin.setTitle(title);
        bulletin.setType(type);
        bulletin.setId(id);
        bulletin.setIssuer(issuer);
        bulletin.setPostTime(postTime);
        bulletin.setYear(year);
        bulletin.setMonth(month);
        bulletin.setDetail(detail);
        bulletinService.update(bulletin);
        return ResponseMsgUtil.success(bulletinService.findBy("id", id));
    }

    @PostMapping("/upload")
    public Result<Bulletin> upload(@RequestParam Integer id,
                                   @RequestParam MultipartFile file,
                                   @RequestParam String fileName){
        return null;

    }

    @PostMapping("/updateFile")
    public Result<Bulletin> updateFile(@RequestParam Integer id,
                                       @RequestParam (required = false)MultipartFile file,
                                       @RequestParam (defaultValue = "")String fileName) throws IOException {
        Bulletin bulletin = new Bulletin();
        byte[] data = file.getBytes();
        Result<FileItem> files = fileManagerClient.upload(data, fileName);
        bulletin.setAttandUrl(files.getData().getVirtualPath());
        bulletin.setId(id);
        bulletinService.update(bulletin);
        return ResponseMsgUtil.success(bulletinService.findBy("attandUrl", bulletin.getAttandUrl()));
    }

    @GetMapping("/detail")
    public Result<Bulletin> detail(@RequestParam Integer id) {
        Bulletin undercover = bulletinService.detail(id);
        return ResponseMsgUtil.success(undercover);
    }

    @GetMapping("/selectByFirst")
    public Result<Bulletin> selectByFirst(@RequestParam Integer  type) {
        Bulletin bulletin = bulletinService.selectByFirst(type);
        return ResponseMsgUtil.success(bulletin);
    }

}
