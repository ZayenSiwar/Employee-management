package net.javaguides.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.model.User;

public interface UserService extends UserDetailsService{
    void save(UserRegistrationDto registrationDto, byte[] photo);
    User getUserByEmail(String email);

}