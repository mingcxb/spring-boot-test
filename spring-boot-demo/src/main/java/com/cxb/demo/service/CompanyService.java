package com.cxb.demo.service;

import com.cxb.demo.demain.Company;
import com.cxb.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Cacheable(cacheNames = "test", value = "vc")
    public Company getVirtualCompany(){
        Company c = new Company();
        c.setId(Integer.valueOf(-1));
        c.setName("virtual company");
        return c;
    }
}
