package com.munaf.ERP_SYSTEM.utils;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class CommonPageResponse {

    public static PageResponseModel OK(Object data, Object pageResult){
        return new PageResponseModel(data, HttpStatus.OK, pageResult);
    }
}
