package com.example.localbuzz.user.controller;

import com.example.localbuzz.security.CustomUserDetails;
import com.example.localbuzz.security.JwtService;
import com.example.localbuzz.user.dto.LoginRequest;
import com.example.localbuzz.user.dto.LoginResponse;
import com.example.localbuzz.user.dto.RegisterRequest;
import com.example.localbuzz.user.dto.UserProfileResponse;
import com.example.localbuzz.user.dto.UserResponse;
import com.example.localbuzz.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService,
                          JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/user/home")
    public String home() {
        return "User Home";
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getName();
    }

    @GetMapping("/token")
    public String generateToken() {

        String token =
                jwtService.generateToken("pranav@gmail.com");
        return jwtService.extractEmail(token);
    }
    @GetMapping("/me")
    public UserProfileResponse getProfile(
            Authentication authentication
    ) {

        CustomUserDetails userDetails =
                (CustomUserDetails)
                        authentication.getPrincipal();

        return userService.getProfile(
                userDetails.getId()
        );
    }
    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
