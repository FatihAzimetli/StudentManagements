package com.project.security.jwt;

import com.project.security.service.UserDetailsImpl01sc;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component //s10
public class JwtUtils03sc {


    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils03sc.class); //s48



    @Value("${backendapi.app.jwtSecret}") //45-46-47
    private String jwtSecret; //s45

    @Value("${backendapi.app.jwtExpirationMs}") //45-46-47 wordler aplicazionproportiesten kopyalanir
    private long jwtExpirationMs;
    //Not : Generate JWT
    public String generateJwtToken(Authentication authentication){
       UserDetailsImpl01sc userDetailsImpl01sc = (UserDetailsImpl01sc) authentication.getPrincipal();
       return generateJwtTokenFromUsername(userDetailsImpl01sc.getUsername());

    } //s45-46

    public String generateJwtTokenFromUsername(String username ){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs)) //1,new Date normal 2.newDate javax.util
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    } //s45-47

    public boolean validateJwtToken(String jwtToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.error("Jwt token is expired : {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Jwt token is unsupported : {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Jwt token is invalid : {}", e.getMessage());
        } catch (SignatureException e) {
            LOGGER.error("Jwt signature is invalid : {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Jwt is empty : {}", e.getMessage());
        }
        return false;
    }//s50-51-52-53-s54 burasi trykach icine alacagiz son iki satir secilir ve code->SurroundWith--try/catch
    //Not: getUsernameFromJWT yazilacak
    public String getUserNameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }//s55
}//s9


/*Bu klasta 3 tane önemli 1-generat jwt token 2- validayt jwt token 3- jwt token icine User name bilgisini cekmek
* Burada Authtoke filter islemde kullanacagimiz icin enjeksin islemi yapiyoruz companenet ile anote ediyoruz sc10*/

/*sc11 auth token filter clasi yapim 04sc*/

/*getPrincipal() anlik olarak login olan kullaniciyi bana getir bu metodu bilmek gerekiyor ikici ialem ise
* jwt token gret edilmesi icin username bilgisine ulasilmasidir */

/* s45 aplicationPropertis dosyasina 2 satir kod ekledik
* backendapi.app.jwtExpirationMs =8640000
backendapi.app.jwtSecret=schoolmanagementproject*/

/*try/catch olsusan excepcionlarda düzenleme yapildi
 } catch (ExpiredJwtException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException(e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);*/
/*s56 AuthTokenFilter04sc klasina gidiyorum*/
