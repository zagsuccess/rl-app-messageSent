package com.example.demo.dao;

import com.example.demo.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao {
    UserInfo findUserInfoById(String id);

}
