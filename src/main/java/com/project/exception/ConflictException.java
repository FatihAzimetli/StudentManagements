package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) //162
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }//161
}//160 ContactMessageService klasindan geldim
//161 ic yapi icin 1-generatin 2-Contracter 3- RuntimeException(message:String) ile calistir diyorum
//163 ContactMessageService class gidiyorum
