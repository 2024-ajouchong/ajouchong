package com.ajouchong.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, length = 50)
    private Long user_id;

    @Column(name = "u_name", nullable = false, length = 50)
    private String u_name;

    @Column(name = "u_major", nullable = false, length = 50)
    private String u_major;

    @Column(name = "u_pwd", nullable = false)
    private String u_pwd;

    @Column(name = "u_email", nullable = false)
    private String u_email;

    @Enumerated(EnumType.STRING)
    @Column(name = "u_role", nullable = false)
    private UserRole u_role;

    @Builder
    public User(String u_name, String u_major, String u_pwd, String u_email, UserRole u_role) {
        this.u_name = u_name;
        this.u_major = u_major;
        this.u_pwd = u_pwd;
        this.u_email = u_email;
        this.u_role = u_role;
    }
}
