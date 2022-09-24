package com.example.mayankdemo.user.controller;

import com.example.mayankdemo.response.ResponseHandler;
import com.example.mayankdemo.user.domain.User;
import com.example.mayankdemo.user.domain.UserLoginDto;
import com.example.mayankdemo.user.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@Validated
@RequestMapping(path="api/v1/user")
@Tag(name = "User Accounts Module", description = "This module is intended to handle all the user accounts related operations.")
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/register", consumes = { "application/json", "application/xml" })
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Registered"),
            @ApiResponse(responseCode = "400", description = "User already exist.")
    })
    public ResponseEntity<Object> register(
            @Parameter(description="User object.", required=true, schema=@Schema(implementation = User.class))
            @Valid @RequestBody User user) {
        userService.registerUser(user);
        return ResponseHandler.generateResponse(
                "User Registered",
                HttpStatus.CREATED);
    }

    @PostMapping(path = "/login", consumes = { "application/json", "application/xml" })
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in"),
            @ApiResponse(responseCode = "401", description = "Invalid Credentials")
    })
    public ResponseEntity<Object> login(
            @Parameter(description="User login Dto.", required=true, schema=@Schema(implementation = UserLoginDto.class))
            @Valid @RequestBody UserLoginDto userLoginDto) {
        String jwtToken = userService.loginUser(userLoginDto);
        return ResponseHandler.generateResponse(
                "User logged in",
                HttpStatus.OK,
                jwtToken);
    }

}
