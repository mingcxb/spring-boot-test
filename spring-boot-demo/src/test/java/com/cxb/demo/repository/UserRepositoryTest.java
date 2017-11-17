package com.cxb.demo.repository;

import com.cxb.demo.demain.Company;
import com.cxb.demo.demain.User;
import com.cxb.demo.enums.SexEnum;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void insertUser() {
        User u = new User();
        u.setLoginName("admin");
        u.setPassword("123456");
        u.setName("王二");
        u.setPhone("13809873547");
        u.setEmail("wanger@126.com");
        u.setBirthday(new DateTime("1985-03-22").toDate());
        u.setSex(SexEnum.MAN.getCode());

        Company company = new Company();
        company.setId(1);
        u.setCompany(company);

        User user = userRepository.save(u);
        Assert.assertNotEquals(null, user.getId());
    }

    @Test
    public void findByLoginName() throws Exception {
        User user = userRepository.findByLoginName("admin");
        Assert.assertEquals("admin", user.getLoginName());
    }

}