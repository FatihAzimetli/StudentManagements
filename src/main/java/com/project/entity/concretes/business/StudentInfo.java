package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.entity.concretes.user.User;
import com.project.entity.enums.Note;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity //360

@Data //361
@AllArgsConstructor//362
@NoArgsConstructor//363
@Builder(toBuilder = true)//364

public class StudentInfo {


    @Id//366
    @GeneratedValue(strategy = GenerationType.IDENTITY)//367
    private Long id; //365


    private Integer absentee; //368 derslere girisis takibi


    private Double midtermExam; //369 sinav


    private Double finalExam; //370 sinav bunlara notnull yazamayiz


    private Double excamAverage; //371 iki sinav ortalamasi


    private String infoNote; //372


    @Enumerated(EnumType.STRING)//376
    private Note letterGrade; //373 ögrencinin aa-bb gibi notu olacagindan burada enumclass olusturacagiz


    @ManyToMany // 378 bir ögretmenin birden fazla studentInfosu olabilir User class danda iliskilendirmek gerekiyor
    @JsonIgnore // 379 infunuploop girmemek icin bu metoduda yazacagiz
    private User teacher; //377


    @ManyToMany //381
    @JsonIgnore //382
    private User student; //380

    //TODO : Lesson ve EducationTerm eklenecek

}//359 Student class'dan geldik


/*Bu klas ögrenciye has notlarin tutulacagi sayfadir ögretmen ögrencinin
 notunu girip yazacak ve ögrenci buradan notunu ögrenecek*/

/*374 enums pakeg gidiyorum katagorik notlar icin Note class yapiyorum*/

/*377-378 Bu clasta teacher ve student icin iki degisken olustururacagiz
*User taraindan bakilinca ÖgrenciÖgretmen icin : OneToMany
*User tarafindan bakilinca OgretmenÖgrenci icin : OneToMany */

/*383 User class dönüyoruz iliskiyi karsilastirmak icin*/
