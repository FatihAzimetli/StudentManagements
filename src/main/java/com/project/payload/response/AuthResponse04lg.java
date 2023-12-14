package com.project.payload.response;//dto

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//l22
@AllArgsConstructor//l23
@NoArgsConstructor//l24
@Builder //l25 ikiden fazla degisken oldugu icin kullandik
@JsonInclude(JsonInclude.Include.NON_NULL) //l31 Buradaki gönderilmeyen degerler Json ciktida null gözükmesin istiyorsak bu komut konullanilir

public class AuthResponse04lg {

    private String username; //l226
    private String ssn; //l27
    private String role; //l28
    private String token; // l29
    private String name; //l30

}//l21


/*User tarafinda 20 den fazla degisken var hepsini göndermek zorunda degiliz */
