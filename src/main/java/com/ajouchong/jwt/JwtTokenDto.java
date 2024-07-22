package com.ajouchong.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@AllArgsConstructor
@Getter
public class JwtTokenDto{
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
