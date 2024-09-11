package com.aluminium.auth.IO.LoginIO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginInput {
    private String email;
    private String password;
}
