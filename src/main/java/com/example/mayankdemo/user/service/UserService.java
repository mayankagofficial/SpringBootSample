package com.example.mayankdemo.user.service;

import com.example.mayankdemo.user.domain.User;
import com.example.mayankdemo.user.domain.UserLoginDto;

public interface UserService {

    void registerUser(User user);
    String loginUser(UserLoginDto userLoginDto);

}
