package com.flyshadow.module.user;

import com.flyshadow.persistence.entity.User;
import com.flyshadow.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title: UserController
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/16 14:27
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/user/add",method = RequestMethod.GET)
    public String add(){
        User user = new User();
        user.setId(1);
        user.setName("a");
        userService.inert(user);
        User user2 = new User();
        user2.setId(2);
        user2.setName("b");
        userService.inert(user2);
        return "success";
    }

    @RequestMapping(path = "/user/list", method = {RequestMethod.GET})
    public List<User> getUserList() {
        return userService.list();
    }
}

