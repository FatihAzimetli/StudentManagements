package com.project.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component //s99
public class AuthEntryPointJwt07sc implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt07sc.class); //s101
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

       LOGGER.error("Unauthorize error {}", authException.getMessage()); //s102

        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //s103
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //s104 burasi 401 statüs kod dönecek

        final Map<String, Object>  body = new HashMap<>(); //s105
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED); //s106
        body.put("error", "Unauthorized"); //s107
        body.put("message", authException.getMessage()); ///s108
        body.put("path", request.getServletPath()); //s109 tetikleme bilgisi

        // olusturdugumiz map i respontion icine göndermek istersek object map sinifi var Json ktp.

        final ObjectMapper objectMapper = new ObjectMapper(); //s110
        objectMapper.writeValue(response.getOutputStream(), body);//s111

    } //s100 implemet gelen metod kimlik dogrulama hatasi oldugu anda calisacak olan medhot budur
} //s98 burada yapacagimiz overide ile olusan mesejlar claynta gidecek ve implement etik  tek metod onayladik

/*s112 burada olusturulan class WebSecurityConfig05sc injection yapacagiz s//85 altina yazdik*/

/*soru bu class nicin yazildi??? Security katindaki firlamasi muhtemel exception lar bu kata yönlendirilir bu
* class türünde exception larin firlamasini sagliyoruz burada adminin yapmasi gerekeni baska birisi yapmak istediginde
* burasi 401 hata kodunu gönderecek bu class ConfigException gibi calisiyor*/
