package org.yonixee.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yonixee.entity.User;

import javax.annotation.Resource;

/**
 * Created by yonixee on 15/5/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class UserServiceTest {

    @Resource
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void addTest() {
        User user = new User();
        user.setUserName("zhangsan");
        user.setPassword("zhangsan");
        user.setPhoneNumber("3124234342342");
        userService.saveUser(user);
    }
}
