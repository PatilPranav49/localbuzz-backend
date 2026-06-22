package com.example.localbuzz.business.service;

import com.example.localbuzz.business.dto.BusinessResponse;
import com.example.localbuzz.business.dto.CreateBusinessRequest;

import java.util.List;

public interface BusinessService {

    BusinessResponse createBusiness(
            CreateBusinessRequest request,
            Long ownerId
    );

    List<BusinessResponse> getMyBusinesses(
            Long ownerId
    );

    BusinessResponse approveBusiness(
            Long businessId
    );

    BusinessResponse rejectBusiness(
            Long businessId
    );
}