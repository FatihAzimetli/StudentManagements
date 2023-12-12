package com.project.security.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data //s2
@AllArgsConstructor //s3
@NoArgsConstructor //s4

public class UserDetailsImpl01sc implements UserDetails {

    private Long id; //s20
    private String username;//s21
    private String name; //s22
    private Boolean isAdvisor; //s23 kullanici rehberögretmen mi
    @JsonIgnore //s25
    private String password; //s24 pasword yanlislikla servis katina gitmemesi icinJsonIngore ile engelliyoruz
    private String ssn; //s26 unic olan kimlik numarasi
    private Collection<? extends GrantedAuthority> authorities; //s27

    // s28Burada constructor olusturuyorum user bilgilerini buraya almak icin en alttaki authorities almadik user dan gelmedigi icin
    //ayrica String role bilgisini ekledik bunu ekledigimiz icin setlemek icin bir kod yaziyoruz s29-30


    public UserDetailsImpl01sc(Long id, String username, String name, Boolean isAdvisor, String password, String role, String ssn) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.isAdvisor = isAdvisor;
        this.password = password;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(); //s29
        grantedAuthorities.add(new SimpleGrantedAuthority(role)); //s 30
        this.authorities = grantedAuthorities;//s27 buraya setlenmesini sagladik Authorise: roller
        this.ssn = ssn;
    } //s28

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // s31 return null; sildik null dönmesini istemiyoruz s27
    }

    @Override
    public String getPassword() {
        return password; //s31 return null;--> password
    }

    @Override
    public String getUsername() {
        return username; //s32 null kaldirdik
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //s33 False true yaptik
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // s34 true yaptik
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //s35 true
    }

    @Override
    public boolean isEnabled() {
        return true; //s36 true
    }
    //s37 buraya equels medodu yazilir
    public boolean equals(Object o){
      if (this == o)
          return true;
      if (o == null || getClass() != o.getClass()){
          return false;
        }
      UserDetailsImpl01sc user = (UserDetailsImpl01sc) o ;
      return Objects.equals(id, user.getId());
    }//s37
}//s1 implements UserDetails page kizardi üzerinden implemet yaptik ve metodlar otomatik olustu

/*s6. metodlarin ici bos impl ve set yapacagimiz class lari ici bos sekilde olusturmaya basliyouz*/
