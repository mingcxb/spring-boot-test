package com.cxb.demo.repository;

import com.cxb.demo.demain.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void testSave() {
        Company c = new Company();
        c.setName("unimas");
        c.setCreateTime(new Date());

        companyRepository.save(c);
    }
}