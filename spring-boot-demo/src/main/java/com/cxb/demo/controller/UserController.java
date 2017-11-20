package com.cxb.demo.controller;

import com.cxb.demo.demain.Company;
import com.cxb.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/vc")
    public Company getVirtualCompany(){
        return  userService.getVirtualCompany();
    }
}
