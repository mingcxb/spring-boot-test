package com.cxb.shiro.demo.repository;

import com.cxb.shiro.demo.demain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    public User findByLoginName(String loginName);

    @Query("select u from User u where u.company.name = ?1")
    public List<User> findByCompanyName(String companyName);

}
