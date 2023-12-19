package com.project.payload.mappers;


import com.project.entity.concretes.user.User;
import com.project.payload.request.abstracts.BaseUserRequest03uc;

import com.project.payload.response.UserResponse07lg;
import org.springframework.stereotype.Component;

@Component //lg85
public class UserMapper08lg {

    ///!!! bu asagidaki metod eldeki POJO alip DTO cevirmektir
    public UserResponse07lg mapUserToUserResponse(User user){
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
                .build();//lg87
    }//lg86
    //elimizdeki DTO bürda POJO cevirecegiz dönmesi gereken UserRequeste class
    public User mapUserRequestToUser(BaseUserRequest03uc userRequest03uc){
        return User.builder()
                .username(userRequest03uc.getUsername())
                .name(userRequest03uc.getName())
                .surname(userRequest03uc.getSurname())
                .password(userRequest03uc.getPassword())
                .ssn(userRequest03uc.getSsn())
                .birthDay(userRequest03uc.getBirtDay())
                .birthPlace(userRequest03uc.getBirthPlace())
                .phoneNumber(userRequest03uc.getPhoneNumber())
                .gender(userRequest03uc.getGender())
                .built_in(userRequest03uc.getBuiltIn())
                .build();//uc89
    } //uc88
}//lg84


/*Burda ayapmamiz gereken pjo yu dto cevirmek -->l86-87 islemleri */

//l88 icin AuthenticationService05lg class gittim

/*todo BaseUserRequest04uc teacher request ve Student request bu klastan üretilecek bu poliformizimdir Buraya extent eden
*  tüm klaslari göndermis oluyoruz ayni cins olan seyler poliformizimde kullanilir
*   publik void A(Gül gül) {}
*   publik void A(Lale lale) {}
*   publik void A(Cicek cicek)  gül ve lale bir cicek sinifidir UserRequest04uc--> BaseUserRequest04uc
*   BaseUserRequest04uc--> UserRequest, StudentRequest, TeacherRequest bu kastan extend edilecek*/