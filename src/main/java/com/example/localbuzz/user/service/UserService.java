    package com.example.localbuzz.user.service;

    import com.example.localbuzz.user.dto.LoginRequest;
    import com.example.localbuzz.user.dto.LoginResponse;
    import com.example.localbuzz.user.dto.RegisterRequest;
    import com.example.localbuzz.user.dto.UserResponse;
    public interface UserService {

        UserResponse register(RegisterRequest request);
        LoginResponse login(LoginRequest request);
    }