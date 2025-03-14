package com.trip_service.trip_service.utils.DTO;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SendResponse<T> {

    private int status;
    private String message;
    private T result;

    // Contructors

    public SendResponse() {
        this.status = 200;
        this.message  = "Data Retrived Successfully!";
        this.result = null;
    }

    public SendResponse(int status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public SendResponse(String message, T result) {
        this.status = 200;
        this.message = "Data Retrived Successfully!";
        this.result = result;
    }

    public SendResponse(T result) {
        this.status = 200;
        this.message = "Data Retrived Successfully!";
        this.result = result;
    }

}