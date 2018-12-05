package com.uhope.messageSent.web;
import com.uhope.base.constants.Constant;
import com.uhope.messageSent.domain.MsWeekReturn;
import com.uhope.messageSent.service.MsWeekReturnService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.lang.String;

/**
 * 周动态退回原因表-Controller类
 * @author mengaoran on 2018/11/27
 */
@RestController
@RequestMapping("v1/msWeekReturn")
public class MsWeekReturnController {
    @Autowired
    private MsWeekReturnService msWeekReturnService;


    @PostMapping("/add")
    public Result<MsWeekReturn> add(MsWeekReturn msWeekReturn) {
        msWeekReturnService.insert(msWeekReturn);
        return ResponseMsgUtil.success(msWeekReturn);
    }


    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        msWeekReturnService.remove(id);
        return ResponseMsgUtil.success(null);
    }


    @PutMapping("/update")
    public Result<MsWeekReturn> update(MsWeekReturn msWeekReturn) {
        msWeekReturnService.update(msWeekReturn);
        return ResponseMsgUtil.success(msWeekReturn);
    }


    @GetMapping("/detail")
    public Result<MsWeekReturn> detail(@RequestParam String id) {
        MsWeekReturn msWeekReturn = msWeekReturnService.get(id);
        return ResponseMsgUtil.success(msWeekReturn);
    }

    /**
     * 带分页的查询,根据条件获取菜单列表,可分页，默认查询第一页,一次" + DEFAULT_PAGE_SIZE + "条记录
     *
     * @param pageNumber 默认第{@link Constant#DEFAULT_PAGE_NUMBER}页，如果小于1，则查出所有的记录
     * @param pageSize   分页大小，如果小于1，则查出所有的记录,默认{@link Constant#DEFAULT_PAGE_SIZE}条
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<MsWeekReturn>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        if (pageNumber > 0 && pageSize > 0){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<MsWeekReturn> list = msWeekReturnService.find();
        PageInfo<MsWeekReturn> pageInfo = new PageInfo<>(list);
        return ResponseMsgUtil.success(pageInfo);
    }
}
