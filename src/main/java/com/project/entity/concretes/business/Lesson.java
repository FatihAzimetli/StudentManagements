package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity//387


@Data//388
@AllArgsConstructor//389
@NoArgsConstructor//390
@Builder//391
public class Lesson {


    @Id//393
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //394
    private Long lessonId;//392


    private String lessonName; //395


    private Integer creditScore; //396 dersin kredi puani busineslogicte islem olmayacak


    private Boolean isCompulsory; //397 secmelimi zorunlumu

    @JsonIgnore //475
    @ManyToMany(mappedBy = "lessons", cascade = CascadeType.REMOVE) //474
    private Set<LessonProgram> lessonPrograms;//473
}//386


/*uygulama icinde tüm dersleri bu class ile dizayn edecegiz */


/*Buraya hangi klaslar üzerinden islem yapacagiz.? 1-LessonProgram ile iliskilendirecegiz
* her dersin ders programlari var bu yeterli denildi  Lesson class LessonProgram ile iliskilendirilerek
* bir nevi encupsilation yapmis oluyoruz Lesson klasina ulasmak isteyen diger classlar LessonProgram
* class üerinden ulasacaklar*/


/*397 secmelimi zorunlumu busineslogicte islem olmayacak projeyi büyütmek
    // icin farkli aksiyonlar yapilsin diye bu iki method konuldu ornek ögrenci 40 kredi burada alacak gibi bir
    // sey korsak burasi kullanilacak ancak texte böyle birsey yok*/

/*398 EducationTerm (egitimdönemi) yaz dönemi kis dönemi class yaziyoruz*/

//TODO lesson a LessonProgram üzerinden ulasiliyor 473 ile devam ediyoruz

/*Meet ve User daki tablo isimleri ve yapilanma ayni her iki tablodaki veriler ayni amac her iki taraftan birbirine
* ulasilabilsin istendi ama infonid Loop olusmasin diyede User @JsonIngore annatation yazdik*/

/*473 -474 burasi infinitloop girer bir tarafa JsonIngore etmemiz gerekiyor bu yüzden buraya yazdik*/

/*476 EducationTerm lessonProgram eklemek icin gittk*/