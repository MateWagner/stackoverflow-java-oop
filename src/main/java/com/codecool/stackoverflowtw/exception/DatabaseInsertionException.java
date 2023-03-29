package com.codecool.stackoverflowtw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DatabaseInsertionException extends RuntimeException {
    public DatabaseInsertionException(){
        super("Failed to insert element to database!");
    }
}
