package com.project.service.validator;

import com.project.entity.concretes.user.User;
import com.project.exception.ConflictException;
import com.project.payload.messages.ErrorMessages12lg;
import com.project.payload.request.abstracts.AbstractUserRequest02uc;
import com.project.repository.UserRepository06;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component//uc63
@RequiredArgsConstructor //uc64
public class UniquePropertyValidator05uc {
    private final UserRepository06 userRepository06;//uc65 injection

    public void checkDuplicate (String username, String ssn, String phone, String email ){

        if (userRepository06.existsByUsername(username)){
         throw new ConflictException(String.format(ErrorMessages12lg.ALREADY_REGISTER_MESSAGE_USERNAME, username)); //uc69 cakisma exception erormessage gidiyoruz ve final bir mesaj olusturuyoruz -->uc70
        }//uc67 greate UserRepository06 metod olustu ve bir exception olusturmamiz gerekiyor

        if (userRepository06.existsBySsn(ssn)){
            throw new ConflictException(String.format(ErrorMessages12lg.ALREADY_REGISTER_MESSAGE_SSN, ssn)); //uc71
        }//uc70 repositoriyede existsBySsn metodu olusturuyoruz greade -->uc72 erroremesage -->uc73

        if (userRepository06.existsByPhoneNumber(phone)){
            throw new ConflictException(String.format(ErrorMessages12lg.ALREADY_REGISTER_MESSAGE_PHONE, phone)); //uc75
        }//uc74 repositoriyede existsBySsn metodu olusturuyoruz greade -->uc76 erroremesage -->uc77

        if (userRepository06.existsByEmail(email)){
            throw new ConflictException(String.format(ErrorMessages12lg.ALREADY_REGISTER_MESSAGE_EMAIL, email)); //uc79
        }//uc78 repositoriyede existsBySsn metodu olusturuyoruz greade -->uc80 erroremesage -->uc81 bundan sonra UserService injection yapmaliyim -->uc82
    } // uc66 bu unique lik konrolü icin
    public void checkUniqueProperties(User user, AbstractUserRequest02uc abstractUserRequest02uc){
        String updatedUsername = ""; //uc312
        String updatedSsn = ""; //uc313
        String updatedPhone = ""; //uc314
        String updatedEmail = ""; //uc315
        boolean isChanged = false; //uc316 bu unique degerlerden herhangi biri degisti ise burayi true yapicagiz asagidaki method
        //!!!Username degistimi kontrolü
        if (!user.getUsername().equalsIgnoreCase(abstractUserRequest02uc.getUsername())){
            updatedUsername = abstractUserRequest02uc.getUsername();//uc318
            isChanged = true; //uc319
        }//uc317
        //!!!Ssn degistimi kontrolü
        if (!user.getSsn().equalsIgnoreCase(abstractUserRequest02uc.getSsn())){
            updatedSsn = abstractUserRequest02uc.getSsn();//uc321
            isChanged = true; //uc322
        }//uc320
        //!!!Phone degistimi kontrolü
        if (!user.getPhoneNumber().equalsIgnoreCase(abstractUserRequest02uc.getPhoneNumber())){
            updatedPhone = abstractUserRequest02uc.getPhoneNumber();//uc324
            isChanged = true; //uc325
        }//uc323
        //!!!email degistimi kontrolu
        if (!user.getEmail().equalsIgnoreCase(abstractUserRequest02uc.getEmail())){
            updatedEmail = abstractUserRequest02uc.getEmail();//uc327
            isChanged = true; //uc328
        }//uc326
        if (isChanged){
            checkDuplicate(updatedUsername, updatedSsn, updatedPhone, updatedEmail);//uc330
        }//uc329
    }//uc311
}//uc62


/*todo soru buraya neden @Bean yazmadikta compenent yazdik.? Bean yazarsak olmaz Compenent class seviyesindeki injectionlar
*  icin kullanilir Bean ise metod seviyesindeki injectionlar icin kullanilir*/
