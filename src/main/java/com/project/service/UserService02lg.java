package com.project.service;

import com.project.entity.concretes.user.User;
import com.project.entity.enums.RoleType;
import com.project.exception.BadRequestException11lg;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.abstracts.BaseUserResponse06lg;
import com.project.payload.mappers.UserMapper08lg;
import com.project.payload.messages.ErrorMessages12lg;
import com.project.payload.messages.SuccessMessages10lg;
import com.project.payload.request.user.UserRequest04uc;
import com.project.payload.response.ResponseMessage;
import com.project.payload.response.UserResponse07lg;
import com.project.repository.UserRepository06;
import com.project.security.service.UserRoleService07uc;
import com.project.service.helper.MethodHelper11uc;
import com.project.service.helper.PageableHelper08uc;
import com.project.service.validator.UniquePropertyValidator05uc;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    private final PageableHelper08uc pageableHelper08uc; //uc131

    private final MethodHelper11uc methodHelper11uc;//uc 189 <--MethodHelper11uc class injection yaptik

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

        user.setIsAdvisor(Boolean.FALSE);//uc114
        User savedUser = userRepository06.save(user); //uc115 -->uc116 SuccessMessages10lg class icinde

        return ResponseMessage.<UserResponse07lg>builder()
                .message(SuccessMessages10lg.USER_CREATE)
                .object(userMapper08lg.mapUserToUserResponse(savedUser))
                .build();//uc117

    }//uc61

    public Page<UserResponse07lg> getUserByPage(int page, int size, String sort, String type, String userRole) {
      Pageable pageable = pageableHelper08uc.getPageableWithProperties(page, size, sort, type); //uc132
       return userRepository06.findByUserByRole(userRole, pageable).map(userMapper08lg::mapUserToUserResponse);  //uc133 -->uc134 findByUserByRole great etmek
    }//uc123 UserController01uc clasinda great ederk geldik uc124 icin service pakeg altina halper yardimci pakeg olusturuyoruz class adi PageableHelper08uc

    public ResponseMessage<BaseUserResponse06lg> getUserById(Long userId) {
        BaseUserResponse06lg baseUserResponse06lg = null; //uc162 <--StudentResponse10uc classtan geldik
        User user = userRepository06.findById(userId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessages12lg.NOT_FOUND_USER_MESSAGE, userId))); //uc163 uc164--> ErrorMessage class

        if (user.getUserRole().getRoleType() == RoleType.STUDENT){
            baseUserResponse06lg = userMapper08lg.mapUserToStudentResponse(user); //uc165 //uc166 //-->Uc173 greatemetod UserMapper08lg class gitti
        } else if (user.getUserRole().getRoleType() == RoleType.TEACHER) {
            baseUserResponse06lg = userMapper08lg.mapUserToTeacherResponse(user); //uc168 //uc167 //-->uc175 gretMet UserMapper08lg class gider
        }else {
            baseUserResponse06lg = userMapper08lg.mapUserToUserResponse(user); //uc170
        }//uc169
        return ResponseMessage.<BaseUserResponse06lg>builder()
                .message(SuccessMessages10lg.USER_FOUND)
                .status(HttpStatus.OK)
                .object(baseUserResponse06lg)
                .build(); //uc172
    }//uc142 <--return userService02lg.getUserById(userId);//uc141 serConrteoller01uc classtan greate ettik

    public String deleteUserById(Long id, HttpServletRequest request) {
        //!!! -->uc183 silinecek user varmi kontrolü varmi yokmu id kontrolü delete ve uptade cok yerde yapicagiz burada yardimci
        // class olusturacagiz injection islemler icin varmi yyokmu servis katinda yapildigi icin bu helper pakeg vardi burada bir class daha yapiyoruz MethodHelper11uc
       User user =  methodHelper11uc.isUserExist(id); //uc190 silinmesini istedigimiz user getirecek talebi yapan user ulasmamiz gerekiyor
        //!!! silme talebi yapan user ulasilmasi gereken method  buna request üzerinden gidecegiz
       String userName = (String) request.getAttribute("username"); //uc191 Object dönüyordu Sring data türüne cevirdik elimizdeki userName ile user ulasacagiz
       User user2 = userRepository06.findByUsername(userName);//uc192 bize dönmesi gereken User data türünde user2 yaptik.(silinme talebinde bulunan user)
        //!!!burada standart kontroller built in yapilacak neye göre user a göre yapilacak
        if (Boolean.TRUE.equals(user.getBuilt_in())){
            throw new BadRequestException11lg(ErrorMessages12lg.NOT_PERMITTED_METHOD_MESSAGE);//uc194
            //!!! Müdür sadece Teacher, Müdür yardimcisi vey Student silebilsin. uc196 method da
        } else if (user2.getUserRole().getRoleType() == RoleType.MANAGER) {
            if (!((user.getUserRole().getRoleType() == RoleType.TEACHER)
                    ||(user.getUserRole().getRoleType() == RoleType.STUDENT)
                    ||(user.getUserRole().getRoleType() == RoleType.ASSISTANT_MANAGER))){
                throw new BadRequestException11lg(ErrorMessages12lg.NOT_PERMITTED_METHOD_MESSAGE);//uc195
            }//uc 196
            //!!!müdür yardimcisi sadece Teacher ve Student silebilsin uc 197
        } else if (user2.getUserRole().getRoleType() == RoleType.ASSISTANT_MANAGER) {
            if (!((user.getUserRole().getRoleType() == RoleType.TEACHER)||
                    (user.getUserRole().getRoleType() == RoleType.STUDENT))){
                throw new BadRequestException11lg(ErrorMessages12lg.NOT_PERMITTED_METHOD_MESSAGE);//uc198
            }//uc 197
        }  //uc193
        userRepository06.deleteById(id); //uc199
        return SuccessMessages10lg.USER_DELETE; //uc201
    }//uc182 greate method deleteUserById yaparak UserController01uc classtan geldik Object String cevirdik httpServletRequest bunuda request olarak degistirdik

    public ResponseMessage<BaseUserResponse06lg> updateUser(UserRequest04uc userRequest04uc, Long userId) {
        //!!! varmi yokmu kontrolu
       User user = methodHelper11uc.isUserExist(userId); //uc 306
        //!!! builtIn kontrolü icin kod tekrari yapmamak icin MethodHelper11uc classta bu metodu olusturaxcagiz
        methodHelper11uc.checkBuiltIn(user);//uc309
        //!!!uniq kontrolü.
        uniquePropertyValidator05uc.checkUniqueProperties(user, userRequest04uc);//uc310 Unique degerleri kont icin UniquePropertyValidator05uc class metod yazmak icin gittik -->uc311
    }//uc305 <-- UserController01uc class geldik great method ile
}//lg04 bu katmanda unique kontrolleri yapilamak zorundadir


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

