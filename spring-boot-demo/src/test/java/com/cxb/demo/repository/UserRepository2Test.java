package com.cxb.demo.repository;

import com.cxb.demo.demain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepository2Test {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll() throws Exception {
        PageRequest page = new PageRequest(0, 2);
        Page<User> all = userRepository.findAll(page);
        System.out.println(all);
    }

}