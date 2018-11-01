package com.uhope.spotcheck.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.spotcheck.domain.ScSpotcheck;
import com.uhope.spotcheck.domain.ScSpotcheckProblem;
import com.uhope.spotcheck.dto.CheckResultDTO;
import com.uhope.spotcheck.dto.ProblemTypeDTO;
import com.uhope.spotcheck.dto.RegionDTO;
import com.uhope.spotcheck.service.ScSpotcheckProblemService;
import com.uhope.spotcheck.service.ScSpotcheckService;
import com.uhope.spotcheck.utils.CommonUtil;
import com.uhope.uip.dto.RoleDTO;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.uip.service.RoleService;
import com.uhope.uip.service.TokenService;
import com.uhope.uip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/24
 * @description: 抽查模块接口
 */
@RestController
@RequestMapping("v1/spotcheck")
public class ScSpotcheckController {

    @Autowired
    private ScSpotcheckProblemService scSpotcheckProblemService;
    @Autowired
    private ScSpotcheckService scSpotcheckService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private FileManagerClient fileManagerClient;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 新增-抽查记录
     *
     * @param scSpotcheck
     * @param request
     * @return
     */
    @PostMapping("/addSpotcheck")
    public Result<ScSpotcheck> addSpotcheck(ScSpotcheck scSpotcheck, HttpServletRequest request) {
        scSpotcheck.setCreateTime(new Date());
        scSpotcheck.setStatus(0);
        scSpotcheck.setCreator(tokenService.getUserIdByRequest(request));
        scSpotcheckService.insert(scSpotcheck);
        return ResponseMsgUtil.success(scSpotcheck);
    }

    /**
     * 新增-抽查问题
     *
     * @param scSpotcheckProblem
     * @param request
     * @return
     */
    @PostMapping("/addSpotcheckProblem")
    public Result<ScSpotcheckProblem> addSpotcheckProblem(ScSpotcheckProblem scSpotcheckProblem, HttpServletRequest request) {
        scSpotcheckProblem.setCreateTime(new Date());
        scSpotcheckProblem.setCreatorId(tokenService.getUserIdByRequest(request));
        scSpotcheckProblemService.insert(scSpotcheckProblem);
        return ResponseMsgUtil.success(scSpotcheckProblem);
    }

    /**
     * 查详情-抽查记录
     *
     * @param id
     * @return
     */
    @GetMapping("/detailSpotcheck")
    public Result<ScSpotcheck> detailSpotcheck(String id) {
        return ResponseMsgUtil.success(scSpotcheckService.get(id));
    }

    /**
     * 查详情-抽查问题记录
     *
     * @param id 问题id
     * @return 抽查问题的详情
     */
    @GetMapping("/detailSpotcheckProblem")
    public Result<ScSpotcheckProblem> detailSpotcheckProblem(String id) {
        return ResponseMsgUtil.success(scSpotcheckProblemService.get(id));
    }

    /**
     * 修改-抽查记录
     *
     * @param spotcheck
     * @return
     */
    @PatchMapping("/updateSpotcheck")
    public Result<ScSpotcheck> updateSpotcheck(ScSpotcheck spotcheck) {
        scSpotcheckService.update(spotcheck);
        return ResponseMsgUtil.success(scSpotcheckService.get(spotcheck.getId()));
    }

    /**
     * 修改-确认并派发任务
     *
     * @param id
     * @return
     */
    @PatchMapping("/doDistribute")
    public Result<ScSpotcheck> doDistribute(String id) {
        ScSpotcheck spotcheck = scSpotcheckService.get(id);
        spotcheck.setStatus(1);
        scSpotcheckService.update(spotcheck);
        return ResponseMsgUtil.success(spotcheck);
    }

