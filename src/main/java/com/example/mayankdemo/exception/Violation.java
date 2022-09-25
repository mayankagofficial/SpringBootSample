package com.example.mayankdemo.exception;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Violation {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
}
