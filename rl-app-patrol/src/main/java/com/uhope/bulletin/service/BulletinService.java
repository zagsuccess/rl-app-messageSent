package com.uhope.bulletin.service;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.core.Service;
import com.uhope.bulletin.domain.Bulletin;
import com.uhope.bulletin.dto.BulletinDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

/**
 * @author: StivenYang
 * @date: 2018/9/8
 * @description: 暗查暗访表-Service接口类
 */
public interface BulletinService extends Service<Bulletin, BulletinDTO, String> {
    /**
     * 模糊查询列表
     *
     * @param pageNumber
     * @param pageSize
     * @param type
     * @return
     */
    public PageInfo<Bulletin> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                   @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                   @RequestParam Integer type,
                                   @RequestParam String year,
                                   @RequestParam String month) throws ParseException;

    /**
     * 根据id删除暗查暗访记录
     *
     * @param id
     */
    public void delete(@RequestParam Integer id);

    /**
     * 查看暗查暗访记录详情
     *
     * @param id
     * @return
     */
    public Bulletin detail(@RequestParam Integer id);

    /**
     * 首页展示
     *
     * @param type
     * @return
     */
    public List<Bulletin> selectByFirst(@RequestParam Integer type);


}
