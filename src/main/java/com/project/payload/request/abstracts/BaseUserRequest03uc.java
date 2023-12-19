package com.project.payload.request.abstracts; //dto

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuperBuilder//uc48

@Data //uc49
@AllArgsConstructor//uc50
@NoArgsConstructor//uc51

public abstract class BaseUserRequest03uc extends AbstractUserRequest02uc{

    @NotNull(message = "Please enter your password")//uc52
    @Size(min = 6, max = 60, message = "Your password should be at least 6 chars or maximum 60 chars")//uc53
    private String password;//uc51

    private Boolean builtIn = false; //uc54
}//uc47

/*todo bir sonraki adim SaveUser04uc class yapacagiz bu request pkag altina user pakeg acilacak
*  pakeg adi user bunun altina UserRequest04uc class yapiyoruz -->uc55 */