/*//uc113 ayrica userRequest.getPassword yine olur
 todo !!!advisor durumu false yapiliyor. Techarde acaba rehberteacher mi diye kontrol ettigimiz degisken isAdvisor var user tarafinda bunun null olmamamsi icin
    null olabilir. bizin save lame islemi yaptiklarimiz admin -menager ve assitand manegar bunlarin zaten edvaserTeacher olma ihtimalleri zaten yok
    bu degiskeni teacher tarafinda hep kontrol edecegim icin önce orayi fallse cekecegiz*/

/*//uc117 update ve save metodlari uzun olurCünkü mevcut veriler güncelllendigi icin--uc118 icinUserController01uc class gittik
     todo savedUser DB gelen user#dir ve id bilgisi mevcuttur user ise kaydedilmeyen olandir ve id bilgisi yok. her ikiside pojo dur*/

/*uc115 -->uc116 SuccessMessages10lg class icinde
     todo bu asagidaki kodlari yazarken ResponseMessage class kontrol ederek yaziyoruz*/
/* todo public ResponseMessage<BaseUserResponse06lg> getUserById(Long userId) {
    }//uc142 <--return userService02lg.getUserById(userId);//uc141 serConrteoller01uc classtan greate ettik
    BaseUserResponse acilimi olan UserResponse, TeacherResponse ve StudentResponse calasslari olusturuyoruz
    bu tarz classlar payload icindeki responcse pakeg icine bununicine onceden UserResponse diye bir class olusturulmus bu yüzden Responce icine
      yeni bir user diye pakeg ekliyoruz bunun icine TeacherResponse09uc ve StudentResponse10uc classlarini olusturduk*/

