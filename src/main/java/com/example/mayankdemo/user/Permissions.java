package com.example.mayankdemo.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Permissions {
    ADMIN_ROLE("admin:role"),
    USER_ROLE("user:role");

    private String permission;
}
