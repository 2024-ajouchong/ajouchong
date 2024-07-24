package com.ajouchong.dto;

import com.ajouchong.entity.User;
import com.ajouchong.entity.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRegistrationRequestDtoTest {

    @Test
    public void testToEntity() {
        // Given
        UserRegistrationRequestDto dto = new UserRegistrationRequestDto();
        dto.setId("123456789");
        dto.setUsername("john_doe");
        dto.setPassword("password123");
        dto.setEmail("john.doe@ajou.ac.kr");
        dto.setMajor("Computer Science");
        dto.setRole(UserRole.STUDENT);

        // When
        User user = dto.toEntity();

        // Then
        assertNotNull(user);
        assertEquals(dto.getId(), user.getId());
        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getPassword(), user.getPassword());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getMajor(), user.getMajor());
        assertEquals(dto.getRole(), user.getRole());
    }
}