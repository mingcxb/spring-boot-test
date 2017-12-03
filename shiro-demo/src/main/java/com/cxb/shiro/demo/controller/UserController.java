package com.cxb.shiro.demo.controller;

import com.cxb.shiro.demo.demain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {



    @RequestMapping("allUsers")
    public List<User> findUser() {
        return null;
    }

/*    @GetMapping("/vc")
    public Company getVirtualCompany(){
        return  userService.getVirtualCompany();
    }*/
}
