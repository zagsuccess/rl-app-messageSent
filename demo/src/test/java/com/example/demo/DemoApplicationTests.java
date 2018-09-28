package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Test
    public void contextLoads() {
    }

    /*@Test
    public void testSelet() {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        String id = "admin";
        User curUser = userDao.findUserInfoById(id);
        if(curUser!=null){
            log.info("成功找到用户:"+curUser.toString());
        }
    }*/


}
