package com.ajouchong.service;

import com.ajouchong.dto.AddMemberRequestDto;
import com.ajouchong.entity.Member;
import com.ajouchong.exception.DuplicateEmailException;
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
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
                .student_id(requestDto.getStudent_id())
                .major(requestDto.getMajor())
                .role(requestDto.getRole())
                .build();

        return memberRepository.save(member);
    }

}
