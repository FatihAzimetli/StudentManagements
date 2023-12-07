package com.project.entity.concretes.user;

import com.project.entity.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity//343
@Table(name = "roles") //344


@Data // 345 buraya Getter Setter yazmak daha mantikli ancak soruldugu icin yazdik
@AllArgsConstructor//346
@NoArgsConstructor//347
@Builder //348
public class UserRole {


    @Id//350
    @GeneratedValue(strategy = GenerationType.IDENTITY) //351
    private Integer id;// 349 kullanici sayisi az olacagi icin long yazmadik


    @Enumerated(EnumType.STRING)//353
    @Column(length = 30) //354
    private RoleType roleType; //352 enum pakegdaki UserRole class ile iliskilendirdik


    private String roleName; //355


}//342


/* 355 bu enum tipin strin ifadesine ulasmak icin string ifade olusturduk
* 356 buradan string ifadeler iliskilendirmek icin User class gittik tek tarafli iliskiyi yazacagiz*/
