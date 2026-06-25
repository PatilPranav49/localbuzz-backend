package com.example.localbuzz.user.service;

import com.example.localbuzz.security.JwtService;
import com.example.localbuzz.user.dto.*;
import com.example.localbuzz.user.entity.Role;
import com.example.localbuzz.user.entity.User;
import com.example.localbuzz.user.exception.AccountNotApprovedException;
import com.example.localbuzz.user.exception.EmailAlreadyExistsException;
import com.example.localbuzz.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.localbuzz.user.exception.AccountNotApprovedException;
import com.example.localbuzz.user.exception.InvalidCredentialsException;
import com.example.localbuzz.user.exception.UserNotFoundException;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    @Override
    public UserProfileResponse getProfile(
            Long userId
    ) {

        User user =
                userRepository.findById(userId)
                        .orElseThrow();

        return UserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        Role role = request.getRole() != null
                ? request.getRole()
                : Role.USER;

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .approved(role != Role.COMMUNITY_ADMIN)
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }
    @Override
    public LoginResponse login(LoginRequest request) {

        System.out.println("Email entered: " + request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        System.out.println("Stored hash: " + user.getPassword());
        System.out.println("Entered password: " + request.getPassword());

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException("Password mismatch");
        }
        if (!user.getApproved()) {
            throw new AccountNotApprovedException(
                    "Your account is awaiting admin approval."
            );
        }
        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(
                token,
                user.getRole()
        );
    }
}