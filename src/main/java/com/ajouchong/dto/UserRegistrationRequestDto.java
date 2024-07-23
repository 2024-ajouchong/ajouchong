package com.ajouchong.dto;

import com.ajouchong.entity.User;
import com.ajouchong.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDto {

    @NotBlank(message = "학번을 입력해주세요.")
    @Pattern(
            regexp = "^\\d{9}$",
            message = "학번은 9자리 숫자여야 합니다."
    )
    private String id;

    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{9,}$",
            message = "비밀번호는 영어+숫자 조합으로 9자리 이상이어야 합니다."
    )
    private String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@ajou.ac.kr$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "학과를 선택해주세요.")
    private String major;

    @NotNull(message = "일반 학생/학생회를 선택해주세요.")
    private UserRole role;

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .major(major)
                .role(role)
                .build();
    }
}
