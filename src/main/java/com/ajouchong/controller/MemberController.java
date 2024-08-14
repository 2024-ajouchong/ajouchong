package com.ajouchong.controller;

import com.ajouchong.common.ApiResponse;
import com.ajouchong.dto.AddMemberRequestDto;
import com.ajouchong.dto.ChangePasswordRequestDto;
import com.ajouchong.dto.LoginRequestDto;
import com.ajouchong.entity.Member;
import com.ajouchong.exception.DuplicateEmailException;
import com.ajouchong.jwt.JwtAuthenticationResponse;
import com.ajouchong.jwt.JwtTokenProvider;
import com.ajouchong.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request){
        return new ApiResponse<>(1, "로그아웃에 성공했습니다.", null);
    }

    @PatchMapping("/changePw")
    public ApiResponse<String> changePassword(@RequestHeader("Authorization") String token,
                                              @RequestBody ChangePasswordRequestDto requestDto) {

        String jwt = token.substring(7);
        String email = jwtTokenProvider.getUsernameFromJWT(jwt);

        Member member = memberService.findByEmail(email);
        memberService.changePassword(member, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ApiResponse<>(1, "비밀번호가 성공적으로 변경되었습니다.", null);
    }

}
