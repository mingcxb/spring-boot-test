package com.cxb.demo.service;

import com.cxb.demo.demain.Company;
import com.cxb.demo.demain.User;
import com.cxb.demo.enums.SexEnum;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveUser() throws Exception {
        User u = new User();
        u.setLoginName("adminadminadminadmin");
        u.setPassword("123456");
        u.setName("王二");
        u.setPhone("13809873547");
        u.setEmail("wanger@126.com");
        u.setBirthday(new DateTime("1985-03-22").toDate());
        u.setSex(SexEnum.MAN.getCode());

        Company company = new Company();
        company.setName("oracle");
        company.setCreateTime(new Date());
        u.setCompany(company);

        userService.saveUser(u);
        Assert.assertNotEquals(null, u.getId());
    }

}