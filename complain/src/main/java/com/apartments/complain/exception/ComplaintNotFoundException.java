package com.apartments.complain.exception;

public class ComplaintNotFoundException extends RuntimeException {

    public ComplaintNotFoundException(String massage){
        super(massage);
    }

}
