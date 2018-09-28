package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 描述:
 * test
 *
 * @author a4994
 * @create 2018-09-18 15:21
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/log")
    public Map<String,String> log(String id){
        Map<String,String>map=null;
        if (null!=userService.log(id)){
            map.put("youde","OK");
            return map;
        }else{
            map.put("xixi","NO");
            return map;
        }
    }

}
