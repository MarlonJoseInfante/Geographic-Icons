package com.alkemy.icons.icons.auth.service;

import com.alkemy.icons.icons.auth.dto.UserDTO;
import com.alkemy.icons.icons.auth.entities.UserEntity;
import com.alkemy.icons.icons.auth.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private EmailService emailService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity= userEntityRepository.findByUsername(username);
        if (userEntity==null){
            throw new UsernameNotFoundException("User or Password not fount");
    }
            return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
}
    public boolean save(UserDTO userDTO){

        UserEntity userEntity= new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity= this.userEntityRepository.save(userEntity);
        if (userEntity!=null){
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity!=null;
    }
}
