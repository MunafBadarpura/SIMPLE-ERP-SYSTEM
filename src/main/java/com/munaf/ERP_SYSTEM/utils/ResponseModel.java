package com.munaf.ERP_SYSTEM.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ResponseModel implements Serializable {

    private LocalDateTime timestamp;
    private Object data;
    private Object error;
    private HttpStatus status;

    public ResponseModel() {
        this.timestamp = LocalDateTime.now();
    }

    public ResponseModel(Object data, Object error, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.data = data;
        this.error = error;
        this.status = status;
    }
}
