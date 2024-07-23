package com.ajouchong.config;

import com.ajouchong.jwt.JwtTokenProvider;
import com.ajouchong.repository.UserRepository;
import com.ajouchong.service.UserService;
import com.ajouchong.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public SpringConfig(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository, passwordEncoder, authenticationManager, jwtTokenProvider);
    }

//    @Bean
//    public MemoryUserRepository userRepository() {
//        return new MemoryUserRepository();
//    }

}
