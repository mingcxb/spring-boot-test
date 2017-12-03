package com.cxb.shiro.demo.repository;

import com.cxb.shiro.demo.demain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}

