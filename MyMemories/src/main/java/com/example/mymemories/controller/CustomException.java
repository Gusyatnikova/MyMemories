package com.example.mymemories.controller;

public class CustomException extends Exception{
    String msg;

    CustomException(String _msg){
        msg = _msg;
    }

    public String getMessage(){
        return msg;
    }
}