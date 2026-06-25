package com.example.localbuzz.user.dto;

import com.example.localbuzz.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private Role role;

}