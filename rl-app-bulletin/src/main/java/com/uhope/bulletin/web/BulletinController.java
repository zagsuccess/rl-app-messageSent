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
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
public class    BulletinController {

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

    /*@PostMapping("/upload")
    public Result<FileItem> upload(
            @RequestParam MultipartFile file,
            @RequestParam String fileName) throws IOException {
        byte[] data = file.getBytes();
        Result<FileItem> files = fileManagerClient.upload(data, fileName);
        files.getData().setUrl(URLDecoder.decode(files.getData().getUrl()));
        return files;
    }*/

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam(required = true) MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String fileName = file.getOriginalFilename();
        Result<FileItem> itemResult = fileManagerClient.upload(bytes, fileName);
        return ResponseMsgUtil.isSuccess(itemResult) ? itemResult : ResponseMsgUtil.failure();
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

    @GetMapping ("/lookload")
    public void lookload(@RequestParam String attandUrl,HttpServletResponse res) {
        try{
            String[] str=attandUrl.split("_");
            String fileName=str[1];
        // 最需要注意的是  告诉浏览器只保存文件  不用在页面展示
        res.setHeader("content-disposition", "inline;filename="+URLEncoder.encode(fileName,"UTF-8"));
            byte[] bs = fileManagerClient.download(attandUrl).getData();
            res.getOutputStream().write(bs);
            res.flushBuffer();
        }catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
