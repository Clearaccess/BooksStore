package com.university.books.store.service.impl;

import com.university.books.store.model.entity.RoleEntity;
import com.university.books.store.model.entity.UserEntity;
import com.university.books.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr on 6/4/2017.
 */
@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        UserEntity userByLogin=userService.findByLogin(ssoId);
        UserEntity userByEmail=userService.findByEmail(ssoId);

        if(userByLogin==null && userByEmail==null){
            System.out.println("User not found");
            throw  new UsernameNotFoundException("Username not found");
        }

        UserEntity user=getNotNull(userByLogin,userByEmail);

        return new User(user.getLogin(),user.getPassword(),getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserEntity user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(RoleEntity role : user.getRoles()){
            System.out.println("UserRole : "+role.getDescription());
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getDescription()));
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }

    private UserEntity getNotNull(UserEntity userByLogin,UserEntity userByEmail){
        return userByLogin!=null? userByLogin:userByEmail;
    }
}
