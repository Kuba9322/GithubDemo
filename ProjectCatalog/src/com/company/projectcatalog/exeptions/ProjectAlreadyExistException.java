package com.company.projectcatalog.exeptions;

public class ProjectAlreadyExistException extends RuntimeException{

    public ProjectAlreadyExistException(String message) {
        super(message);
    }
}
