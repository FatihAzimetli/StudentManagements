package com.project.security.config;


import com.project.security.jwt.AuthEntryPointJwt07sc;
import com.project.security.jwt.AuthTokenFilter04sc;
import com.project.security.service.UserDetailsServiceImpl02sc;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity //sc16
@Configuration //sc17 opsiyonel
@EnableGlobalMethodSecurity(prePostEnabled = true) //sc18 method seviye yetkilendirme
@RequiredArgsConstructor //sc19 final quewoerdlerin injection yapilmasini sagliyoruz
public class WebSecurityConfig05sc {

    private final UserDetailsServiceImpl02sc userDetailsService; //s85

    private final AuthEntryPointJwt07sc authEntryPointJwt07sc; //s112

    @Bean //s75
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager(); //s76
    }//s74 throwsexception getAuthenticationManager() den geldi

    @Bean //s78
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt07sc).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers(AUTH_WHITE_LIST).permitAll()
                .anyRequest().authenticated(); //s90
        http.headers().frameOptions().sameOrigin(); //s91
        http.authenticationProvider(authenticationProvider());//s92
        http.addFilterAfter(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);//s93
        return http.build();//s94
    } //s77 throws Exception in cors() den geldi

    @Bean //s79
    public AuthTokenFilter04sc authenticationJwtTokenFilter(){
        return new AuthTokenFilter04sc();
    } //s80
    @Bean// s81
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }//s82

    @Bean //s83
    public DaoAuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); //s86
      authenticationProvider.setUserDetailsService(userDetailsService); //s87
        authenticationProvider.setPasswordEncoder(passwordEncoder()); //s88
        return authenticationProvider;
    }//s84
    @Bean //s96
    public WebMvcConfigurer corsConfigurer(){
       return  new WebMvcConfigurer() {
           @Override
           public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedHeaders("*")
                    .allowedMethods("*");

           }
       };//s97
    } //ss95 default deger döndürdügü icin kapattik WebMvcConfigurer.super.addCorsMappings(registry);
    private static final String[] AUTH_WHITE_LIST ={
            "/",
            "/index.html",
            "/images/**",
            "/css/**",
            "/js/**",
            "/contactMessage/save"
    }; //s89

}//sc15

/*sc20 ilk dolduracagimiz clas UserDetailsImpl02sc klasi olacak*/



/*@Override
           public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedHeaders("*")
                    .allowedMethods("*");

           }
       };//s97 corsConfigurer den overide/implement secerek addCorsMappings(CorsRegistry registry) isaretledik
        //verdigi default degeri sildi yerine registry.addMapping("/**") yazdik uygulamaya gelen her istekte bu ayar kullanilacak
        //metod ceyn oygulayacagiz .allowedOrigins("*") nereden geldiginin bir önemi yok gelen istekleri kabul ediyorum
        //ve http metoglarin hangisi ile gelirse gelsin kabul ediyorum GET POST DELETE v.s .allowedMethods("*"); Buna Cotse ayari
        //denir ve bunun frontedi belli ve yayini belli degil test asamasinda "*" konur ancak canliya cikarken Fronted ve giger
        // ayarlar belli oldugunda gelinip bu bilgiler "*" buraya yazilir*/

/*97-->98  jwt pakeg altina bir class olusturuyoruz bu class scurity katmaninda kastim exception lari karsilayacak
* AuthEntryPointJwt07sc*/

/* //TODO: exeptionHand ler eklenecek
                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt07sc).and()
                s112 den sonra TODO kaldirp bu metohd yazildi bu metod frontede veya clanta bilgi verecek
                                07.12.2023 login Endpointleri ders8 itibari ile yaziyoruz
                                bu sistemde register yok gecici kayit bilgi ve sifresini biz verecegiz
                                bu uygulamaya herkesi admin dahil edecek*/
