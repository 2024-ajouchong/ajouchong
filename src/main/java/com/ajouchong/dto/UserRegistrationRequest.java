package com.ajouchong.dto;

import com.ajouchong.entity.UserRole;
import com.ajouchong.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    private String u_name;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{9,}$",
            message = "비밀번호는 영어+숫자 조합으로 9자리 이상이어야 합니다."
    )
    private String u_pwd;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@ajou.ac.kr$", message = "이메일 형식이 올바르지 않습니다.")
    private String u_email;

    @NotBlank(message = "학과를 선택해주세요.")
    private String u_major;

    @NotBlank(message = "일반 학생/학생회를 선택해주세요.")
    private UserRole u_role;

    public User toEntity() {;
        return User.builder()
                .u_email(u_email)
                .u_pwd(u_pwd)
                .u_role(u_role)
                .build();
    }
}
