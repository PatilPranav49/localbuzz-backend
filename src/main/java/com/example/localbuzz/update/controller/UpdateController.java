package com.example.localbuzz.update.controller;

import com.example.localbuzz.update.dto.CreateUpdateRequest;
import com.example.localbuzz.update.dto.UpdateResponse;
import com.example.localbuzz.update.service.UpdateService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UpdateController {

    private final UpdateService updateService;

    public UpdateController(
            UpdateService updateService
    ) {
        this.updateService = updateService;
    }

    @PostMapping("/updates")
    public UpdateResponse createUpdate(
            @RequestBody CreateUpdateRequest request,
            Authentication authentication
    ) {

        return updateService.createUpdate(
                request,
                authentication.getName()
        );
    }

    @GetMapping("/businesses/{id}/updates")
    public List<UpdateResponse> getUpdates(
            @PathVariable Long id
    ) {

        return updateService
                .getBusinessUpdates(id);
    }
}