package com.aluminium.auth.IO.signupIO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupInput {
    private String username;
    private String email;
    private String password;
}
