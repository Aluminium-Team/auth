package com.aluminium.auth.IO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokensDto {
    String accessToken;
    String refreshToken;
}
