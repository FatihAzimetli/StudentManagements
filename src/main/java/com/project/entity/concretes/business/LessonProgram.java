package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.entity.concretes.user.User;
import com.project.entity.enums.Day;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity//426

@Getter//427
@Setter //428
@AllArgsConstructor //429
@NoArgsConstructor  //430
@Builder(toBuilder = true) //431
public class LessonProgram {


    @Id //433
    @GeneratedValue(strategy = GenerationType.IDENTITY) //434
    private Long id;//432


    @Enumerated(EnumType.STRING)// 437
    private Day day; //435 günler icin enums class yapilir


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "US") //439
    private LocalTime startTime; // 438 dersin baslama saati


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "US") //441
    private LocalTime stopTime; // 440 dersin baslama saati



    @ManyToMany//480
    @JoinTable(name = "lesson_program_lesson",
            joinColumns = @JoinColumn(name = "lessonprogram_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))//481
    private Set<Lesson> lessons; //479


    @ManyToOne(cascade = CascadeType.PERSIST ) //483
    private EducationTerm educationTerm; //482


    @ManyToMany(mappedBy = "lessonProgramList", fetch = FetchType.EAGER) // 485 User'da iliskiManyToMany
    private Set<User> users;  //484 User lari set liyecegiz



    @PreRemove //486
    private void removeLessonProgramFromUser(){
        users.forEach(user -> user.getLessonProgramList().remove(this)); //488 entityler bu asamada bitti
    }//487
}//425 bu class a EducationTerm clasindan geldik

/*burda ders programlarini yapacagiz örnegin matematik icin ikitane ögretmen atayabiliriz
bu ders haftanin hangi günlerinde olacak ? bu veriyi bizim tutmamiz gerekiyor*/


/*435-436 enums pakeg altinda günler icin class yapimi*/

/*desin baslama ve bitis saatleri olabilir LocalDateTime olustursak idik LocalTime gerek kalmazdi
bu ikisinin mirari dizayni burada aylara bagli olmadan günler üzerinden lesson programi olusturuyoruz
buradaki mantik örnegin her pazartesi mesela matamatik dersinin olacagi gibi*/


/* TODO:Lesson programin lesson lar ile iliskisi var EducationTerm ile iliskisi var, User larla iliskisi var*/

/* 442 sonuncu entity olusturacagiz Meet class i  busines pakeg altina bu rehberlik anlamina geliyor*/

/*482 buray EducationTerm de 476 yapisinin tersini olusturacagiz
* @OneToMany(mappedBy = "educationTerm", cascade = CascadeType.ALL)  //477
   @JsonProperty(access = JsonProperty.Access.READ_ONLY) //478
    private List<LessonProgram> lessonProgram;   //476*/

/*485 User ile uyumlu olmali @JsonIgnore //462
    @ManyToMany //463
    @JoinTable(name = "user_lessonprogram",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_program_id")) //464
    private Set<LessonProgram> lessonProgramList; //461*/

/*// 486 soru :?lesson program silindiginde business logicler ne olmali servis katindaki deletelesson program
    // byid metodunun badisini yazdigimizi düsünelim nelere dikkat etmemiz gerekiyor
    // 1- id varmi? kontrolü 2- iliskilerine ? bakmamk lazim mesela en önemli iliski burada Usurlarla olan iliski
    // o zaman bu programin kullanicilari varmi Kullanicilar varsa User class gidip bakiyorum
     @JsonIgnore //462
    @ManyToMany //463
    @JoinTable(name = "user_lessonprogram",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_program_id")) //464
    private Set<LessonProgram> lessonProgramList; //461 bu silinen yapi hala burada duyor ne yapmam lazim.?
    yada oradaki zorluklari asmak icin sunu yapabiliriz HB life saykilindaki @PreRemove anlami lesson programda birseler
    yapacagim önce onlari yap sonra lesson programi sil Serviste cok sey yazacagimiza burada tek method ile tamamliyoruz*/
