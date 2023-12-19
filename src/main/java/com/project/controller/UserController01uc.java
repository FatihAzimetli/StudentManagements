package com.project.controller;

import com.project.payload.request.user.UserRequest04uc;
import com.project.payload.response.ResponseMessage;
import com.project.payload.response.UserResponse07lg;
import com.project.service.UserService02lg;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController //uc02
@RequestMapping("/user") //uc03
@RequiredArgsConstructor //uc04
public class UserController01uc {
    private final UserService02lg userService02lg; //uc05 enceksiyon
    @PostMapping("/save/{userRole}")// todo uc06 http://localhost:8080/user/save/Admin + POST + JSON --->Dean / ViceDean
    @PreAuthorize("hasAnyAuthority('ADMIN')")  //uc07
    public ResponseEntity<ResponseMessage<UserResponse07lg>> saveUser(@RequestBody @Valid UserRequest04uc userRequest04uc,
                                                                      @PathVariable String userRole){
        return ResponseEntity.ok(userService02lg.saveUser(userRequest04uc, userRole));//uc60

    } // todo uc08
    @GetMapping("/getAllUserByPage") //uc118 todo http://localhost:8080/user/getAllUserByPage/Admin + GET
    @PreAuthorize("hasAnyAuthority('ADMIN')")//uc119
    public ResponseEntity<Page<UserResponse07lg>>getUserByPage(@PathVariable String userRole,
                                                               @RequestParam(value = "Page", defaultValue = "0") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestParam(value = "sort", defaultValue = "name") String sort,
                                                               @RequestParam(value = "type",defaultValue = "desc") String type){
       Page<UserResponse07lg> adminOrDeans = userService02lg.getUserByPage(page, size, sort, type, userRole); //uc121
        return new ResponseEntity<>(adminOrDeans, HttpStatus.OK); //uc122  yukarida uc68 kisa halidir.-->uc123 icin  getUserByPage great UserService02lg class gittik
    } //uc120
}//todo uc01

/*todo kullanicilarla ilgili UserController, TeacherController, StudentController bu 3 class arka palanda UserService bagli olacak
* todo UserController in icinde Admin/manager/AssitantManager olacak */

/* todo public ResponseEntity<?> saveUser(@RequestBody @Valid //!!!UserRequest02uc userRequest02uc//){}
     // todo uc08 bu istegi karsilayacak request class olusturmak gerekiyor*/

/*todo User le alakali ortak veriler var (BaseUserRequest class) name, surname, birthDay, birthPlace, //!!password.......
   tüm kullanicilar icin gecerli veriler var ancak passwort bir abrast classta olmalidir (UserRequest abrastClass bunda sdece passwort ve built_in  konabilir
   her iki class abrast olacak ve Student ile Teacher claslarda normal olacak hepsini Hepsini UserRole clastan extend edebiliriz
*  Ancak bu User teacher ise olmasini istedigimiz veriler var. isAdviser bu sadece teacherda olmali yukarida olmamali
*  ve student ise maderName, fatehname, studentNumber  Bu classlari Payload pkeg altinda request pkeg altindaki
   abrast diye bir pakeg icinde olusturulacak ilk etapta icinde passwort bilgisi olmayan abstracts class olusturuyoruz*/


/*todo ic ice iki generate tip yazicagiz ResponseEntity<?>  ? oldugu yere custom olarak olsturulan bu class lar ic ice
*  public ResponseEntity<ResponseMessage<UserResponse07lg>> bu sekilde yazilabilir buraya sadece UsrResponse07lg yazsak olur ama
   farkindalik icin cünkü bu yapilabiliyor servis katinda nasil setlenecegini görmek icin özellikle ekledik*/

/*todo public ResponseEntity<ResponseMessage<UserResponse07lg>> saveUser(@RequestBody @Valid UserRequest04uc userRequest04uc) yukarida petveriable var
*  @PostMapping("/save/{userRole}") bunuda ekleriz
*  public ResponseEntity<ResponseMessage<UserResponse07lg>> saveUser(@RequestBody @Valid UserRequest04uc userRequest04uc,
                                                                      @PathVariable String userRole)*/


/*todo --uc61 icin great yaptik return ResponseEntity.ok(userService02lg.saveUser(userRequest04uc, userRole));//uc60*/