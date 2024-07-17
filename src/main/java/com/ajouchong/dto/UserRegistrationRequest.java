package com.ajouchong.dto;

import com.ajouchong.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String u_name;
    private String u_major;
    private String u_pwd;
    private UserRole u_role;
}
