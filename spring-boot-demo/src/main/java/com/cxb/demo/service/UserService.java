package com.cxb.demo.service;

import com.cxb.demo.demain.Company;
import com.cxb.demo.demain.User;
import com.cxb.demo.repository.CompanyRepository;
import com.cxb.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Transactional
    public User saveUser(User user) {
        Company c = companyService.saveCompany(user.getCompany());
        user.setCompany(c);
        return userRepository.save(user);
    }

    public Company getVirtualCompany(){
        return companyService.getVirtualCompany();
    }
}
