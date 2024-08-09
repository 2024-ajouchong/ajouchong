package com.ajouchong.controller;

import com.ajouchong.common.ApiResponse;
import com.ajouchong.dto.AddMemberRequestDto;
import com.ajouchong.entity.Member;
import com.ajouchong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<Member> signup(@RequestBody AddMemberRequestDto requestDto) {
        Member member = memberService.save(requestDto);
        return new ApiResponse<>(1, "회원가입에 성공했습니다.", member);
    }
}
