package com.project.service.helper;

import com.project.entity.concretes.user.User;
import com.project.exception.BadRequestException11lg;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.messages.ErrorMessages12lg;
import com.project.repository.UserRepository06;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component //uc184
@RequiredArgsConstructor//uc185
public class MethodHelper11uc {
    private final UserRepository06 userRepository06; //uc186
//!!!id ile kontrol
    public User isUserExist(Long userId) {
        return userRepository06.findById(userId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessages12lg.NOT_FOUND_USER_MESSAGE, userId)));//uc188 -->uc189 icin UserService02lg class gittik
    }//uc187 kullanici varmi
    //!!! built in kontrolü
    public void checkBuiltIn(User user){
        if (Boolean.TRUE.equals(user.getBuilt_in())) {
            throw new BadRequestException11lg(ErrorMessages12lg.NOT_PERMITTED_METHOD_MESSAGE);//uc194 <--UserService02lg class copy past yaptik
        }//uc308
    }//uc307 <--UserService02lg classtan geldik

}//uc183

/*bueasi excist yapacak varsa id li kullanici getiecek yoksa yok oldugunun bildirecek bir exception mesaj firlatacak
* kullaniciyi getirebilmesi icin mutlaka UserRepository gitmesi gerekiyor bu nedenle injwction yaiyoruz -->uc185 */
