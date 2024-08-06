package com.ajouchong.service;

import com.ajouchong.dto.AddUserRequestDto;
import com.ajouchong.entity.User;
import com.ajouchong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequestDto requestDto){
        return userRepository.save(User.builder()
                .email(requestDto.getEmail())
                // 패스워드 암호화
                .password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
                .build()).getId();
    }

}
