package com.example.railgo.application.exception;

import com.example.railgo.domain.utils.exception.ExceptionCode;

public class ApplicationException extends RuntimeException {
    ExceptionCode responseCode;

    public ApplicationException(ExceptionCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
    public ApplicationException(ExceptionCode responseCode){
        this.responseCode = responseCode;
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ExceptionCode getExceptionCode() {
        return responseCode;
    }
}