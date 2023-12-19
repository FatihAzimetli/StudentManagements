package com.project.service;

import com.project.entity.concretes.user.User;
import com.project.entity.enums.RoleType;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.UserMapper08lg;
import com.project.payload.messages.ErrorMessages12lg;
import com.project.payload.messages.SuccessMessages10lg;
import com.project.payload.request.user.UserRequest04uc;
import com.project.payload.response.ResponseMessage;
import com.project.payload.response.UserResponse07lg;
import com.project.repository.UserRepository06;
import com.project.security.service.UserRoleService07uc;
import com.project.service.validator.UniquePropertyValidator05uc;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
//todo uc102 buraya servisi injection yapacagiz ya yoksa kontrolleri servis katinda oldugu icin buradan UserRoleService katina dallanacagiz

@Service//lg05
@RequiredArgsConstructor//lg06
public class UserService02lg {
    private final UserRepository06 userRepository06; //lg07

    private final UniquePropertyValidator05uc uniquePropertyValidator05uc; //uc83

    private final UserMapper08lg userMapper08lg; //uc90

    private final UserRoleService07uc userRoleService07uc; //uc107 ilgili enjection yapildi

    private final PasswordEncoder passwordEncoder;//uc 112

    public ResponseMessage<UserResponse07lg> saveUser(UserRequest04uc userRequest04uc, String userRole) {
        //!!!1-usurname-ssn-email-phonenumber unique olmali ve unique mi buraya simdi injection yapiyorum -->us82 UniquePropertyValidator05uc
        uniquePropertyValidator05uc.checkDuplicate(userRequest04uc.getUsername(),userRequest04uc.getSsn(),
                userRequest04uc.getPhoneNumber(), userRequest04uc.getEmail());//uc86 peki duableqeyt yok ise -->uc88 elimizde pjo yok dto var
        // UserRequest04uc bu dto alip pjo yapmamiz gerekiyor dto alip pojo yapicagiz yani usur dto user pojo yapacagiz UsserMapper12lg class
        //!!!DTO---->POJO
        User user = userMapper08lg.mapUserRequestToUser(userRequest04uc); //uc91
        /* todo rol bilgisi .?? sondaki userRole bakicagiz userRole admin olarak geliyorsa rolünü admin ayarliyacagiz eger dean olarak geliyorsa
            rolonü maneger olarak ayarliyacagiz visedaen olarak geliyorsa rolünü asistandmanegar olarak ayarliyacagiz  */
        if (userRole.equalsIgnoreCase(RoleType.ADMIN.name())){
            if (Objects.equals(userRequest04uc.getUsername(),"SuperAdmin")){
                user.setBuilt_in(true);//uc94 silenemez ve degistirilemez kullanici
            }//uc93
            user.setUserRole(userRoleService07uc.getUserRole(RoleType.ADMIN));//uc95 -->uc96 repository pakeg altina UserRoleRepository06uc interface class ve entity

        }else if (userRole.equalsIgnoreCase("Dean")){
            user.setUserRole(userRoleService07uc.getUserRole(RoleType.MANAGER));//uc108
        }else if (userRole.equalsIgnoreCase("ViceDean")) {
            user.setUserRole(userRoleService07uc.getUserRole(RoleType.ASSISTANT_MANAGER));//uc109
        }else {
            throw new ResourceNotFoundException(String.format(ErrorMessages12lg.NOT_FOUND_USER_USERROLE_MESSAGE, userRole));//uc110
        } //uc92
        //!!!Password encoder edilecek hashlenecek -->uc112 yukarida injection islemi yapildi
        //sifreyi encode edeceek cod yaziyoruz
        user.setPassword(passwordEncoder.encode(user.getPassword()));//uc113 ayrica userRequest.getPassword yine olur
        /* todo !!!advisor durumu false yapiliyor. Techarde acaba rehberteacher mi diye kontrol ettigimiz degisken isAdvisor var user tarafinda bunun null olmamamsi icin
            null olabilir. bizin save lame islemi yaptiklarimiz admin -menager ve assitand manegar bunlarin zaten edvaserTeacher olma ihtimalleri zaten yok
            bu degiskeni teacher tarafinda hep kontrol edecegim icin önce orayi fallse cekecegiz*/
        user.setIsAdvisor(Boolean.FALSE);//uc114
        user savedUser = userRepository06.save(user); //uc115 -->uc116 SuccessMessages10lg class icinde
        /* todo bu asagidaki kodlari yazarken ResponseMessage class kontrol ederek yaziyoruz*/
        return ResponseMessage.<UserResponse07lg>builder()
                .message(SuccessMessages10lg.USER_CREATE)
                .object(userMapper08lg.mapUserToUserResponse(savedUser))
                .build();//uc117 update ve save metodlari uzun olurCünkü mevcut veriler güncelllendigi icin--uc118 icinUserController01uc class gittik
        /*todo savedUser DB gelen user#dir ve id bilgisi mevcuttur user ise kaydedilmeyen olandir ve id bilgisi yok. her ikiside pojo dur*/
    }//uc61

    public Page<UserResponse07lg> getUserByPage(int page, int size, String sort, String type, String userRole) {
    }//uc123 UserController01uc clasinda great ederk geldik
}//l04


/*todo public OBJET saveUser(UserRequest04uc userRequest04uc, String userRole) {
    }//uc61 objet kaldirdik ve ResponseMessage<UserResponse07lg> yazdik  */

/*todo sorü bizden istenilen bu görevi saveUser yani kullaniciyi kaydetme islemi bu islemlerde silme veya update vey kayit
*  önce bazi kontrolleri yapmamiz gerekiyor getAll dahil bu metodda kontrol etmem gereken seylerin basinda ne var neleri
*  kontrol etmemiz gerekiyor hangi logic islemleri yapmamiz gerekiyor.? 1- username Uniq olmasi gerekiyor 2-password hash
*  lenmesi gerekiyor  3- Rol bunu setlemek gerekiyor ancak kullanicin rolu yok */

/*todo bir soru madem username uniq olmasi gerekiyor ise AbrastUserRequest klASSDA userName fild üzerine neden uniq olmasi gerekiyor diye
*    bir fielg atmadik.? AbstractUserRequest klasda yapamadik userName uniq olmasi icin DB gitmemiz gerekiyor önce pojo klaslara
*     yazilir ve kontrolu DB yapilir ama uername uniq kontrolunu yapmak zorundayiz bu durumda DB mutlaka gitmemiz gerekecek mesal
*   ssn de uniq olmasi gerekiyor email, tlefonnumber bunlarinda uniq olmasi gerekiyor bu 4 datanin uniq olmasi gerekir
*   unique kontrolu sadece servis katlari icin gecerlidir*/

/* todo -->uc62 unique yapilar icin bir class olusturuyoruz ve gelip buraya injecktion ediyoruz bu class nerde olmali bu metod
*   sadece servis katlari icin gecerli bu metod conrollerde cagrilmayacak bu metod repository dede cagrilmayacak sadece servis kati
*   icin kullanacaksak bu metodu o zaman service katinda bir pakeg daha olustururuz validator ismi ile yani bilgiler unique degilmi
*   valide edecek unique ise true degilse false verecek bu validator pakeg altina bir class yapiyoruz UniquePropertyValidator05uc*/

/* todo farklilik olsun diye diger String metodu buraya yazdik  } else if (userRole.equalsIgnoreCase(RoleType.MANAGER.name()) {
*   else if (userRole.equalsIgnoreCase("Dean")*/

/