    /**
     * 查找-抽查记录列表
     *
     * @param status
     * @param checkTime
     * @param regionName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/listSpotcheck")
    public Result<PageInfo<ScSpotcheck>> listSpotcheck(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "checkTime", required = false) String checkTime,
            @RequestParam(name = "regionName", required = false) String regionName,
            @RequestParam(name = "appSearch", required = false) String appSearch,
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        if (appSearch != null && appSearch != ""){
            Condition appCondition = new Condition(ScSpotcheck.class);
            Example.Criteria appCriteria = appCondition.createCriteria();
            appCriteria.orLike("title", "%" + appSearch + "%");
            appCriteria.orLike("regionName", "%" + appSearch + "%");
            appCriteria.orLike("checkRiver", "%" + appSearch + "%");
            appCondition.orderBy("createTime");
            List<ScSpotcheck> appList = scSpotcheckService.findByCondition(appCondition);
            PageInfo pageInfo = new PageInfo(appList);
            return ResponseMsgUtil.success(pageInfo);
        }
        Condition condition = new Condition(ScSpotcheck.class);
        Example.Criteria criteria = condition.createCriteria();
        if (checkTime != null && checkTime != "") {
            criteria.andCondition("check_date = '" + checkTime + "'");
        }
        if (status != null && status != "") {
            criteria.andCondition("status=" + status);
        }
        if (regionName != null && regionName != "") {
            criteria.andLike("regionName", "%" + regionName + "%");
        }
        condition.orderBy("createTime").desc();
        List<ScSpotcheck> list = scSpotcheckService.findByCondition(condition);
        PageInfo<ScSpotcheck> pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    /**
     * 查找-下发人员列表
     *
     * @return
     */
    @GetMapping("/listSendPerson")
    public Result<com.uhope.base.dto.PageInfo<UserDTO>> listSendPerson() {
        com.uhope.base.dto.PageInfo<RoleDTO> roleDTOPageInfo = CommonUtil.getFeigionServiceResultData(roleService.queryRoleList(null, null, null, 0, 0));
        System.out.println(roleDTOPageInfo);
        String countryID = scSpotcheckProblemService.findRoleIdByName("市河长办");
        Result<com.uhope.base.dto.PageInfo<UserDTO>> list = userService.queryUserList(null, null, null, countryID, 0, 0);
        return list;
    }

    /**
     * 查找-所有的区
     *
     * @return
     */
    @GetMapping("/listRegion")
    public Result<List<RegionDTO>> listRegion() {
        //查找所有的16个区
        return ResponseMsgUtil.success(scSpotcheckProblemService.listRegion());
    }

    /**
     * 查找-所选区对应的所有河段
     *
     * @param regionStr
     * @return
     */
    @GetMapping("/listRiver")
    public Result<List<String>> listRiver(String regionStr) {
        List<String> riverList = Lists.newArrayList();
        //得到选中的区字符串，例如：河东区，河西区，津南区   字符串以都好分割
        List<String> regionlist = Splitter.on(",").trimResults().splitToList(regionStr);
        //根据区名取得所对应的所有河段
        for (String regionName : regionlist) {
            List<String> tempList = scSpotcheckService.findRiverByRegion(regionName);
            riverList.addAll(tempList);
        }
        //返回所有的河段
        return ResponseMsgUtil.success(riverList);
    }

    /**
     * 列出所有扣分类型，需要返回类型id，类型名，扣分方式，扣分数值，
     */
    @GetMapping("/listProblemType")
    public Result<List<ProblemTypeDTO>> listProblemType() {
        return ResponseMsgUtil.success(scSpotcheckService.listProblemType());
    }

    /**
     * 查找-抽查问题详情、检查结果
     *
     * @param id
     */
    @GetMapping("/detailCheckResult")
    public Result<CheckResultDTO> detailCheckResult(@RequestParam String id) throws ParseException {
        //1. 查找抽查计划
        ScSpotcheck scSpotcheck = scSpotcheckService.get(id);
        //2. 找到对应的问题列表
        Condition condition = new Condition(ScSpotcheckProblem.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("spotcheck_id='" + id + "'");
        List<ScSpotcheckProblem> problems = scSpotcheckProblemService.findByCondition(condition);

        //3. 组装DTO，返回
        CheckResultDTO resultDTO = new CheckResultDTO();
        resultDTO.setCheckDate(scSpotcheck.getCheckDate());
        resultDTO.setProblemList(problems);
        resultDTO.setSendPerson(scSpotcheck.getSendPerson());

        return ResponseMsgUtil.success(resultDTO);
    }

    /**
     * 查找-所有问题列表
     * @return
     */
    @GetMapping("/findSpotcheckProblemList")
    public Result<List<ScSpotcheckProblem>> listSpotcheckProblem(String id){
        Condition condition = new Condition(ScSpotcheckProblem.class);
        condition.createCriteria().andEqualTo("spotcheckId", id);
        condition.orderBy("createTime");
        return ResponseMsgUtil.success(scSpotcheckProblemService.findByCondition(condition));
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<FileItem> upload(@RequestParam MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        Result<FileItem> fileItemResult = fileManagerClient.upload(file.getBytes(), filename);
        return fileItemResult;
    }
}
