package com.example.localbuzz.admin.controller;

import com.example.localbuzz.business.dto.BusinessResponse;
import com.example.localbuzz.business.service.BusinessService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/businesses")
public class AdminBusinessController {

    private final BusinessService businessService;

    public AdminBusinessController(
            BusinessService businessService
    ) {
        this.businessService = businessService;
    }

    @PostMapping("/{id}/approve")
    public BusinessResponse approveBusiness(
            @PathVariable Long id
    ) {
        return businessService.approveBusiness(id);
    }

    @PostMapping("/{id}/reject")
    public BusinessResponse rejectBusiness(
            @PathVariable Long id
    ) {
        return businessService.rejectBusiness(id);
    }
}