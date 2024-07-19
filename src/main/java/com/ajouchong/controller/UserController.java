package com.ajouchong.controller;

import com.ajouchong.common.ApiResponse;
import com.ajouchong.dto.ProfileResponseDto;
import com.ajouchong.dto.UserRegistrationRequestDto;
import com.ajouchong.entity.User;
import com.ajouchong.exception.DuplicateEmailException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.ajouchong.service.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Map<String, Object>>> signupUser(@RequestBody @Valid UserRegistrationRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "회원가입에 실패했습니다.", null));
        }

        try{
            User savedUser = userServiceImpl.join(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(1, "회원가입이 완료되었습니다.", Map.of("user", savedUser)));
        }
        catch(DuplicateEmailException ex){
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("errCode", "duplicate_email");
            errorData.put("errMsg", ex.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponse<>(0, "중복된 이메일입니다.", errorData));
        }

    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<Map<String, Object>>> profile() {
        Optional<User> currentUser = userServiceImpl.getCurrentUser();
        if (currentUser.isPresent()) {
            User user = currentUser.get();
            ProfileResponseDto responseDto = new ProfileResponseDto(user.getId(), user.getName(), user.getEmail(), user.getMajor(), user.getRole());
            return ResponseEntity.ok(new ApiResponse<>(1, "사용자 정보 조회 성공", Map.of("user", responseDto)));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(0, "사용자 정보가 없습니다.", null));
        }
    }

}
