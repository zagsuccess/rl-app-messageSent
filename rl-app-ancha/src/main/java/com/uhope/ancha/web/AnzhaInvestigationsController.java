package com.uhope.ancha.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.uhope.ancha.domain.AnzhaInvestigations;
import com.uhope.ancha.dto.RegionDTO;
import com.uhope.ancha.service.AnzhaInvestigationsService;
import com.uhope.ancha.utils.CommonUtil;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
import com.uhope.uip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author :shujihui
 * @Date : 2018/10/24
 * @Time : 15:21
 * Aim :
 */

@RestController
@RequestMapping("/v1/AnzhaInvestigations")
public class AnzhaInvestigationsController {
    @Autowired
    private AnzhaInvestigationsService anzhaInvestigationsService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/add")
    public Result<AnzhaInvestigations> add(@RequestParam String title,
                                           @RequestParam String date,
                                           @RequestParam String regionid,
                                           @RequestParam String reachname,
                                           String coordinate,
                                           @RequestParam String schemeid,
                                            String leader,String personnel) {
        AnzhaInvestigations anzhaInvestigations = new AnzhaInvestigations();
        anzhaInvestigations.setTitle(title);
        anzhaInvestigations.setSchemeid(schemeid);
        anzhaInvestigations.setDate(date);
        String[] str=regionid.split(",");
        String regionnames="";
        for (String s:str) {
            RegionDTO regionDTO = anzhaInvestigationsService.selectRegionName(s);
            regionnames+=regionDTO.getRegionName()+",";
        }
        anzhaInvestigations.setRegionid(regionid);
        anzhaInvestigations.setRegionname(regionnames.substring(0,regionnames.length()-1));
        anzhaInvestigations.setReachname(reachname);
        anzhaInvestigations.setCoordinate(coordinate);
        anzhaInvestigations.setLeader(leader);
        anzhaInvestigations.setStatus("0");
        anzhaInvestigations.setPersonnel(personnel);
        anzhaInvestigationsService.insert(anzhaInvestigations);
        return ResponseMsgUtil.success(anzhaInvestigations);
    }

    //查询所有的区
    @GetMapping("/districtlist")
    public Result<List<RegionDTO>> districtlist(){
        return ResponseMsgUtil.success(anzhaInvestigationsService.districtlist());
    }

    //通过区查询所有的河道
    @GetMapping("/reachlist")
    public Result<List<String>> reachlist(String regionStr) {
        List<String> riverList = Lists.newArrayList();
        //得到选中的区字符串，例如：河东区，河西区，津南区   字符串以都好分割
        List<String> regionlist = Splitter.on(",").trimResults().splitToList(regionStr);
        //根据区名取得所对应的所有河段
        for (String regionid : regionlist) {
            List<String> tempList = anzhaInvestigationsService.findRiverByRegion(regionid);
            riverList.addAll(tempList);
        }
        //返回所有的河段
        return ResponseMsgUtil.success(riverList);
    }

    //查询市河长办中所有的人员账号
    @GetMapping("/selectPersonnel")
    public Result<com.uhope.base.dto.PageInfo<UserDTO>> selectPersonnel(){
        String roleId=anzhaInvestigationsService.selectRoleId("市河长办");
       Result<com.uhope.base.dto.PageInfo<UserDTO>> list = userService.queryUserList(null,null,null,roleId,0,0);
        return list;
    }

    @GetMapping("/detail")
    public Result<AnzhaInvestigations> detail(@RequestParam String id) {
        AnzhaInvestigations anzhaInvestigations = anzhaInvestigationsService.get(id);
        return ResponseMsgUtil.success(anzhaInvestigations);
    }

    @PutMapping("/update")
    public Result<AnzhaInvestigations> update(@RequestParam String id,
                                              @RequestParam String title,
                                              @RequestParam String date,
                                              @RequestParam String regionid,
                                              @RequestParam String reachname,
                                               String coordinate,
                                              String leader,String personnel) {

        AnzhaInvestigations anzhaInvestigations = new AnzhaInvestigations();
        anzhaInvestigations.setId(id);
        anzhaInvestigations.setTitle(title);
        anzhaInvestigations.setDate(date);
        String[] str=regionid.split(",");
        String regionnames="";
        for (String s:str) {
            RegionDTO regionDTO = anzhaInvestigationsService.selectRegionName(s);
            regionnames+=regionDTO.getRegionName()+",";
        }
        anzhaInvestigations.setRegionid(regionid);
        anzhaInvestigations.setRegionname(regionnames.substring(0,regionnames.length()-1));
        anzhaInvestigations.setReachname(reachname);
        anzhaInvestigations.setCoordinate(coordinate);
        anzhaInvestigations.setLeader(leader);
        anzhaInvestigations.setPersonnel(personnel);
        anzhaInvestigationsService.update(anzhaInvestigations);
        return ResponseMsgUtil.success(anzhaInvestigations);
    }

    @GetMapping("/list")
    public Result<PageInfo<AnzhaInvestigations>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                      @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                      String schemeid,
                                                      String date, String region, String status) {

        return ResponseMsgUtil.success(anzhaInvestigationsService.list(pageNumber,pageSize,schemeid,date,region,status));
    }


    //查询每个月份通报下对应的暗查暗访任务
   /* @GetMapping("/listMonth")
    public Result<List<AnzhaInvestigations>> listMonth(@RequestParam String month){
        return ResponseMsgUtil.success(anzhaInvestigationsService.listMonth(month));
    }*/

    //修改状态为检查中
    @PutMapping("/updateStatus")
    public Result<AnzhaInvestigations> updateStatus(@RequestParam String id){
        AnzhaInvestigations anzhaInvestigations = new AnzhaInvestigations();
        anzhaInvestigations.setId(id);
        anzhaInvestigations.setStatus("1");
        anzhaInvestigationsService.update(anzhaInvestigations);
        return ResponseMsgUtil.success(anzhaInvestigations);
    }

    //查询用户所拥有的任务
    @GetMapping("/selectTaskById")
    public Result<PageInfo<AnzhaInvestigations>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                      @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                      @RequestParam String id) {
        PageHelper.startPage(pageNumber, pageSize);
        List<AnzhaInvestigations> list = anzhaInvestigationsService.find();
        List<AnzhaInvestigations> anlist=new ArrayList<>();
        for (AnzhaInvestigations  anzhaInvestigations:list) {
            String[] str=anzhaInvestigations.getPersonnel().split(",");
            for (String s:str) {
                if(id.equals(s)){
                    anlist.add(anzhaInvestigations);
                }
            }
        }
        PageInfo<AnzhaInvestigations> pageInfo = new PageInfo(anlist);
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/userinfo")
    public Result<String> userinfo(HttpServletRequest request){
        //获取当前用户信息
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }
        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade="00";

        if("市河长办".equals(anzhaInvestigationsService.selectRole(userDTO.getId()))){
            grade="02";
        }

        if("河长办".equals(anzhaInvestigationsService.selectRole(userDTO.getId()))){
            grade="05";
        }

        return ResponseMsgUtil.success(grade);
    }


}
