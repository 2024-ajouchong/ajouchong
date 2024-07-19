package com.ajouchong.dto;


import com.ajouchong.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class ProfileResponse {
    private Long user_id;
    private String u_name;
    private String u_email;
    private String u_major;
    private UserRole u_role;

    public ProfileResponse(Long user_id, String u_name, String u_email, UserRole u_role, String u_major) {
        this.user_id = user_id;
        this.u_name = u_name;
        this.u_email = u_email;
        this.u_role = u_role;
        this.u_major = u_major;
    }
}
