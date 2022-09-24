package com.example.mayankdemo.user.domain;

import com.example.mayankdemo.user.validation.email.ValidEmail;
import com.example.mayankdemo.user.validation.password.ValidPassword;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NotNull
    @NotEmpty
    @ValidEmail
    String email;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;

    @NotNull
    @NotEmpty
    private String tier;
}
