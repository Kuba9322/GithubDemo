package com.company.projectcatalog.exeptions;

public class NoSuchOptionException extends RuntimeException{
    public NoSuchOptionException(String message) {
        super(message);
    }
}
