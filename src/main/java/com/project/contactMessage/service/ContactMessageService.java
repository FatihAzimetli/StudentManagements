package com.project.contactMessage.service;

import com.project.contactMessage.dto.ContactMessageRequest;
import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import com.project.contactMessage.mapper.ContactMessageMapper;
import com.project.contactMessage.messages.Messages;
import com.project.contactMessage.repository.ContactMessageRepository;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service//21

@RequiredArgsConstructor//22

public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository; //22 servisin konusacagi kontak
    //ContactMessaceService injecser yapiyorum
    private final ContactMessageMapper contactMessageMapper; //82

    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest) {
        //DTO-->PJO dönüsümü yapacagiz baglanti icin mapper pakej olusturduk amacimiz mapleme islemleri icin
        ContactMessage contactMessage = contactMessageMapper.requestToContactMessage(contactMessageRequest);//83-84 degiskeni atamak

        // kullanacagiz DTO-Pjo yada tam tersi pjo-->dto dönüsmleri icin bu pakeg kullanacagiz mapper
        // icinde class olusturuyoruz class adi ContactMessageMapper
        //84 repoya gönderdik
        ContactMessage saveData = contactMessageRepository.save(contactMessage);//85//86 karsilama bunu responsa ceviriyorum
        //ContactClassMapper 87
        return ResponseMessage.<ContactMessageResponse>builder() //96
                .message("ContactMessage Created Successfully")//97
                .status(HttpStatus.CREATED)//98 kod201
                .object(contactMessageMapper.contactMessageToResponse(saveData))//99 -31 ile uyumlu olmali : public class ResponseMessage<E>
                .build();//100 servis
    }//72

    public Page<ContactMessageResponse> getAll(int page, int size, String sort, String type) {
        //bunu DB gönderecegiz
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());//105
        if (Objects.equals(type, "desc")) {
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        }//106 ascendig-descending
        return contactMessageRepository.findAll(pageable).map(contactMessageMapper::contactMessageToResponse);//107 findAll=Page<ContactMessage>
    }//104 getAll üzerinde servis katinda bu metod olussun istedik burada metod referans yapmis olduk

    public Page<ContactMessageResponse> searchByEmail(String email, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());//105
        if (Objects.equals(type, "desc")) {
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());//burada kod tekrari oldu
        }//112 burada kod tekrari olmamasi icin yardimci metod class olusturacagiz
        return contactMessageRepository.findByEmailEquals(email, pageable).map(contactMessageMapper::contactMessageToResponse);
        // 113 repository katina great et findByEmailEquals
       // return contactMessageRepository.findByEmailEquals(email, pageable).map(e->contactMessageMapper.contactMessageToResponse(e));
        //114 devami icin map map(contactMessageMapper::contactMessageToResponse) metodunu yazdik PJO yu ::contactMessageToResponse DTO cevir

    }

    public Page<ContactMessageResponse> searchBySubject(String subject, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());//123
        if (Objects.equals(type, "desc")) {
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        }//120 to 121
        return contactMessageRepository.findBySubjectEquals(subject, pageable).map(contactMessageMapper::contactMessageToResponse);//127 Map metodunu ekleik
    }


    public String deleteById(Long contactMessageId) {
        getContactMessageById(contactMessageId);//136
        contactMessageRepository.deleteById(contactMessageId);//137 to Messages
        return Messages.CONTACT_MESSAGE_DELETED_SUCCESSFULLY; //140

    }//130 String yerene Object geldi elle düzeltme yaptim.
    public ContactMessage getContactMessageById(Long id){
     return contactMessageRepository.findById(id).orElseThrow(
             ()-> new ResourceNotFoundException(Messages.NOT_FOUND_MESSAGE)//Buradan cikacak olan exception ContactMessage exception'dir bu nedemle
             // cntactMassage pakeg icine bir pakeg olusturuyoruz. messages No: 133 excepinlari bu pakeg altindaki class larda toplayacagiz
             // bu pakeg altina bir class olusturduk Messages No;134
     ); //132
    }//131
}  //20
//124 greate metohd findBySubjectEquals to ContactMessageRepository
//128 to ContactMessageController
//132 bu asamada exection clasi olusturuyoruz exception klasini projectte olusturuyoruz uygulmanin genelinde kullanmak icin,
//132 project pakeg altina exception pakeg aciyoruz
//134 to 135 message pakeg altindaki Message clasidir