package com.ajouchong.dto;

import lombok.Data;

@Data
public class AddMemberRequestDto {
    private String name;
    private String email;
    private String password;
}
