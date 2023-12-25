package com.project.controller;

import com.project.payload.abstracts.BaseUserResponse06lg;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController //uc02
@RequestMapping("/user") //uc03
@RequiredArgsConstructor //uc04
public class UserController01uc {
    private final UserService02lg userService02lg; //uc05 enceksiyon
    @PostMapping("/save/{userRole}")// todo uc06 http://localhost:8080/user/save/Admin + POST + JSON --->Dean(dekan) / ViceDean(dekanYardimcisi)
    @PreAuthorize("hasAnyAuthority('ADMIN')")  //uc07
    public ResponseEntity<ResponseMessage<UserResponse07lg>> saveUser(@RequestBody @Valid UserRequest04uc userRequest04uc,
                                                                      @PathVariable String userRole){
        return ResponseEntity.ok(userService02lg.saveUser(userRequest04uc, userRole));//uc60

    } // todo uc08
    @GetMapping("/getAllUserByPage/{userRole}") //uc118 todo http://localhost:8080/user/getAllUserByPage/Admin + GET
    @PreAuthorize("hasAnyAuthority('ADMIN')")//uc119 bu endpointi sadece adminler tetikleyecek
    public ResponseEntity<Page<UserResponse07lg>>getUserByPage(@PathVariable String userRole,
                                                               @RequestParam(value = "Page", defaultValue = "0") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestParam(value = "sort", defaultValue = "name") String sort,
                                                               @RequestParam(value = "type",defaultValue = "desc") String type){
       Page<UserResponse07lg> adminOrDeans= userService02lg.getUserByPage(page, size, sort, type, userRole); //uc121
        return new ResponseEntity<>(adminOrDeans, HttpStatus.OK); //uc122  yukarida uc68 kisa halidir.-->uc123 icin  getUserByPage great UserService02lg class gittik
    } //uc120
    @GetMapping("/getUserById/{userId}") //uc138 <--137 UserRepository06 class geldik http://localhost:8080/user/getUserById/1 + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')") //uc139
    public ResponseMessage<BaseUserResponse06lg> getUserById(@PathVariable Long userId){
     return userService02lg.getUserById(userId);//uc141
    }//uc140
    //delete user uc177.. ...id'li kullaniciyi sil anlaminda kod yazacagiz burada süperAdmin haric herkesin silinmesi
    @DeleteMapping("/delete/{id}") //uc178  http://localhost:8080/user/delete/3 + DELETE
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASISTANT_MANAGER')")// uc179 bu kullanicilardan herhangi biri bu yetkiye sahip
    public ResponseEntity<String> deleteUserById(@PathVariable Long id, HttpServletRequest httpServletRequest){

        return ResponseEntity.ok(userService02lg.deleteUserById(id, httpServletRequest));//uc181 -->uc182 gretamethod
    } // uc180 kullaniciyi id bilgisi ile silme metodu admin ise herkesi silsin , maneger ise asistani silsin asistan ise alttakileri silsin ama tersine yapilamasin
    //!!!Uptade methodu yaziyoruz bu kullaniciyi sadece admin tetikleyebilecek Dean(müdür) veya viceDean(müdürYardimcisini) güncellerken kullsnscsgi metodu yaziyoruz
    @PutMapping("/update/{userId}")//uc 301 http://localhost:8080/user/uptade/3 + PUT +JSON
    @PreAuthorize("hasAnyAuthority('ADMIN')")//uc302
    public ResponseMessage<BaseUserResponse06lg> updateAdminDeanViceDeanForAdmin(@RequestBody @Valid UserRequest04uc userRequest04uc,
                                                              @PathVariable Long userId){
        return userService02lg.updateUser(userRequest04uc, userId); //uc304 -->uc305 updateUser greate method ile UserService02lg class gidiyoruz
    }//uc303
}//todo uc01


/*HttpServletRequest httpServletRequest bu metodda gelenin rolu Manager ise sadece Admin silsin, .... Asistant manger ise teaher ve student silebilsin dir.
* tersine islem yailamasin.*/


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