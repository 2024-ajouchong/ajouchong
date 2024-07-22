package com.ajouchong.dto;


import com.ajouchong.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class ProfileResponseDto {
    private String id;
    private String username;
    private String major;
    private String email;
    private UserRole role;

    public ProfileResponseDto(String id, String username, String major, String email, UserRole role) {
        this.id = id;
        this.username = username;
        this.major = major;
        this.email = email;
        this.role = role;
    }
}
