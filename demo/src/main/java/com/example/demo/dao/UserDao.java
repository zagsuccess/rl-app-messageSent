package com.example.demo.dao;

import com.example.demo.entity.User;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface UserDao {
    public User findUserInfoById(String id);
}
