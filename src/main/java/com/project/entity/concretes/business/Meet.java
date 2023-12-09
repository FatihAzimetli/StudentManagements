package com.project.entity.concretes.business;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.entity.concretes.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity//443


@Data //444
@AllArgsConstructor //445
@NoArgsConstructor //446
@Builder(toBuilder = true) //447
public class Meet {

    @Id //449
    @GeneratedValue (strategy = GenerationType.IDENTITY) //450
    private Long id; //448

    private String description; //449 aciklama anlamina gelir ve doldurmak zorunda degil opsiyonel

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "US")//451
    private LocalDate date; //450 toplanti tarihi

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "US")//453
    private LocalTime startTime; //452



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "US")//455
    private LocalTime stopTime; //454

    @ManyToOne(cascade = CascadeType.PERSIST) //457
    private User advisoryTeacher;//456

    @ManyToMany //459
    @JoinTable(name = "meet_student_table",
            joinColumns = @JoinColumn(name = "meet_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")) //460
    private List<User> studentList; //458
}//442

/*Soru 450-452 bu ikisine LocalDateTime yazssak daha kolay olmazmi idi.?
Buradaki amac query yazarken kolaylik olmasini saglar bu sekilde daha rahat calisir*/

/*burada katilimcilar Userlar yani studentler düzenleyen ise theacher ancak bu rehber ögretmen dolayisi ile burada iki
* degisken olusturacagiz userlar ve rehber ögretmen olarak */

/*bir advisorTeachr 1 den fazla meet olusturabilir ancak bir ögrenci sadece 1 meet e katilabilir ögretmen
* birden fazla meet yapacagi icin Many ama bir ögrenci sadece bir meet istikal edebilecegi icin One  olur*/

/*casket type remoll mu olmali persistmi olmali? burada ogretmenin iki meet olsa ve birisini silerse remov devreye girer ve
* kendisinide siler ortada bir user kalmaz kasket type kullanirken dikkatli olmaliyiz bu silinme olasilsiligindan
* dolayi persist kullanmaliyiz*/

/*460 dan sonra 461 icin User class gidiyorum  meet lessonProgram ve meet iliskilendirmek icin todo listesi*/
