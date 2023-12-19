package com.project.payload.request.user;

import com.project.payload.request.abstracts.BaseUserRequest03uc; //uc56
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder//uc57

@Getter //uc58 getter-setter yerine data yazilabilir
@Setter //uc59
@AllArgsConstructor //uc61 yazilmasada oluyor cünki Superbuildir bunu karsiliyor
@NoArgsConstructor//uc60




public class UserRequest04uc extends BaseUserRequest03uc {

} //uc55

/* todo teacher requesti icin buuradaki gibi bir contrate clas yazsak ve icini bos biraksak olurmu sadece BaseUserRequest03uc.??
*   olmaz dendi  niye olmaz teacher icinde isedvaiser diye bir degisken olmasi lazim hani nerede AbsarakUserRequest ve BaseUserRequestte yok
* o zaman techerRequest icine girdigimizde boolean isAdvisor diye bir degisken ekleriz senaryoya uygun dto class olusturulur*/

/*todo soru StudendRequest i BaseUserRequest ten extend etsem ve badisini bos biraksam olurmu
*  olmaz niye BaseUserRequest gittigimizde studentNumber görünmüyor AbstrakUserRequeste bakiyoruz ordada yok madeName.?
* faderName nerde yok o zaman olmaz o zaman ne yapacagim teacherRequesti BaseUserRequestten extend edecegim ve onun badisine
* 3 tane degisken koyacagim faderName, madername ve studentNumber koymak gerekiyor yada edvisor teacherla ilgili bir bilgi varsa onuda yazacagim */

/*bir clastan extend ettiktek sonra eksik olan fieldler classa eklenir*/


