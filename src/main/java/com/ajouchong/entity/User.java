package com.ajouchong.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(nullable = false, length = 9, unique = true)
    @Pattern(regexp = "^\\d{9}$", message = "학번은 9자리 숫자여야 합니다.")
    private String id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false, length = 50)
    private String major;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Builder
    public User(String id, String username, String password, String major, String email, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.major = major;
        this.email = email;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
