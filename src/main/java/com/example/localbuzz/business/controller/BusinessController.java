package com.example.localbuzz.business.controller;

import com.example.localbuzz.business.dto.BusinessResponse;
import com.example.localbuzz.business.dto.CreateBusinessRequest;
import com.example.localbuzz.business.service.BusinessService;
import com.example.localbuzz.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(
            BusinessService businessService
    ) {
        this.businessService = businessService;
    }

    @PostMapping
    public BusinessResponse createBusiness(
            @RequestBody CreateBusinessRequest request,
            Authentication authentication
    ) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        return businessService.createBusiness(
                request,
                userDetails.getId()
        );
    }

    @GetMapping("/my")
    public List<BusinessResponse> getMyBusinesses(
            Authentication authentication
    ) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        return businessService.getMyBusinesses(
                userDetails.getId()
        );
    }
}