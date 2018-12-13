package com.uhope.messageSent.web;

import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.messageSent.domain.MsReturnBack;
import com.uhope.messageSent.service.MsReturnBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 退回表
 * @author  wanglijun
 */
@RestController
@RequestMapping("/v1/msReturnBack")
public class MsReturnBackController {

    @Autowired
    private MsReturnBackService msReturnBackService;

    @GetMapping("/getList")
    public Result<List<MsReturnBack>> getList(@RequestParam String objid) {

        Condition condition = new Condition(MsReturnBack.class);
        Example.Criteria criteria = condition.createCriteria();

        criteria.andCondition("objid = '" + objid + "'");

        List<MsReturnBack> list = msReturnBackService.findByCondition(condition);

        return ResponseMsgUtil.success(list);
    }
}
