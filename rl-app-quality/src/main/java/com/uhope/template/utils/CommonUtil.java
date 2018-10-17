package com.uhope.template.utils;

import com.uhope.base.constants.Constant;
import com.uhope.base.dto.PageInfo;
import com.uhope.base.exception.NotLoginException;
import com.uhope.base.exception.ServiceException;
import com.uhope.base.result.Result;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
import com.uhope.uip.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: StivenYang
 * @date: 2018/9/11
 * @description:
 */
public class CommonUtil {

    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    @Autowired
    protected static TokenService tokenService;

    @Autowired
    private static UserService userService;

    /**
     * 分页大小默认值
     */
    protected static final String DEFAULT_PAGE_SIZE = "10";
    /**
     * 分页页数默认值
     */
    protected static final String DEFAULT_PAGE_NUMBER = "1";


    /**
     * 封装分页信息
     *
     * @param pageNumber
     * @param pageSize
     * @param records
     * @param count
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> setPageInfo(int pageNumber, int pageSize, List<T> records, Integer count) {
        PageInfo<T> pageInfo = new PageInfo<T>();
        pageInfo.setCurrentPageNumber(pageNumber);
        pageInfo.setPageSize(pageSize);
        pageInfo.setRecords(records == null ? new ArrayList<>() : records);
        pageInfo.setTotalNum(count == null ? 0 : count.longValue());
        return pageInfo;
    }

    /**
     * 获取当前登录用户id，如果我未登录，会抛出{@link NotLoginException}异常，该异常会在{@link com.uhope.rl.resumption.exception.GlobalExceptionHandler}中统一处理
     *
     * @param request
     * @return
     * @throws NotLoginException
     */
    public static String getCurrentUserId(HttpServletRequest request) throws NotLoginException {
        String currentUserId = tokenService.getUserIdByRequest(request);
        if (currentUserId == null) {
            throw new NotLoginException();
        }
        return currentUserId;
    }

    /**
     * 判断Result请求是否成功
     *
     * @param result
     * @param <T>
     * @return false:失败 true：成功
     */
    public static <T> Boolean resultIsSuccess(Result<T> result) {
        return result != null && result.getResCode().equals(Constant.RESULT_CODE_SUCCESS);
    }


    public UserDTO getUserDTO(String currentUserId){
        Result<UserDTO> userResult = userService.getById(currentUserId);
        UserDTO userDTO;
        //如果请求失败，返回空
        if (!resultIsSuccess(userResult)) {
            return null;
        } else {
            userDTO = userResult.getData();
            if (userDTO == null) {
                throw new ServiceException("用户不存在:" + currentUserId);
            }
            return userDTO;
        }
    }


    /**
     * 调用远程服务，获取data
     * @param result
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T getServiceData(Result<T> result) throws Exception {
        if(result == null){
            throw new Exception("调用远程服务失败");
        }

        if(result.getResCode() == Constant.RESULT_CODE_SUCCESS && result.getData() != null){
            return result.getData();
        }else{
            throw new Exception("调用远程服务失败");
        }
    }

    /**
     * 获取调用服务data
     * @param result
     * @param <T>
     * @return
     */
    public static <T> T getFeigionServiceResultData(Result<T> result){

        if(result == null){
            log.error("调用feigin服务返回为空,错误码:{},错误信息:{}",result.getResCode(),result.getResMsg());
        }else{
            if(result.getData() != null){
                return result.getData();
            }else{
                log.error("调用服务获取数据为空");
            }
        }
        return null;
    }
}
