package com.project.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity //sc16
@Configuration //sc17 opsiyonel
@EnableGlobalMethodSecurity(prePostEnabled = true) //sc18 method seviye yetkilendirme
@RequiredArgsConstructor //sc19 final quewoerdlerin injection yapilmasini sagliyoruz
public class WebSecurityConfig05sc {
}//sc15

/*sc20 ilk dolduracagimiz clas UserDetailsImpl02sc klasi olacak*/
