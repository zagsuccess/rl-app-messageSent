package com.uhope.bulletin.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.bulletin.dto.BulletinDTO;
import com.uhope.converter.client.Converter;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.config.FmConfig;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.bulletin.domain.Bulletin;
import com.uhope.bulletin.service.BulletinService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
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
    @Autowired
    private Converter converter;


    @GetMapping("/list")
    public Result<PageInfo<Bulletin>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                           Integer type,
                                           String post_time) throws ParseException {
        return ResponseMsgUtil.success(bulletinService.list(pageNumber, pageSize, type, post_time));
    }


    @PostMapping("/add")
    public Result<Bulletin> add(Bulletin bulletin) {
        bulletin.setAttand_url(bulletin.getAttand_url());
        bulletin.setDetail(bulletin.getDetail());
        bulletin.setPost_time(bulletin.getPost_time());
        bulletin.setTitle(bulletin.getTitle());
        bulletin.setYear(bulletin.getYear());
        bulletin.setMonth(bulletin.getMonth());
        bulletin.setIssuer(bulletin.getIssuer());
        bulletin.setType(bulletin.getType());
        bulletin.setCreate_time(new Date());
        String detailUrl = bulletin.getAttand_url();
        String tempString="";
        if(detailUrl!= null && "".equals(detailUrl)) {
            tempString = detailUrl.substring(detailUrl.lastIndexOf(".") + 1);
        }
        String url =detailUrl;
        if (tempString.contains("doc")){
            url = converter.startConverter(bulletin.getAttand_url());
        }
        bulletin.setDetail_url(url);
        bulletinService.insert(bulletin);
        return ResponseMsgUtil.success(bulletin);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        bulletinService.remove(id);
        return ResponseMsgUtil.success();
    }

    @PutMapping("/update")
    public Result<Bulletin> update(Bulletin bulletin) {
        bulletin.setTitle(bulletin.getTitle());
        bulletin.setType(bulletin.getType());
        bulletin.setId(bulletin.getId());
        bulletin.setAttand_url(bulletin.getAttand_url());
        bulletin.setIssuer(bulletin.getIssuer());
        bulletin.setPost_time(bulletin.getPost_time());
        bulletin.setYear(bulletin.getYear());
        bulletin.setMonth(bulletin.getMonth());
        bulletin.setDetail(bulletin.getDetail());
        bulletinService.update(bulletin);
        return ResponseMsgUtil.success(bulletin);
    }

    /**
     * 文件上传
     *
     * @param files
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<List<String>> upload(@RequestParam(required = true) MultipartFile files[]) throws IOException {
        List<String>list=new ArrayList<>();
        for (int i=0;i<files.length;i++){
            byte[] bytes = files[i].getBytes();
            String fileName = files[i].getOriginalFilename();
            String lastName = fileName.substring(fileName.lastIndexOf(".") + 1);
            FileItem fileItem = fileManagerClient.upload(bytes, fileName).getData();
            String filePath = fileItem.getVirtualPath();
            /*if (lastName.contains("doc") || lastName.contains("xls")){
                filePath = converter.startConverter(fileItem.getVirtualPath());
            }*/
            list.add(filePath);
        }
        return ResponseMsgUtil.success(list);
    }


    @GetMapping("/detail")
    public Result<BulletinDTO> detail(@RequestParam String id) {
        Bulletin undercover = bulletinService.get(id);
        String name=undercover.getAttand_url();
        String name1=undercover.getDetail_url();
        BulletinDTO bulletinDTO =new BulletinDTO();
        BeanUtils.copyProperties(undercover,bulletinDTO);
        String[] str=bulletinDTO.getAttand_url().split("_");
        String ren="";
        if(str!=null && str.length>1){
             ren = str[1];
        }
        bulletinDTO.setAttand_url(FmConfig.getAgentUrl() +name);
        bulletinDTO.setDetail_url(FmConfig.getAgentUrl() +name1);
        bulletinDTO.setRen(ren);
        return ResponseMsgUtil.success(bulletinDTO);
    }

    @GetMapping("/selectByFirst")
    public List<Bulletin> selectByFirst(@RequestParam Integer type) {

        return bulletinService.selectByFirst(type);
    }



    /*@GetMapping ("/lookload")
    public void lookload(@RequestParam String attand_url,HttpServletResponse res) {
        try{
            String[] str=attand_url.split("_");
            String fileName=str[1];
            // 最需要注意的是  告诉浏览器只保存文件  不用在页面展示
            res.setHeader("content-disposition", "inline;filename="+URLEncoder.encode(fileName,"UTF-8"));
            byte[] bs = fileManagerClient.download(attand_url).getData();
            res.getOutputStream().write(bs);
            res.flushBuffer();
        }catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/



}
