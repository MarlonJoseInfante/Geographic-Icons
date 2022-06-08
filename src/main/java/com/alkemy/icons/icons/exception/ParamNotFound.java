package com.alkemy.icons.icons.exception;

public class ParamNotFound extends RuntimeException{

    @SuppressWarnings("serial")
    public ParamNotFound(String error){
        super(error);
    }
}
