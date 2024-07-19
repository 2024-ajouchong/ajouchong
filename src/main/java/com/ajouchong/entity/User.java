package com.ajouchong.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false, length = 9, unique = true)
    @Pattern(regexp = "^\\d{9}$", message = "학번은 9자리 숫자여야 합니다.")
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false, length = 50)
    private String major;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Builder
    public User(String id, String name, String pwd, String major, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.major = major;
        this.email = email;
        this.role = role;
    }
}
