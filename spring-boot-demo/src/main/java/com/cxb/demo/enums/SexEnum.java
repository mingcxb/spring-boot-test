package com.cxb.demo.enums;

import lombok.Getter;

@Getter
public enum SexEnum {
    MAN(1, "男"),
    WOMEN(2, "女");


    private int code;
    private String value;

    SexEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
