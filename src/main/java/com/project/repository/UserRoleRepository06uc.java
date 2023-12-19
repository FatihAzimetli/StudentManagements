package com.project.repository; //entity

import com.project.entity.concretes.user.UserRole;
import com.project.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //uc97 ---> uc98 servis yazmaya gidiyoruz

public interface UserRoleRepository06uc extends JpaRepository<UserRole, Integer> {

    @Query("SELECT r FROM UserRole r WHERE r.roleType = ?1")//uc104
  Optional<UserRole>  findByEnumRoleEquals(RoleType roleType); //uc103
}//uc96 extend ediyoruz JpaRepository<EntityKlasadi(UserRole), id data türü:integer>



/* todo public interface UserRoleRepository06uc {
    }//uc96 extend ediyoruz JpaRepository<EntityKlasadi(UserRole), id data türü:integer>
    bir sonraki adim usercontroler icin servis yaziyoruz Jwt altindaki service pakeg icinde UserRoleService07uc class olusturduk*/

/*todo UserRoleService07uc great edilen bu metod optional calisacaksa bu option metodu koyuyoruz java utilden
*  Optional<UserRole>  findByEnumRoleEquals(RoleType roleType); //uc103 burda bir JPQL hemen üstüne yaziyoruz Buradan UserRoleService07uc
*   class gittik*/
