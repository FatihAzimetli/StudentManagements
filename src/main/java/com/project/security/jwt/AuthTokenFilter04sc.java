package com.project.security.jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component//sc12
@RequiredArgsConstructor //sc13 loglama yapmamiza yariyacak
public class AuthTokenFilter04sc extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    }//sc14
}//sc11 -sc12


/* sc12A utTokenFilter castem bir class scurity anlayacagi filter ceyn ekleyebilmek icin OncePerRequestFilter
clasindan extend ediyoruz ve ardinda oweryde ediyoruz implement methot yaptik sc13*/


/*sc15 websecurity class config pakegda olusturduk olusturacagiz*/


