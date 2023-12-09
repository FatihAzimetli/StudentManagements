package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.entity.enums.Term;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity//399

@Data //400
@AllArgsConstructor //401
@NoArgsConstructor //402
@Builder(toBuilder = true) //403 ToBuilder sadece soru geldigi icin yazildi
public class EducationTerm {


    @Id //405
    @GeneratedValue(strategy = GenerationType.IDENTITY) //406
    private Long id;//404


    @NotNull(message = "Education term must not be empty") //412
    @Enumerated(EnumType.STRING) //411
    private Term term; //407 -410 buna enum class yapacagiz yaz,kiS ,KIS ,YAz

   @NotNull(message = "Start date must not be empty") //414
   @Column(name = "start_date") //415
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") //416
   private LocalDate startDate; //413 EcucationTerm baslama ve bitis tarihi


    @NotNull(message = "End date must not be empty") //418
    @Column(name = "end_date") //419
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") //420
    private LocalDate endDate; //417 EcucationTerm baslama ve bitis tarihi



    @NotNull(message = "Last registration date must not be empty") //422
    @Column(name = "last_registration_date") //423
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") //424
    private LocalDate LastRegistrationDate; //421


   @OneToMany(mappedBy = "educationTerm", cascade = CascadeType.ALL)  //477 bir educationTerm icinde birden fazla lesson program olabilir
   @JsonProperty(access = JsonProperty.Access.READ_ONLY) //478
    private List<LessonProgram> lessonProgram;   //476

} //398

/*398'e Lesson clasindan geldik bir entity class*/

/*her classta olmasi gereken standart degiskenler yazilir 1-id,*/

/*407 den 408'e enums pkag gidp Term class yapiyoruz */

/* 409-410 private String term; enum class yaptiktan sonra String silinir yerine class monte edilir*/

/* 421 baslama tarihi belli bitis tarihi belli bu durumda birde kayit tarihi ekliyebiliriz LastRegistration */


/* TODO :Bu class a LessonProgram iliskisi eklenecek*/

/* 425 LessonProgram class yapimi Rehber ögretmenlerin ögrencilere yapacagi rehberlik toplantisini takip edecegimiz
entity class business pakeg altina bu klass yapilir*/

/*476 bazi yerlere List bai yerlere Set koyduk dogru tamaminin set olmasi idi sadece farkliliklar gözüksün diye SET LIST karisik yaptik*/

/*479 icin LessonPrograma gittik*/
