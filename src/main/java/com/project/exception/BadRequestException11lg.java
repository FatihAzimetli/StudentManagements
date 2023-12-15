package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)//lg115
public class BadRequestException11lg extends RuntimeException{
    public BadRequestException11lg(String message) {
        super(message);
    }//lg117
}//lg114 -116

/* lg-116 public class BadRequestException11lg {
}//lg114 exception yapilandirma public class BadRequestException11lg extends RuntimeException{*/

/* lg117 -->parametreli consructer ini süper ki word ile olusturuyorum burada dikkat etmemiz gereken RuntimeException(message:String)
* bi kiword  yeterlidir. public BadRequestException11lg(String message) {
        super(message);
    }*/

/*lg118 icin AuthenticationService05lg classa gidiyorum */
