package com.huolala.core.model.response;

import lombok.Data;

@Data
public class CommonResponse<T> {
    private T data;
    private String msg;
    private Integer ret;
    private int status;
    private String result;
    private String error;
    private int match_mode;
    private String directions;
}