package com.project.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter//l93
@Setter //l94
@AllArgsConstructor//l95
@NoArgsConstructor//l96
public class UpdatePasswordRequest09lg {
    @NotBlank(message = "Pleas provide old password") //l99
    private String oldPassword; //l97

    @NotBlank(message = "Pleas provide new password") //l101
    private String newPassword; //l100

}//l92

/*bu classta iki adet filed olacak eski sifre ve yeni sifre bu durma @Builder yazmaya gerek yok */
