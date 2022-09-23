package com.example.mayankdemo.user.domain;

import com.example.mayankdemo.user.Tiers;
import com.example.mayankdemo.user.validation.email.ValidEmail;
import com.example.mayankdemo.user.validation.enums.ValidEnumValue;
import com.example.mayankdemo.user.validation.password.ValidPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String firstName;
    private String lastName;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;

    @NotNull
    @NotEmpty
    @ValidEmail
    @Column(unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @ValidEnumValue(enumClass = Tiers.class)
    @Schema(hidden = true)
    private String tier = Tiers.USER.name();

    //Admin panel can lock/unlock the account and also used in heartbeat api
    @Schema(hidden = true)
    private Boolean isAccountNonLocked = true;

    //After confirming OTP while registration
    @Schema(hidden = true)
    private Boolean isEnabled = false;

    //Disable it from cron job after 180 days of last login and also used in heartbeat api
    @Schema(hidden = true)
    private Boolean isAccountNonExpired = true;

    //Not used yet
    @Schema(hidden = true)
    private Boolean isCredentialsNonExpired = true;

    @Schema(hidden = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinedDate;

    @Schema(hidden = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginDate;

    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    @Schema(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Tiers.valueOf(tier).getGrantedAuthorities();
    }

    @Override
    @Schema(hidden = true)
    public String getUsername() {
        return email;
    }

    @Override
    @Schema(hidden = true)
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    @Schema(hidden = true)
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    @Schema(hidden = true)
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    @Schema(hidden = true)
    public boolean isEnabled() {
        return isEnabled;
    }
}
