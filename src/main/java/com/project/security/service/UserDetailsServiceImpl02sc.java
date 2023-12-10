package com.project.security.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service //s8
@AllArgsConstructor //s0
public class UserDetailsServiceImpl02sc implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    } //s7 inmlement
}//sc6

/* sc28-->private Collection<? extends GrantedAuthority> //sc27 türemis herhangi bir klas ve collectoin  olabilir
* Buray bir parametreli contructer olusturuyoruz (private Collection<? extends GrantedAuthority> authorities) bu
* yapi user dan gelmeyecegi icin bunu secmedik ayrica String role, el ile ekledik ve bu
* List<GrantedAuthority> grantedAuthorities = new ArrayList<>(); kodu araya yazdik*/
