package com.trip_service.trip_service.utils.DTO;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SendResponse<T> {

    private int status;
    private String message;
    private String errorMsg;
    private T result;

    // Constructors

    public SendResponse() {
        this.status = 200;
        this.message  = "Data Retrieved Successfully!";
        this.errorMsg = "";
        this.result = null;
    }

    public SendResponse(int status, String message, String errorMsg, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.errorMsg = errorMsg;
    }

    public SendResponse(String message, T result) {
        this.status = 200;
        this.message = "Data Retrieved Successfully!";
        this.errorMsg = "";
        this.result = result;
    }

    public SendResponse(T result) {
        this.status = 200;
        this.message = "Data Retrieved Successfully!";
        this.message = "";
        this.result = result;
    }

}