package com.project.security.service;

import com.project.entity.concretes.user.UserRole;
import com.project.entity.enums.RoleType;
import com.project.payload.messages.ErrorMessages12lg;
import com.project.repository.UserRoleRepository06uc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service //uc99
@RequiredArgsConstructor //uc 100 repository kati ile injecsin yapabilmek icin
public class UserRoleService07uc {
    private final UserRoleRepository06uc userRoleRepository06uc; //uc 101 injecsin islemi -->UserService02lg class gittim geri geldik

    public UserRole getUserRole (RoleType roleType){
        return userRoleRepository06uc.findByEnumRoleEquals(roleType).orElseThrow(()-> new ResolutionException(ErrorMessages12lg.ROLE_NOT_FOUND));//uc105
    } //uc102 varsa getir yoksa exception firlat

}//uc98

/*todo findByEnumRoleEquals() türettigimiz bir isim run calistiginda burayi okurken eneum isminde bir kiwort yok enum isminde
   UserRole kolonlarinda bir degisken yok Data JPA bunu gördügünde bu ya JPQL yada neytiSQL burada cta hatasi vermez  run ettigimizde
   neytiSQL ve JPQL yoksa bize bir exception gönderir bunun optionel calismasi lazim ama bunu DB optional olarak anatation etmedigimiz icin
   sonuna nokta koymamiza ragmez bize acilim getirmiyor findByEnumRoleEquals(roleType). Önce Db gidip metodu creat ediyoruz-->uc103
    UserRoleRepository06uc gider oradaki qr yazdik ve geldik artik noktadan sonra acildi*/

/*todo new ResolutionException(?);)buraya yazilacak mesaj errorMessage icine yazacagiz uc106*/