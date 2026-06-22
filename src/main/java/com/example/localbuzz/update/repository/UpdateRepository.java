package com.example.localbuzz.update.repository;

import com.example.localbuzz.update.entity.Update;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UpdateRepository extends JpaRepository<Update, Long> {

    List<Update> findByBusinessIdOrderByCreatedAtDesc(Long businessId);

}