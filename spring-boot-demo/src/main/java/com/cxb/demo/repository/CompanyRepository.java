package com.cxb.demo.repository;

import com.cxb.demo.demain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}

