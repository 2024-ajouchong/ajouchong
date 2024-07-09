package com.ajouchong.Service;

import com.ajouchong.Entity.Token;
import com.ajouchong.Entity.User;
import com.ajouchong.Repository.TokenRepository;
import com.ajouchong.Repository.UserRepository;
import com.ajouchong.dto.SignUpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(SignUpRequest signupRequest) {
        // 비밀번호 해싱
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        User user = new User(signupRequest.getUsername(), encodedPassword, signupRequest.getEmail());
        userRepository.save(user);

        // 이메일 인증 토큰 생성
        String token = UUID.randomUUID().toString();
        Token confirmationToken = new Token(token, user);
        tokenRepository.save(confirmationToken);

        // 이메일 전송
        String link = "http://localhost:8080/api/auth/confirm?token=" + token;
        emailService.sendEmail(user.getEmail(), "이메일 인증", "링크를 클릭하여 인증하세요: " + link);
    }

    public void confirmEmail(String token) {
        Token confirmationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("토큰이 유효하지 않습니다."));
        User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        tokenRepository.delete(confirmationToken);
    }
}
