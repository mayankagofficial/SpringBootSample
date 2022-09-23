package com.example.mayankdemo.security.jwt;

import com.example.mayankdemo.user.domain.User;

public interface JwtTokenService {

    String createJwtToken(User user);

    boolean verifyJwtToken(String jwtToken);

    String getUserNameFromJwtToken(String token);
}
