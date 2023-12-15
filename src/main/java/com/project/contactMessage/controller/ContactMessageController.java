package com.project.contactMessage.controller;

import com.project.contactMessage.dto.ContactMessageRequest;
import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import com.project.contactMessage.service.ContactMessageService;
import com.project.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")//lg131
    public Page<ContactMessageResponse> getAll(
            @RequestParam(value = "page",defaultValue = "0")int page,
            @RequestParam(value = "size",defaultValue = "10")int size,
            @RequestParam(value = "sort", defaultValue = "dataTime")String sort,
            @RequestParam(value = "type", defaultValue = "desc")String type){
        return contactMessageService.getAll(page,size,sort,type);//103
    }//102
    @GetMapping("/searchByEmail") // 108 http://localhost:8080/contactMessage/searcchByEmail?email=aaa@bbb.com + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")//lg132
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
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")//lg133
    public Page<ContactMessageResponse> searchBySubject(
            @RequestParam(value = "subject") String subject, //118
            @RequestParam(value = "page",defaultValue = "0")int page,
            @RequestParam(value = "size",defaultValue = "10")int size,
            @RequestParam(value = "sort", defaultValue = "dataTime")String sort,
            @RequestParam(value = "type", defaultValue = "desc")String type
    ){
            return contactMessageService.searchBySubject(subject, page, size, sort, type);//119
    }//117 Pet verable sistemi
    @DeleteMapping("/deleteById/{contactMessageId}") //128  http://localhost:8080/contactMessages/deleteById/1 + DELETE
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")//todo lg134
    public ResponseEntity<String> deleteByIdPath(@PathVariable Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));//130
    }//129
    //140 requeest param ile yapmak
    @DeleteMapping("/deleteByIdParam") // 141 http://localhost:8080/contactMessages/deleteByIdParam?contactMessageId=1 + DELETE
    public ResponseEntity<String> deleteByIdParam(@RequestParam(value = "contactMessageId") Long contactMessageId ){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));//143
    }//142
    @GetMapping("/getByIdParam") //144 http://localhost:8080/contactMessages/getByIdParam?contactMessageId=1 + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")// todo lg135
    public ResponseEntity<ContactMessage> getByIdWithParam(@RequestParam(value = "contactMessageId")Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.getContactMessageById(contactMessageId));//146
    }//145
    @GetMapping("/getById/{contactMessageId}") //147 petveriablr http://localhost:8080/contactMessages/getById/1 + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")// todo lg136
    public ResponseEntity<ContactMessage> getByIdWithPath(@PathVariable Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.getContactMessageById(contactMessageId));//149
    }//148
    @GetMapping("/searchBetwennDates") //150 petveriablr http://localhost:8080/contactMessages/searchBetwennDates?beginDate=2023-12-05&endDate=2023-12-06 + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")// todo lg137
    public ResponseEntity<List<ContactMessage>> searchBetwennDates(
            @RequestParam(value = "beginDate") String beginDateString,
            @RequestParam(value = "endDate") String endDateString
    ){
       List<ContactMessage> contactMessages= contactMessageService.searchByDateBetween(beginDateString, endDateString); //152
        return ResponseEntity.ok(contactMessages); //153
    }// 151
    @GetMapping("searchBetweenTimes/")//167 http://localhost:8080/contactMessages/searchBetwennTimes?startHour=09&startMinute=00&endHour=17&endMinute=30 + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")// todo lg138
    public ResponseEntity<List<ContactMessage>> searchBetweenTimes(
            @RequestParam(value = "startHour") String startHour,
            @RequestParam(value = "startMinute") String startMinute,
           @RequestParam(value = "endHour") String endHour,
            @RequestParam(value = "endMinute")String endMinute){
      List<ContactMessage> contactMessages = contactMessageService.searchBetweenTimes(startHour,startMinute,endHour,endMinute);//169
    return ResponseEntity.ok(contactMessages);//170
    }//168
} //23
//120 greate metod searchBySubject to ContactMessageService
//130 greate metotd deleteById to ContactMessageService
// 154 searchByDateBetween great ile servis katina gidis
//167 icin ConMesRepo dan geldik
//169-171 gerat mothod ContactMessageService git

/*todo lg131 login metodlari icin geldik*/