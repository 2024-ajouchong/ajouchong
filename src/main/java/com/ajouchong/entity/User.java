package com.ajouchong.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, length = 50)
    private String u_name;

    @Column(nullable = false, length = 50)
    private String u_major;

    @Column(nullable = false)
    private String u_pwd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private UserRole u_grade;

    public User() {}

    public User(String u_name, String u_major, String u_pwd, UserRole u_grade) {
        this.u_name = u_name;
        this.u_major = u_major;
        this.u_pwd = u_pwd;
        this.u_grade = u_grade;
    }
}
