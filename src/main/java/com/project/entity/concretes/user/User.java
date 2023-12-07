package com.project.entity.concretes.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.entity.concretes.business.StudentInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter//302
@Setter//303
@AllArgsConstructor//304
@NoArgsConstructor//305
@Builder(toBuilder = true)//306

@Entity //307
@Table(name = "t_user") //308



public class User {


    @Id //310
    @GeneratedValue(strategy = GenerationType.IDENTITY)//311
    private Long id;//309

    @Column(unique = true)//313
    private String username; //312

    @Column(unique = true) //315
    private String ssn; //314 tc kimlik gibi


    private String name;//316


    private String surname;//317


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")//319
    private LocalDate birthDay;//318



    private String birthPlace;//320


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )//322
    private String password;//321


    @Column(unique = true)//324
    private String phoneNumber;//323


    @Column(unique = true)//326
    private String email;//325


    private Boolean built_in;//327 superUser Built_in silinemez hesap


    private String motherName; //328 ögrenci icin


    private String fatherName; //329 ögrenci icin


    private int studentNumber; //330 ögrenci icin


    private boolean isActive;  //331 ögrenci kaydinin devamliligi activ veya pasif



   private Boolean isAdvisor; // 332 User ögretmen ise bunun edUser olup olmadigini kontrol etme ögretmen rehber ise degiskenini true yapacagiz




    private Long advisorTeacherId; // 333 bir ögrenci hangi rehberögretmen ile iliski bunu görmek icin sadece ögrenci icin


    @Enumerated(EnumType.STRING) //336
    private String gender; // 334 ögrencinin cinsiyet bilgisi bu bir enum class from 335 enums class gidiyorum



    @OneToOne //357
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )//358 klaynttan bu bilgi gelirse karsila ama DB bu bilgi giderse rol bilgisini ve password verme.
    private UserRole userRole; //356


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE) //384 StudentInfo Tarafinda ManyToMany iliskisi yazildi
    private List<StudentInfo> studentInfos; //383

}//301

/*User bir student olabilir bir teacher veya admin olabilir. Bu user klasi prentter ve tüm kullanicilara hitap eder
* buraya student kaydedersk bazi klonlar null olacak. Buraya yazdigimiz kodlar ile rolüne göre setliyecegiz*/

/*321-322 hassa veri oldugu icin clant tarafina gitmeyecek (write_only)*/

/* 337 bu asamada userRol claslari ve bunlarin enumtype olusturacagiz RoleType olusturulan class*/


/*359 business pakegdaki entity classs lari yaziyoruz yardimci entityleri bussines tarafina yazacagiz
* 1-StudentInfo class yapiyoruz*/


//TODO : StudentInfo, lessenProgram, meet ile entity class larla iliskilendirilmesi gerekiyor unutmamak icin not aldik


/*383 Buradaki StudentInfo kodu yazmak icin geldik*/
