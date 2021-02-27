package com.company.projectcatalog.exeptions;

public class NoSuchFileTypeExeption extends RuntimeException {

    public NoSuchFileTypeExeption(String message) {
        super(message);
    }
}
