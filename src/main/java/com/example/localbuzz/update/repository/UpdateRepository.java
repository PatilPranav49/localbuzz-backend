package com.example.localbuzz.update.repository;

import com.example.localbuzz.business.entity.BusinessStatus;
import com.example.localbuzz.update.entity.Update;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface UpdateRepository extends JpaRepository<Update, Long> {

    List<Update> findByBusinessIdOrderByCreatedAtDesc(Long businessId);
    List<Update> findByBusinessStatusOrderByCreatedAtDesc(
            BusinessStatus status
    );
    Optional<Update> findFirstByBusinessIdOrderByCreatedAtDesc(
            Long businessId
    );

}