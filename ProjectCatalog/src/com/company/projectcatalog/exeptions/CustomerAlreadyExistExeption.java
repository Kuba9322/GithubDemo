package com.company.projectcatalog.exeptions;

public class CustomerAlreadyExistExeption extends RuntimeException {

    public CustomerAlreadyExistExeption(String message) {
        super(message);
    }
}
