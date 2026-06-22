package com.example.localbuzz.business.dto;

import com.example.localbuzz.business.entity.BusinessCategory;
import com.example.localbuzz.business.entity.BusinessStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BusinessResponse {

    private Long id;

    private String name;

    private String description;

    private String address;

    private Double latitude;

    private Double longitude;

    private String phone;

    private String website;

    private String coverImageUrl;

    private BusinessCategory category;

    private BusinessStatus status;

    private Boolean active;

    private LocalDateTime createdAt;
}