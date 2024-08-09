package com.ajouchong.service;

import com.ajouchong.dto.AddUserRequestDto;
import com.ajouchong.entity.Member;
import com.ajouchong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member save(AddUserRequestDto requestDto) {
        System.out.println("11 "+requestDto.getName());

        Member member = Member.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .build();

        return userRepository.save(member);
    }

}
