package com.ajouchong.controller;

import com.ajouchong.dto.AddUserRequestDto;
import com.ajouchong.entity.Member;
import com.ajouchong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody AddUserRequestDto requestDto) {
        Member member = userService.save(requestDto);
        return ResponseEntity.ok().body(member);
    }
}
