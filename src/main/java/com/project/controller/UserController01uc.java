package com.project.controller;

import com.project.service.UserService02lg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController //uc02
@RequestMapping("/user") //uc03
@RequiredArgsConstructor //uc04
public class UserController01uc {
    private final UserService02lg userService02lg; //uc05 enceksiyon
    @PostMapping("/save/{userRole}")// todo uc06 http://localhost:8080/user/save/Admin + POST + JSON
    @PreAuthorize("hasAnyAuthority('ADMIN')")  //uc07
    public ResponseEntity<?> saveUser(@RequestBody @Valid){} // todo uc08
}//todo uc01

/*todo kullanicilarla ilgili UserController, TeacherController, StudentController bu 3 class arka palanda UserService bagli olacak
* todo UserController in icinde Admin/manager/AssitantManager olacak */

/* todo public ResponseEntity<?> saveUser(@RequestBody @Valid //!!!UserRequest02uc userRequest02uc//){}
     // todo uc08 bu istegi karsilayacak request class olusturmak gerekiyor*/

/*todo User le alakali ortak veriler var (BaseUserRequest class) name, surname, birthDay, birthPlace, //!!password.......
   tüm kullanicilar icin gecerli veriler var ancak passwort bir abrast classta olmalidir (UserRequest abrastClass bunda sdece passwort ve built_in  konabilir
   her iki class abrast olacak ve Student ile Teacher claslarda normal olacak hepsini Hepsini UserRole clastan extend edebiliriz
*  Ancak bu User teacher ise olmasini istedigimiz veriler var. isAdviser bu sadece teacherda olmali yukarida olmamali
* ve student ise maderName, fatehname, studentNumber  Bu classlari Payload pkeg altinda request pkeg altindaki
 abrast diye bir pakeg icinde olusturulacak ilk etapta icinde passwort bilgisi olmayan abstracts class olusturuyoruz*/
