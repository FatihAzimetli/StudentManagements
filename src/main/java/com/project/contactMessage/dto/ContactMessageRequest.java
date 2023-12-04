package com.project.contactMessage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data//54
@AllArgsConstructor//55
@NoArgsConstructor//56
@Builder(toBuilder = true)//57
public class ContactMessageRequest {

    @NotNull(message = "Please enter name") //62 notblacte diyebilirdik
    @Size(min = 3, max = 16, message = "Your name should be at least 3 characters")//63
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the character.") //64
    private String name; // 34 - 1ab //s,58

    @Email(message = "Please enter valid email address") //65
    @Size(min = 5, max = 20, message = "Your email should be at least 3 characters")//66
    @NotNull(message = "Please enter email")//67
    private String email;//59

    @NotNull(message = "Please enter subject")//68
    @Size(min = 3, max = 50, message = "Your subject should be at least 3 characters")//69
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your subject must consist of the character.")//70
    private String subject;//60

    @NotNull(message = "Please enter message")//71
    @Size(min = 3, max = 500, message = "Your message should be at least 3 characters")//72
    private String message; //61
}//53
/*bu klasta validatition olmak zorunda cünkü istekciye cevap verebilmemiz icin iletisim bilgilerine burdan ulasacagiz*/
