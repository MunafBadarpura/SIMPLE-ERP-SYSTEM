package com.munaf.ERP_SYSTEM.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class PageResponseModel {

    private LocalDateTime timestamp;
    private Object data;
    private HttpStatus status;
    private Object pageResult;

    public PageResponseModel() {
        this.timestamp = LocalDateTime.now();
    }

    public PageResponseModel(Object data, HttpStatus status, Object pageResult) {
        this();
        this.data = data;
        this.status = status;
        this.pageResult = pageResult;
    }
}
