package com.example.demo.serviceimpl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author a4994
 * @create 2018-09-18 15:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    public User log(String id) {
        return userDao.findUserInfoById(id);
    }
}
