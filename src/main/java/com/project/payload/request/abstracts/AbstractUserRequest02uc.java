package com.project.payload.request.abstracts;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.time.LocalDate;

@SuperBuilder //uc10 bundan claslar üretecegimiz icindir

@Data //uc11
@AllArgsConstructor//uc12 tercih
@NoArgsConstructor //uc13 tercih
public abstract class AbstractUserRequest02uc {
    @NotNull(message = "Please enter your username")//uc15
    @Size(min = 2, max = 30, message = "Your username should be at least 2 chart")//uc16
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your username must consist of characters") //uc17
    private String username;//uc14

    @NotNull(message = "Please enter your name")//uc19
    @Size(min = 2, max = 30, message = "Your name should be at least 2 chart")//uc20
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of characters") //uc21
    private String name;//uc18

    @NotNull(message = "Please enter your surname")//uc23
    @Size(min = 2, max = 30, message = "Your surname should be at least 2 chart")//uc24
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your surname must consist of characters") //uc25
    private String surname;//uc22

    @NotNull(message = "Please enter your birt day") //uc27
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") //uc28
    @Past(message = "Your birth day can not be in the future") //uc29
    private LocalDate birtDay;//uc26

    @NotNull(message = "Please enter your ssn") //uc31
    @Pattern(regexp = "^(?!000/666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$",
            message = "Please enter valid SSN number")//uc32
    private String ssn; //uc30

    @NotNull(message = "Please enter your birthPlace")//uc34
    @Size(min = 2, max = 30, message = "Your birthPlace should be at least 2 chart")//uc35
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your birthPlace must consist of characters") //uc36
    private String birthPlace;//uc33

    @NotNull(message = "Please enter your phoneNumber")//uc38
    @Size(min = 2, max = 30, message = "Your phoneNumber should be at least 2 chart")//uc39
    @Pattern(regexp = "^((\\(\\d{3}\\))/\\d{3})[-.]?\\d{3}[-.]?\\d{4}$", message = "Your phoneNumber must consist of characters") //uc40
    private String phoneNumber;//uc37

    @NotNull(message = "Please enter your gender")//uc42
    private Gender gender;//uc41

    @NotNull(message = "Please enter your email")//uc44
    @Email(message = "Please enter valid email")//uc45
    @Size(min = 5, max = 50, message = "Your email should be between 5 and 50 chars")//uc46
    private String email; //uc43
}//uc09

//uniqlik kontrolu buralarda yapilamaz uniqlik kontrolu DB katmaninda yapilabilir ama bu unieqlik kontrolunü yapmak zorundayiz peki nerede
//yapmaliyiz o zaman DB her harükalde gidecegiz ve DB bu kontrolu yapacagiz Unique neler olmasi gerekiyor 1-Username 2-ssn 3- email 4-phonenumber
//yeni bir kullanici kaydedilirken bu 4 veri unique olmalidir Uniue kontrolü servis katmaninda yapilmasi gerekiyor


/*todo -->uc47 ikinci abraks class yapiyoruz ve bu klastan extend edecegiz BaseUserRequest03uc*/
