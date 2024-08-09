package com.ajouchong.service;

import com.ajouchong.entity.Member;
import com.ajouchong.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 사용자 이름(email)으로 사용자 정보를 가져오는 메소드
    @Override
    public Member loadUserByUsername(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
