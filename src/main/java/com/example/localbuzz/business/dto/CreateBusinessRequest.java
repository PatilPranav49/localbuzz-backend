package com.example.localbuzz.business.dto;

import com.example.localbuzz.business.entity.BusinessCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBusinessRequest {



    private String description;

    private String phone;

    private String website;

    private String coverImageUrl;


    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private BusinessCategory category;
}