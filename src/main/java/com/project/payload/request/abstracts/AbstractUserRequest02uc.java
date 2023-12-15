package com.project.payload.request.abstracts;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    private String ssn; //uc3ß
}//uc09
