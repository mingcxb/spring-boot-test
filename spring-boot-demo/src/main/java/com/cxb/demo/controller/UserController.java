package com.cxb.demo.controller;

import com.cxb.demo.demain.User;
import com.cxb.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("allUsers")
    public List<User> findUser() {
        List<User> allUser = userService.findUserByCompanyName("google");
        log.info(allUser.toString());
        return allUser;
    }
}
