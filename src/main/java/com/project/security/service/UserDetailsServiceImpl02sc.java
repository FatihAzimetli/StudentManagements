package com.project.security.service;


import com.project.entity.concretes.user.User;
import com.project.repository.UserRepository06;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service //s8
@AllArgsConstructor //s0
public class UserDetailsServiceImpl02sc implements UserDetailsService {

      private UserRepository06 userRepository06; //s41

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository06.findByUsernameEquals(username); //s42
        if (user != null){
            return new UserDetailsImpl01sc(user.getId(),
                    user.getUsername(), user.getName(),false,
                    user.getPassword(), user.getUserRole().getRoleType().name(),user.getSsn());
        } //s43
        throw new UsernameNotFoundException("User' " + username + "not found"); //s44
    } //s7 inmlement öncelikle null sildik return null;
}//sc6

/* sc28-->private Collection<? extends GrantedAuthority> //sc27 türemis herhangi bir klas ve collectoin  olabilir
* Buray bir parametreli contructer olusturuyoruz (private Collection<? extends GrantedAuthority> authorities) bu
* yapi user dan gelmeyecegi icin bunu secmedik ayrica String role, el ile ekledik ve bu
* List<GrantedAuthority> grantedAuthorities = new ArrayList<>(); kodu araya yazdik*/

/* s38 öncelikle buraya UserRepositoryi injection yapiyoruz bu nedenle UserRepository06 class olusturduk
* tekrar buraya döndük injection yapmak icin*/

/*buraya kendi entity class'imizdaki User import edecegiz findByUsernameEquals bu kizariyor ve UserReopositoryde imp ediyoruz */

/*if (user != null){
            return new UserDetailsImpl01sc(user.getId(),user.getUsername(), user.getName(),false,)
            false olan this.isAdvisor = isAdvisor; (UserDetailImpl01 class da
        } //s43*/

/*user.getUserRole()) bunu clas aldigimiz icin bunun enum gidiyoruz
*  if (user != null){
            return new UserDetailsImpl01sc(user.getId(),
                    user.getUsername(), user.getName(),false,
                    user.getPassword(), user.getUserRole().getRoleType().name,user.getSsn());
                    * urayi yazarken dikkatli yavas yap*/

/*hata olursa class düzeltecegim
* public class UserDetailsServiceImpl02sc implements UserDetailsService {

    private UserRepository06 userRepository06; //s41

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository06.findByUsernameEquals(username); //s42
        if (user != null) {
            return new UserDetailsImpl01sc(
                    user.getId(),
                    user.getUsername(),
                    user.getName(),
                    false,
                    user.getPassword(),
                    RoleType.valueOf(user.getUserRole().getRoleType().name()), // Değişiklik burada
                    user.getSsn()
            );
        } //s43
        throw new UsernameNotFoundException("User '" + username + "' not found");
    } //s7
}*/
