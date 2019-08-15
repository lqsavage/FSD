package com.iihtibm.registration.exception;

import lombok.Data;

import java.util.Date;

/**
 * @author savagelee
 */
@Data
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
