package com.project.contactMessage.mapper;

import com.project.contactMessage.dto.ContactMessageRequest;
import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component//74
public class ContactMessageMapper {
    //request-->PJO
    public ContactMessage requestToContactMessage(ContactMessageRequest contactMessageRequest){
     return ContactMessage.builder()//76
             .name(contactMessageRequest.getName())//77
             .subject(contactMessageRequest.getSubject())//78
             .message(contactMessageRequest.getMessage())//79
             .dateTime(LocalDateTime.now())//80
             .build();//81
    }//75
    //POJO-->Responce(DTO)87
    public ContactMessageResponse contactMessageToResponse(ContactMessage contactMessage){
        return ContactMessageResponse.builder()//89
                .name(contactMessage.getName())//90
                .subject(contactMessage.getSubject())//91
                .message(contactMessage.getMessage())//92
                .email(contactMessage.getEmail())//93
                .dateTime(LocalDateTime.now())//94
                .build();//95
    }//88
}//73
//bu class sigilten stop olusturarrak cagrildigi her yerden gelmesini istiyoruz bunun icin @companent ekliyoruz

/*//94//contactMessage.getDateTime() eski mesaj istenirse bu yazilacak
                //reponce olusma anindaki zaman istenirse dateTime(LocalDateTime.now())bu kullanilacak*/
