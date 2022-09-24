package com.example.mayankdemo.user.service;

import com.example.mayankdemo.security.config.PasswordEncoder;
import com.example.mayankdemo.security.jwt.JwtTokenService;
import com.example.mayankdemo.user.domain.User;
import com.example.mayankdemo.user.domain.UserLoginDto;
import com.example.mayankdemo.user.repository.UserRepository;
import com.example.mayankdemo.user.validation.UserValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserValidator userValidator;
    private JwtTokenService jwtTokenService;

    @Override
    public void registerUser(User user) {
        Optional<User> userPresent = userRepository.findByEmail(user.getEmail());
        if(userPresent.isPresent()) {
            LOGGER.error("User already exists - " + user.getEmail() + ", Date and Time : "+LocalDateTime.now());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        } else {
            user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
            user.setLastLoginDate(LocalDateTime.now());
            userRepository.save(user);
        }
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        User loginUser = userRepository.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials"));
        userValidator.validatePassword(userLoginDto, loginUser);
        userValidator.validateTier(loginUser, userLoginDto.getTier());
        userRepository.updateLoginDate(loginUser.getEmail(),LocalDateTime.now());
        return jwtTokenService.createJwtToken(loginUser);
    }
}
