package com.project.payload.request; //dto

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data//l12
@AllArgsConstructor//l14
@NoArgsConstructor//l15
public class LoginRequest03lg {


    @NotNull(message = "Username must not be empty")//l17
    private String username; //l16

    @NotNull(message = "Password must not be empty")//l19
    private String password; //l18
}//l12

/* l20 --> bunlari yazdiktan sonra AuthenticationController01lg clasina gidiyoruz*/
