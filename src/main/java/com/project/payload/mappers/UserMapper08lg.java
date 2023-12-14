package com.project.payload.mappers;


import com.project.entity.concretes.user.User;
import com.project.payload.response.UserResponse07lg;
import org.springframework.stereotype.Component;

@Component //l85
public class UserMapper08lg {
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
                .build();//l87
    }//l86
}//l84


/*Burda ayapmamiz gereken pjo yu dto cevirmek -->l86-87 islemleri */

//l88 icin AuthenticationService05lg class gittim