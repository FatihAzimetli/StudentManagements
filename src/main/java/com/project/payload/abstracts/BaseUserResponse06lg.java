package com.project.payload.abstracts;


import com.project.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data //l57
@AllArgsConstructor //l58
@NoArgsConstructor //l59
@SuperBuilder //l60
public abstract class BaseUserResponse06lg {

    private Long userId; //l61
    private String username; //l62
    private String name;  //l63
    private String surname; //l64
    private LocalDate birthDay; //l65
    private String ssn; //l66
    private String birthPlace; //l67
    private String phoneNumber; //l68
    private Gender gender ; //l69
    private String email; //l70
    private String userRole; //l71

} //l55-l56


/*public  class BaseUserResponse06lg--> abstract*/

/* //l72 Abstract klas normalde kullanilamaz bunu kullanabilmek icib dto classlar yapariz
* bu classtan ilk dto class yapiyoruz responce pakeb altinda bir class olusturuyoruz UserResponse07lg*/
