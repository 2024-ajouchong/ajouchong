package com.ajouchong.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Column(unique = true)
    @NotBlank(message = "학번을 입력해주세요.")
    @Pattern(
            regexp = "^\\d{9}$",
            message = "학번은 9자리 숫자여야 합니다."
    )
    private String id;

    @Column
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 1, max = 5)
    private String username;

    @Column
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{9,}$",
            message = "비밀번호는 영어+숫자 조합으로 9자리 이상이어야 합니다."
    )
    private String password;

    @Column(unique = true)
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@ajou.ac.kr$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Column
    @NotNull(message = "학과를 선택해주세요.")
    private String major;

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull(message = "일반 학생/학생회를 선택해주세요.")
    private UserRole role;

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

    @Builder
    public User(String id, String username, String password, String major, String email, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.major = major;
        this.email = email;
        this.role = role;
    }
}
