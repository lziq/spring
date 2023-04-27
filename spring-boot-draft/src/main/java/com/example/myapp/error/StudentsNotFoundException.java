package com.example.myapp.error;

public class StudentsNotFoundException extends RuntimeException{
    public StudentsNotFoundException(String message){
        super(message);
    }
}
