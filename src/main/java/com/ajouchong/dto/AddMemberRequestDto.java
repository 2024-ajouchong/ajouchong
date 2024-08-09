package com.ajouchong.dto;

import lombok.Data;

@Data
public class AddUserRequestDto {
    private String name;
    private String email;
    private String password;
}
