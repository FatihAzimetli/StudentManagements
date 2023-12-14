package com.project.service;

import com.project.entity.concretes.user.User;
import com.project.payload.mappers.UserMapper08lg;
import com.project.payload.request.LoginRequest03lg;
import com.project.payload.response.AuthResponse04lg;
import com.project.payload.response.UserResponse07lg;
import com.project.repository.UserRepository06;
import com.project.security.jwt.JwtUtils03sc;
import com.project.security.service.UserDetailsImpl01sc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService05lg {
    private final UserRepository06 userRepository06; //l34

    private final AuthenticationManager authenticationManager;//l38

    private final JwtUtils03sc jwtUtils03sc; //l41 buraya injection yapildi asagiya greyd yapiyoruz l42

    private final UserMapper08lg userMapper; //l88
    public ResponseEntity<AuthResponse04lg> authenticateUser (LoginRequest03lg loginRequest03lg){
      String username =  loginRequest03lg.getUsername();
      String password =  loginRequest03lg.getPassword();
//!! kullanici authenticationManager üzerinden valide edilir yani isim ve password dogrumu
    Authentication authentication =
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); //l39
        //!!! valide edilen kullanici kontex'e atilir
        SecurityContextHolder.getContext().setAuthentication(authentication); //l40
        //jwt token olusturulacak ilgili klass ulasacagiz --> yukaridaki l41 metodu
        String token = "Bearer " + jwtUtils03sc.generateJwtToken(authentication); //l2
        //!!Response nesnesi olusturuluyor
       UserDetailsImpl01sc userDetails = (UserDetailsImpl01sc) authentication.getPrincipal(); //l42
        //!!! grantedAuth --> Role (String ) tipte role cevirmem gerekiyor

        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());//l43
        Optional<String> role = roles.stream().findFirst(); //l44

      AuthResponse04lg.AuthResponse04lgBuilder authResponse= AuthResponse04lg.builder();  //l45 fidlari setlenmemis yarim yamalak bir nesne oldu
        authResponse.username(userDetails.getUsername()); //l46
        authResponse.token(token.substring(7)); //l47
        authResponse.name(userDetails.getName()); //l48
        authResponse.ssn(userDetails.getSsn()); //l49
        //!!!Eger role bilgisi null degil ise AuthResponse nesnesi icine setleniyor.
        role.ifPresent(authResponse::role);//l50
        return ResponseEntity.ok(authResponse.build());//l51 -->l52 icin AuthenticationController01lg class gittik



    }//L37

    public UserResponse07lg findByUserName(String username) {
     User user = userRepository06.findByUsername(username);//l83 User user = User projedeki User
        //!!! elimdeki pjo yu --> dto ceviriyorum
        return userMapper.mapUserToUserResponse(user); //l89

    } //l82
}//l33


/*/* l33--> Bu clasin Authoncation servis katini yazmaya gidiyoruz servis pakeg altina AuthenticationService bu
 * class olusturduk bu AuthenticationController01lg class in servisidir. */

/* l84 User user = userRepository06.findByUsername(username);//l83 User user = User projedeki User findByUser creat edeiyoruz
* UserRepository06lg olusacak */

/*public UserResponse07lg findByUserName(String username) {
     User user = userRepository06.findByUsername(username);//l83 User user = User projedeki User
     buradaki User i userRepose07lg cevirmemiz grekiyor yani dto class yazmamiz gerekiyor. payload pakeg icine mappers diye bir
     pkag olusturacagiz ve uygulamanin uygulamadaki map leme islemlerini bu pkag icinde olusturalacak classlarda topluyacagiz
     class adi UserMap08lg playload pakeg altinda mappers pakeg yaptik ve altinda UserMapper08lg -->l84  */

/*olusturdugumus mapper clasi kullanmak icin injecsin yaiyorum l88*/

/*servis katinin bize sagladigi kolaylik
* public UserResponse07lg mapUserToUserResponse(User user){
        return UserResponse07lg.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .birthDay(user.getBirthDay())
                .birthPlace(user.getBirthPlace())
                .ssn(user.getSsn())
                .email(user.getEmail())
                .userRole(user.getUserRole().getRoleType().name)
                .build();//l87 bu kod tekrarini yapmadik */