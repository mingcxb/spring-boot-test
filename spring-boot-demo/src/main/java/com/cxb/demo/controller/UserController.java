package com.cxb.demo.controller;

import com.cxb.demo.demain.Company;
import com.cxb.demo.demain.User;
import com.cxb.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
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

/*    @GetMapping("/vc")
    public Company getVirtualCompany(){
        return  userService.getVirtualCompany();
    }*/
}
