package com.aluminium.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokensDto {
    String accessToken;
    String refreshToken;
}
