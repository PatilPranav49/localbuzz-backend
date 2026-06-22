package com.example.localbuzz.business.repository;

import com.example.localbuzz.business.entity.Business;
import com.example.localbuzz.business.entity.BusinessStatus;
import com.example.localbuzz.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface BusinessRepository
        extends JpaRepository<Business, Long> {

    List<Business> findByOwner(User owner);

    List<Business> findByStatus(BusinessStatus status);
    Optional<Business> findByIdAndOwner(
            Long id,
            User owner
    );

}