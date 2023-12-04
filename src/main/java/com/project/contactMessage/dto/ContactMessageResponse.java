package com.project.contactMessage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data//42
@AllArgsConstructor//43
@NoArgsConstructor//44
@Builder(toBuilder = true)//45
public class ContactMessageResponse {

    private String name;//46
    private String email;//47
    private String subject;//48
    private String message;//40
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")//50
    private LocalDateTime dateTime;//51
}//41
/*buray bilgiler DB dönüyor, bu klasta validasyon yapmiyoruz*/
