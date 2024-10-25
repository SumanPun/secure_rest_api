package com.example.restApi.exceptions;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}
