package com.ajouchong.service;

import com.ajouchong.dto.AddMemberRequestDto;
import com.ajouchong.entity.Member;
import com.ajouchong.exception.DuplicateEmailException;
import com.ajouchong.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public void changePassword(Member member, String oldPassword, String newPassword) {
        if (!bCryptPasswordEncoder.matches(oldPassword, member.getPassword())) {
            throw new BadCredentialsException("현재 비밀번호가 일치하지 않습니다.");
        }

        member.updatePassword(bCryptPasswordEncoder.encode(newPassword));
        memberRepository.save(member);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다."));
    }
}
