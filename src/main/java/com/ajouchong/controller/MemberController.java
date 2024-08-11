package com.ajouchong.controller;

import com.ajouchong.common.ApiResponse;
import com.ajouchong.dto.AddMemberRequestDto;
import com.ajouchong.dto.LoginRequestDto;
import com.ajouchong.entity.Member;
import com.ajouchong.exception.DuplicateEmailException;
import com.ajouchong.jwt.JwtAuthenticationResponse;
import com.ajouchong.jwt.JwtTokenProvider;
import com.ajouchong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ApiResponse<Member> signup(@RequestBody AddMemberRequestDto requestDto) {
        try {
            Member member = memberService.save(requestDto);
            return new ApiResponse<>(1, "회원가입에 성공했습니다.", member);
        } catch (DuplicateEmailException e) {
            return new ApiResponse<>(0, "이미 존재하는 이메일입니다.", null);
        }
    }

    @PostMapping("/login")
    public ApiResponse<JwtAuthenticationResponse> authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(authentication.getName());

            JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse(jwt);

            return new ApiResponse<>(1, "로그인에 성공했습니다.", jwtResponse);
        }catch (BadCredentialsException e) {
            return new ApiResponse<>(0, "로그인 실패: 이메일 또는 비밀번호가 올바르지 않습니다.", null);
        }

    }

}
