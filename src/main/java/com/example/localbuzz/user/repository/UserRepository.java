package com.example.localbuzz.user.repository;

import com.example.localbuzz.user.entity.Role;
import com.example.localbuzz.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    List<User> findByRoleAndApproved(
            Role role,
            Boolean approved
    );
}
