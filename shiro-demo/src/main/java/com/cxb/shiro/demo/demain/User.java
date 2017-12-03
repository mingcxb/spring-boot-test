package com.cxb.shiro.demo.demain;


import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String loginName;
    private String password;
    private String name;
    private String phone;
    private String email;
    private Date birthday;
    private Integer sex;
    private Date createTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @Fetch(FetchMode.JOIN)
    private Company company;
}

