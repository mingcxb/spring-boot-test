package com.cxb.swagger.test.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ApiModel
public class Girl {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer age;
    private String cupSize;
}
