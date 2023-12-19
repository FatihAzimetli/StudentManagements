package com.project.repository;

import com.project.entity.concretes.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //s40 opsiyonel
public interface UserRepository06 extends JpaRepository <User, Long> {
    User findByUsernameEquals(String username);//s43
//!!! alttaki ve üstteki ayni isi yapiyor
    User findByUsername(String username);//lg84 burada Equals data cpa'da olsada olmasada ayni isi yapiyor equels kod okunurlugunu artirir

    boolean existsByUsername(String username);//uc68 bu UniquePropertyValidator05uc türetilen qiword dur

    boolean existsBySsn(String username); //uc72


    boolean existsByPhoneNumber(String phone); //uc76


    boolean existsByEmail(String email); //uc80
}//s38


/*öncelikle bu interface clasa JpaRepository extend ediyoruz daimod opreatörüne kendi yazdigimiz
User entity class buraya import ediyoruz*/

/*-->l85 icin AuthenticationService05lg gidiyoruz*/


