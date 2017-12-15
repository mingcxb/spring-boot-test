package com.cxb.swagger.test.repository;

import com.cxb.swagger.test.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GirlRepository extends JpaRepository<Girl, Integer> {
}
