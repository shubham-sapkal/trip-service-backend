package com.trip_service.trip_service.utils.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneratedApiException extends RuntimeException {

    private int statusCode;
    private String errorMsg;

    public GeneratedApiException() {
        super("Internal Server Error!!");
        this.statusCode = 500;
        this.errorMsg = "Internal Server Error!!";
    }

    public GeneratedApiException(String errorMsg) {
        super("Internal Server Error!!");
        this.statusCode = 500;
        this.errorMsg = errorMsg;
    }

    public GeneratedApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.errorMsg = message;
    }

    public GeneratedApiException(int statusCode, String message, String errorMsg ) {
        super(message);
        this.statusCode = statusCode;
        this.errorMsg = errorMsg;
    }

    public GeneratedApiException getGeneratedApiException() {
        return new GeneratedApiException(this.statusCode, this.getMessage(), this.errorMsg);
    }


}
