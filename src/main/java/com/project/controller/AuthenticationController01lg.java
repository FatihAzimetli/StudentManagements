package com.project.controller;


import com.project.payload.messages.SuccessMessages10lg;
import com.project.payload.request.LoginRequest03lg;
import com.project.payload.request.business.UpdatePasswordRequest09lg;
import com.project.payload.response.AuthResponse04lg;
import com.project.payload.response.UserResponse07lg;
import com.project.service.AuthenticationService05lg;
import com.project.service.UserService02lg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController //l02
@RequestMapping("/auth") //l03
@RequiredArgsConstructor //l08
public class AuthenticationController01lg {

    private final UserService02lg userService02lg; //l09

    private final AuthenticationService05lg authenticationService05lg; //l35

    @PostMapping("/login") //l10 login icin yapilan islemler http://localhost:8080/auth/login + POST + JSON
    public ResponseEntity<AuthResponse04lg> authenticateUser(@RequestBody @Valid LoginRequest03lg loginRequest03lg){
        return authenticationService05lg.authenticateUser(loginRequest03lg); //l36
    }//l11
   // kullanici borowserini silip tekrar girmek isterse yazacagimiz kot bu nu tetikleyecek

    @GetMapping("/user")  //l52 http://localhost:8080/auth/user + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER','TEACHER','STUDENT')") //lg129 bunu tüm kullanicilar yapabilmeli
    public ResponseEntity<UserResponse07lg> findByUsername(HttpServletRequest request){
       String username= (String) request.getAttribute("username"); //l53 cast-->
       UserResponse07lg userResponse07lg = authenticationService05lg.findByUserName(username); //l80 -->l82 dByUserName create
        return ResponseEntity.ok(userResponse07lg); //l81
    }//l52-->l79
    @PatchMapping("/updatePassword") //l90 http://localhost:8080/auth/uptadePassword + PATCH *JSON
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER','TEACHER','STUDENT')") //lg130
    public ResponseEntity<String> updatePassword(@RequestBody @Valid UpdatePasswordRequest09lg updatePasswordRequest08lg,
                                                 HttpServletRequest request){
        authenticationService05lg.updatePassword(updatePasswordRequest08lg, request); //l103
        String response = SuccessMessages10lg.PASSWORD_CHANGED_RESPONSE_MESSAGE; //l107
        return ResponseEntity.ok(response); //l108
        //l104 --> String responce = "Sifreniz basarili bir sekilde degistirildi" aciklama asagida
     //!!!servise gönderme islemi
    }//l91 --l102 önce class olusturduk UpdatePasswordRequest08lg

}//l01


/* todo buraya yazacaimiz endpointleri User icine yazabilirdik ancak UserController class ta cok fazla method olacagi icin
* özellikle login alakali ve Authentication alakali gelecek istekleri ayri bir controller icinde  yazarak
* uygulamayi bir birinden daha bagimsiz hale getiriyoruz*/


/*todo l04  com.project altinda service pakeg icinde UserService02lg class olusturuyorum*/

/* todo l12 iki türlü dto vardir bazilari request bazilari response dir bu derumda payload pakeg altina request pkeg yapiyoruz
* bir pakeg altina ilgili class olusturacagiz bu bir login islemiz clasin adi LoginRequest03lg//l12 olacak*/

/*  todo l21-->public ResponseEntity<?> authenticateUser(@ResponseBody @Valid LoginRequest03lg){} ? oldugu yer reponsedir
* istek cleintan gelir controllerde karsilanir servis buraya gönderir bu responce dir
* bu durumda responce pakeg gidiyoru yeni bir class olusturuyoru bu class LoginResponce yazabilirdik ancak AuthResponce04lg
* yazdik */

/* todo l33--> Bu clasin Authoncation servis katini yazmaya gidiyoruz servis pakeg altina AuthenticationService bu
* class olusturduk bu AuthenticationController01lg class in servisidir. */

/* todo @GetMapping("/user")  //l52 http://localhost:8080/auth/user + GET
    public ResponseEntity<?> findByUsername(HttpServletRequest request){
       String username= (String) request.getAttribute("username"); //l53 cast--> String username=  request.getAttribute("username");
        authenticationService05lg.findByUserName(username)*/

/* todo public ResponseEntity<?> findByUsername(HttpServletRequest request buraya ortak fildlarin oldugu abrask klas olusturacagiz
* kod tekrarlarindan kurtulmak ve clien bir yazilim icin bu klasi payload altindaki bir pakeg icinde olusturmak
* gerekiyor bu class diger konrate alasslar ile bir olamamsi gerekiyor bu nedenle response pakeg altinda abrasts clarlari
* toplayacagimiz pakeg yapmaliyiz. yani aslinda response pakeg icinde kullanacagimiz tüm abrast claslar
* abstracts pakeg icindeki claslarda olusacak //l54 */

//l80 -->l82 dByUserName create AuthenticationService05lg  metodu olusturduk otomatik

/* todo  l92 --> sadece passwort güncellemek isterssek l90 eger komple isteseydik @PutMapping yapacaktik paswort yenilemede önce eski sifre sorulur
* sonra yeni sifre yazilmasi istenir bu bir request dir bu durumda bu request karsilamak icin bir class yapmamiz gerekir.
* bunu özellestirmek icin  payload altindaki request pqkeg altina yeni bir pakeg olusturuyoruz isim olarak business yazdik
* bunun icinde bir class olusturduk class adi UpdatePasswordRequest08lg*/


/* todo //l104 --> String responce = "Sifreniz basarili bir sekilde degistirildi" aciklama asagida bu strin mesajlar icin
* payload pakeg altinda messajlar icin bir pakeg sayfasi olusturuyoruz messages bu pakag icine birtane konquriet class
* olusturuyoruz bu classta sadece basarili olan mesajlar olacak class adi SuccessMessages10lg */

/* todo :authenticationService05lg.updatePassword(updatePasswordRequest08lg, request); //l103
        String response = SuccessMessages10lg.PASSWORD_CHANGED_RESPONSE_MESSAGE; //l107
        return ResponseEntity.ok(response); //l108

        updatePassword greate yapiyoruz l109

        public void updatePassword(UpdatePasswordRequest09lg updatePasswordRequest08lg, HttpServletRequest request) {
    }//l110  AuthenticationService05lg class gittik*/

/*todo : @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER','TEACHER','STUDENT')") //lg129 bunu tüm kullanicilar yapabilmeli
*  hasAnyAuthority oldugu icin 1 den fazla rol icin bunu  yaptik hasAuthority yazsak idik tek rolun kontrolunu yapardik*/

/*todo bu islemleri contactmessage clastada yapacagiz -->lg131*/