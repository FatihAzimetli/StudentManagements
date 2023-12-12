package com.project.security.jwt;


import com.project.security.service.UserDetailsImpl01sc;
import com.project.security.service.UserDetailsServiceImpl02sc;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component//sc12
@RequiredArgsConstructor //sc13 loglama yapmamiza yariyacak
public class AuthTokenFilter04sc extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter04sc.class); //s56

    @Autowired //s61
    private JwtUtils03sc jwtUtils03sc; //s62

    @Autowired //s64
    private UserDetailsServiceImpl02sc userDetailsService; //s65

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException,
            IOException {
        try {
            String jwt =  parseJwt(request);
            if (jwt != null && jwtUtils03sc.validateJwtToken(jwt) ){
               String userName = jwtUtils03sc.getUserNameFromJwtToken((jwt));
              UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                request.setAttribute("username", userName);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }//s60 - + s61+s62 yaptiktan sonra if (jwt != null && ) 59-7ß notlari asagida burayi try/caht icine aldik s71--try/catch
        } catch (UsernameNotFoundException e) {

            LOGGER.error("Cannot set user authentication", e ); //s73 --> s74 icin WebSecurityConfig05sc kasa gittik
        } //s71 bu exception cok kiymetli
        filterChain.doFilter(request, response); //s72
    }//sc14
    private String parseJwt(HttpServletRequest request){
       String headerAuth = request.getHeader("Authorization");
       if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
           return  headerAuth.substring(7);
       }//s58
        return null;
    }//s57 standart bir method
}//sc11 -sc12


/* sc12A utTokenFilter castem bir class scurity anlayacagi filter ceyn ekleyebilmek icin OncePerRequestFilter
clasindan extend ediyoruz ve ardinda oweryde ediyoruz implement methot yaptik sc13*/


/*sc15 websecurity class config pakegda olusturduk olusturacagiz*/

/*   authentication.setDetails(); //s69 burada kulanicinin tarayicisi, Ip gibi bilgileri almak icin
* bu methodu yazdik*/

 /*String jwt =  parseJwt(request); //s59
        if (jwt != null && jwtUtils03sc.validateJwtToken(jwt) ){
           String userName = jwtUtils03sc.getUserNameFromJwtToken((jwt)); //s63 yukarida +s64+s65 ekle ve gel
          UserDetails userDetails = userDetailsService.loadUserByUsername(userName);//s66
            request.setAttribute("username", userName); //s67 bu metodla addvayser oldugunu anliyacagiz yetkiye göre yetkilendirme yaacak
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities()); //s68
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //s69
            SecurityContextHolder.getContext().setAuthentication(authentication); //s70
        }//s60 - + s61+s62 yaptiktan sonra if (jwt != null && )
    }//sc14 icine aliyoruz throw new RuntimeException(e); bunu sildik yerine LOGGER.error("Cannot set user authentication", e ); yazdik*/


