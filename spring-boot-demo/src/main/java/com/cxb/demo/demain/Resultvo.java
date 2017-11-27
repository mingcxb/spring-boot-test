package com.cxb.demo.demain;

import lombok.Data;

@Data
public class Resultvo<T> {
    private Integer code;
    private String message;
    private T data;
}
