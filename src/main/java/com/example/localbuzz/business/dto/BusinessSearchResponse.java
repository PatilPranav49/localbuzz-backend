package com.example.localbuzz.business.dto;

import com.example.localbuzz.business.entity.BusinessCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessSearchResponse {

    private Long id;
    private String name;
    private BusinessCategory category;
    private String address;
}