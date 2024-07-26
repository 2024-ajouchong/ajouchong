package com.ajouchong.service;

import com.ajouchong.dto.UserRegistrationRequestDto;
import com.ajouchong.dto.UserRegistrationResponseDto;
import com.ajouchong.entity.User;
import com.ajouchong.entity.UserRole;
import com.ajouchong.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, passwordEncoder, null, null);
    }

    @Test
    public void testJoin() {
        // Given
        UserRegistrationRequestDto dto = new UserRegistrationRequestDto();
        dto.setId("123456789");
        dto.setUsername("john_doe");
        dto.setPassword("password123");
        dto.setEmail("john.doe@ajou.ac.kr");
        dto.setMajor("Computer Science");
        dto.setRole(UserRole.STUDENT);

        User user = dto.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Mocking save method
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        UserRegistrationResponseDto savedUser = userService.join(dto);

        // Then
        assertNotNull(savedUser);
        verify(userRepository).save(any(User.class));
    }
}