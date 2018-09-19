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

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


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


    @GetMapping("/list")
    public Result<PageInfo<Bulletin>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                           Integer type,
                                           String year,
                                           String month) throws ParseException {
        return ResponseMsgUtil.success(bulletinService.list(pageNumber, pageSize, type, year, month));
    }


    @PostMapping("/add")
    public Result<Bulletin> add(@RequestParam Integer type,
                                @RequestParam String title,
                                @RequestParam String detail,
                                @RequestParam String postTime,
                                @RequestParam String month,
                                @RequestParam(defaultValue = "") String year,
                                @RequestParam String issuer,
                                @RequestParam(defaultValue = "") String attandUrl) {
        Bulletin bulletin = new Bulletin();
        bulletin.setAttandUrl(attandUrl);
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
                                   @RequestParam(defaultValue = "") String year,
                                   @RequestParam String month,
                                   @RequestParam String detail,
                                   @RequestParam(defaultValue = "") String attandUrl
    ) {
        Bulletin bulletin = new Bulletin();
        bulletin.setTitle(title);
        bulletin.setType(type);
        bulletin.setId(id);
        bulletin.setAttandUrl(attandUrl);
        bulletin.setIssuer(issuer);
        bulletin.setPostTime(postTime);
        bulletin.setYear(year);
        bulletin.setMonth(month);
        bulletin.setDetail(detail);
        bulletinService.update(bulletin);
        return ResponseMsgUtil.success(bulletinService.findBy("id", id));
    }

    @PostMapping("/upload")
    public Result<FileItem> upload(
            @RequestParam MultipartFile file,
            @RequestParam String fileName) throws IOException {
        byte[] data = file.getBytes();
        Result<FileItem> files = fileManagerClient.upload(data, fileName);
        files.getData().setUrl(URLDecoder.decode(files.getData().getUrl()));
        return files;
    }


    @GetMapping("/detail")
    public Result<Bulletin> detail(@RequestParam Integer id) {
        Bulletin undercover = bulletinService.detail(id);
        return ResponseMsgUtil.success(undercover);
    }

    @GetMapping("/selectByFirst")
    public List<Bulletin> selectByFirst(@RequestParam(defaultValue = "") Integer type) {

        return bulletinService.selectByFirst(type);
    }

    @GetMapping ("/download")
    public void download(@RequestParam String attandUrl, HttpServletResponse res) throws IOException {
        byte[] bs = fileManagerClient.download(attandUrl).getData();
        res.getOutputStream().write(bs);
        res.flushBuffer();
    }
}
