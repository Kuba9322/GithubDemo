package com.company.projectcatalog.exeptions;

public class InvalidDataExeption extends RuntimeException{

    public InvalidDataExeption(String message) {
        super(message);
    }
}
