package com.ajouchong.Controller;

import com.ajouchong.dto.SignUpRequest;
import com.ajouchong.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signupRequest) {
        userService.signup(signupRequest);
        return ResponseEntity.ok("회원가입 요청이 성공적으로 처리되었습니다. 이메일을 확인해주세요.");
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmEmail(@RequestParam("token") String token) {
        userService.confirmEmail(token);
        return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
    }
}
