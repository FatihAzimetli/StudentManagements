package com.project.contactMessage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
//import java.io.Serializable;public class ContactMessage implements Serializable { bu metoda ihtiyac duymuyoruz
import java.time.LocalDateTime;

@Entity //1-

@Data //2-
@AllArgsConstructor //3-
@NoArgsConstructor  //4-
@Builder(toBuilder = true) //5-
public class ContactMessage {

    @Id//6- JavaPersistence (JPA) göre birincil (Primary key) anahtar
    @GeneratedValue(strategy = GenerationType.IDENTITY)//*7
    @Setter(AccessLevel.NONE)//*8
    private Long id;//5- id kücük harf ile yapiyoruz data JPA da sorun yasamamak icin

    @NotNull//1ß
    private String name; //9

    @NotNull//12
    private String email;//11

    @NotNull//14
    private String subject;//13

    @NotNull//16
    private String message;//15

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",timezone = "US")//16
    private LocalDateTime dateTime; //17

}//1
