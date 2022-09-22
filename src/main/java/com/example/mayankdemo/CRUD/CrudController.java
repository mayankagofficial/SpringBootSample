package com.example.mayankdemo.CRUD;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrudController {

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "HEALTH CHECK OK!";
    }

    @GetMapping("/read")
    public String readOperation() {
        return "Nothing to return at the momemnt";
    }
}
