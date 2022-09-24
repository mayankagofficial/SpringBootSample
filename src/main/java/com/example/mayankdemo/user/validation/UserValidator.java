package com.example.mayankdemo.user.validation;

import  com.example.mayankdemo.user.domain.UserLoginDto;
import com.example.mayankdemo.security.config.PasswordEncoder;
import com.example.mayankdemo.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class UserValidator{

    PasswordEncoder passwordEncoder;

    public void validatePassword(UserLoginDto userToVerify, User userStored){
        if(!passwordEncoder.bCryptPasswordEncoder()
                .matches(userToVerify.getPassword(), userStored.getPassword())){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid credentials."
            );
        }
    }
    public void validateTier(User user, String tier) {
        if(!user.getTier().equalsIgnoreCase(tier)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "User Tier invalid."
            );
        }
    }
}
