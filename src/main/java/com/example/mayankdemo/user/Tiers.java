package com.example.mayankdemo.user;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.mayankdemo.user.Permissions.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Tiers {
    ADMIN(Sets.newHashSet(ADMIN_ROLE)),
    USER(Sets.newHashSet(USER_ROLE));

    private Set<Permissions> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
