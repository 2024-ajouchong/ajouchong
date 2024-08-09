package com.ajouchong.service;

import com.ajouchong.dto.AddMemberRequestDto;
import com.ajouchong.entity.Member;
import com.ajouchong.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member save(AddMemberRequestDto requestDto) {
        System.out.println("11 "+requestDto.getName());

        Member member = Member.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .build();

        return memberRepository.save(member);
    }

}
