package com.ajouchong.dto;


import com.ajouchong.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class ProfileResponse {
    private String id;
    private String name;
    private String major;
    private String email;
    private UserRole role;

    public ProfileResponse(String id, String name, String major, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.email = email;
        this.role = role;
    }
}
