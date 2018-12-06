package com.uhope.messageSent.web;
import com.uhope.base.constants.Constant;
import com.uhope.messageSent.domain.MsSentReports;
import com.uhope.messageSent.domain.MsWorkReturn;
import com.uhope.messageSent.service.MsSentReportsService;
import com.uhope.messageSent.service.MsWorkReturnService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.lang.String;

/**
 * 工作简报退回原因表-Controller类
 * @author mengaoran on 2018/11/27
 */
@RestController
@RequestMapping("v1/msWorkReturn")
public class MsWorkReturnController {
    @Autowired
    private MsWorkReturnService msWorkReturnService;

    @Autowired
    private MsSentReportsService msSentReportsService;

    @PostMapping("/add")
    public Result<MsWorkReturn> add(@RequestParam String returnReason,
                                    @RequestParam String sentReportsId
    ) {
        MsWorkReturn msWorkReturn=new MsWorkReturn();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        msWorkReturn.setReturnDate(simpleDateFormat.format(new Date()));
        msWorkReturn.setReturnReason(returnReason);
        msWorkReturn.setSentReportsId(sentReportsId);
        MsSentReports msSentReports = msSentReportsService.get(sentReportsId);
        msSentReports.setSentState(3);
        msSentReportsService.update(msSentReports);
        msWorkReturnService.insert(msWorkReturn);
        return ResponseMsgUtil.success(msWorkReturn);
    }

    @GetMapping("/selectList")
    public Result<PageInfo<MsWorkReturn>> selectList(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                           @RequestParam String sentReportsId){
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(MsWorkReturn.class);
        Example.Criteria criteria = condition.createCriteria();
        if (sentReportsId != null && sentReportsId != "") {
            criteria.andLike("sentReportsId",sentReportsId);
        }
        List<MsWorkReturn> list = msWorkReturnService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        msWorkReturnService.remove(id);
        return ResponseMsgUtil.success(null);
    }


    @PutMapping("/update")
    public Result<MsWorkReturn> update(MsWorkReturn msWorkReturn) {
        msWorkReturnService.update(msWorkReturn);
        return ResponseMsgUtil.success(msWorkReturn);
    }


    @GetMapping("/detail")
    public Result<MsWorkReturn> detail(@RequestParam String id) {
        MsWorkReturn msWorkReturn = msWorkReturnService.get(id);
        return ResponseMsgUtil.success(msWorkReturn);
    }

    /**
     * 带分页的查询,根据条件获取菜单列表,可分页，默认查询第一页,一次" + DEFAULT_PAGE_SIZE + "条记录
     *
     * @param pageNumber 默认第{@link Constant#DEFAULT_PAGE_NUMBER}页，如果小于1，则查出所有的记录
     * @param pageSize   分页大小，如果小于1，则查出所有的记录,默认{@link Constant#DEFAULT_PAGE_SIZE}条
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<MsWorkReturn>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        if (pageNumber > 0 && pageSize > 0){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<MsWorkReturn> list = msWorkReturnService.find();
        PageInfo<MsWorkReturn> pageInfo = new PageInfo<>(list);
        return ResponseMsgUtil.success(pageInfo);
    }
}
