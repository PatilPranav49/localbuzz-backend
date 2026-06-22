package com.example.localbuzz.update.service;

import com.example.localbuzz.update.dto.CreateUpdateRequest;
import com.example.localbuzz.update.dto.UpdateResponse;

import java.util.List;

public interface UpdateService {

    UpdateResponse createUpdate(
            CreateUpdateRequest request,
            String email
    );

    List<UpdateResponse> getBusinessUpdates(
            Long businessId
    );
}