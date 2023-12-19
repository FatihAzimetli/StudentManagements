package com.project.service.validator;

import com.project.exception.ConflictException;
import com.project.payload.messages.ErrorMessages12lg;
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

        if (userRepository06.existsBySsn(username)){
            throw new ConflictException(String.format(ErrorMessages12lg.ALREADY_REGISTER_MESSAGE_SSN, ssn)); //uc71
        }//uc70 repositoriyede existsBySsn metodu olusturuyoruz greade -->uc72 erroremesage -->uc73

        if (userRepository06.existsByPhoneNumber(phone)){
            throw new ConflictException(String.format(ErrorMessages12lg.ALREADY_REGISTER_MESSAGE_PHONE, phone)); //uc75
        }//uc74 repositoriyede existsBySsn metodu olusturuyoruz greade -->uc76 erroremesage -->uc77

        if (userRepository06.existsByEmail(email)){
            throw new ConflictException(String.format(ErrorMessages12lg.ALREADY_REGISTER_MESSAGE_EMAIL, email)); //uc79
        }//uc78 repositoriyede existsBySsn metodu olusturuyoruz greade -->uc80 erroremesage -->uc81 bundan sonra UserService injection yapmaliyim -->uc82
    } // uc66 bu unique lik konrolü icin
}//uc62


/*todo soru buraya neden @Bean yazmadikta compenent yazdik.? Bean yazarsak olmaz Compenent class seviyesindeki injectionlar
*  icin kullanilir Bean ise metod seviyesindeki injectionlar icin kullanilir*/
