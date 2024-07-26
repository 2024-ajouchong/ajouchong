package com.ajouchong.dto;

import com.ajouchong.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegistrationResponseDto {
    private String id;
    private String username;
    private String password;
    private String email;
    private String major;
    private UserRole role;
}
