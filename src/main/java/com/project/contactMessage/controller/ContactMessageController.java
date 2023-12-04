package com.project.contactMessage.controller;

import com.project.contactMessage.dto.ContactMessageRequest;
import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import com.project.contactMessage.service.ContactMessageService;
import com.project.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController//24

@RequestMapping("/contactMessages")//25 // http://localhost:8080/contactMessages

@RequiredArgsConstructor//26

public class ContactMessageController {

    private final ContactMessageService contactMessageService; //27// servis kati ile konusabilmek icin

    @PostMapping("/save") //28// http://localhost:8080/contactMessages/save   + POST + JSON

    public ResponseMessage<ContactMessageResponse> save(@RequestBody @Valid ContactMessageRequest contactMessageRequest) {
//52 save(@RequestBody @Valid ContactMessageRequest contactMessageRequest)
        return contactMessageService.save(contactMessageRequest);//30
    }//29
    //101 getAll mesaj kismi
    @GetMapping("/getAll")//101 http://localhost:8080/contactMessage/getAll + GET
    public Page<ContactMessageResponse> getAll(
            @RequestParam(value = "page",defaultValue = "0")int page,
            @RequestParam(value = "size",defaultValue = "10")int size,
            @RequestParam(value = "sort", defaultValue = "dataTime")String sort,
            @RequestParam(value = "type", defaultValue = "desc")String type){
        return contactMessageService.getAll(page,size,sort,type);//103
    }//102
    @GetMapping("/searchByEmail") // 108 http://localhost:8080/contactMessage/searcchByEmail?email=aaa@bbb.com + GET
    public Page<ContactMessageResponse> searchByEmail(
            @RequestParam(value = "email") String email, //110
            @RequestParam(value = "page",defaultValue = "0")int page,
            @RequestParam(value = "size",defaultValue = "10")int size,
            @RequestParam(value = "sort", defaultValue = "dataTime")String sort,
            @RequestParam(value = "type", defaultValue = "desc")String type
    ){
        return contactMessageService.searchByEmail(email,page,size,sort,type);//111 greate metod searchByEmail servise yöneltecek
    }//109
    //115
    @GetMapping("/searchBySubject") // 116  http://localhost:8080/contactMessage/searchBySubject?subject=deneme + GET
    public Page<ContactMessageResponse> searchBySubject(
            @RequestParam(value = "subject") String subject, //118
            @RequestParam(value = "page",defaultValue = "0")int page,
            @RequestParam(value = "size",defaultValue = "10")int size,
            @RequestParam(value = "sort", defaultValue = "dataTime")String sort,
            @RequestParam(value = "type", defaultValue = "desc")String type
    ){
            return contactMessageService.searchBySubject(subject, page, size, sort, type);//119
    }//117
@DeleteMapping("/deleteById/{contactMessageId}") //128  http://localhost:8080/contactMessage/deleteById/1 + DELETE
    public ResponseEntity<String> deleteByIdPath(@PathVariable Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));//130
}//129
} //23
//120 greate metod searchBySubject to ContactMessageService
//130 greate metotd deleteById to ContactMessageService