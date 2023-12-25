package com.project.repository;

import com.project.entity.concretes.user.User;
import com.project.payload.response.UserResponse07lg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository //s40 opsiyonel
public interface UserRepository06 extends JpaRepository <User, Long> {
    User findByUsernameEquals(String username);//s43
//!!! alttaki ve üstteki ayni isi yapiyor
    User findByUsername(String username);//lg84 burada Equals data cpa'da olsada olmasada ayni isi yapiyor equels kod okunurlugunu artirir

    boolean existsByUsername(String username);//uc68 bu UniquePropertyValidator05uc türetilen qiword dur

    boolean existsBySsn(String ssn); //uc72


    boolean existsByPhoneNumber(String phone); //uc76


    boolean existsByEmail(String email); //uc80

    @Query("SELECT u FROM User u WHERE u.userRole.rolName = :roleName")//uc137 JPQL -->uc138 UserController01uc class
    Page<User> findByUserByRole(String roleName, Pageable pageable); //uc136 UserService02lg clastan grat ettik
}//s38


/*öncelikle bu interface clasa JpaRepository extend ediyoruz daimod opreatörüne kendi yazdigimiz
User entity class buraya import ediyoruz*/

/*-->l85 icin AuthenticationService05lg gidiyoruz*/

/*todo Page<UserResponse07lg> findByUserByRole(String userRole, Pageable pageable); //uc136 UserService02lg clastan grat ettik
*  burada Userlari istedigimiz icin Response07lg kismini sildik orada tür dönüsümü yapmadigimiz icin burada gelecek olanlar userlardir
*  entity class kendisi gelir buraya bir JPQL yaziyoruz yani Query */


