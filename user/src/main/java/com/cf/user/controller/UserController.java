package com.cf.user.controller;

import com.cf.user.pojo.User;
import com.cf.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/5/5 0005.
 */
@RestController
public class UserController {
    @Value("${e.name}")
    String name;
    @Value("${e.email}")
    String email;

    @Resource
    private UserService userService;

    @RequestMapping("/ne")
    public String ne(){
        return name + ":" + email;
    }

    @RequestMapping("/{name}")
    public List<User> getName(@PathVariable("name") String name){
//        return userService.findByName(name);
        try {
            userService.find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
