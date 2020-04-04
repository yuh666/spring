package com.example.spring;


import com.example.spring.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Application.class})
public class TestUserDao {

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindById(){
        System.out.println(userDao.get(100L));
    }
}
