package com.example.localbuzz.business.service;

import com.example.localbuzz.business.dto.BusinessResponse;
import com.example.localbuzz.business.dto.CreateBusinessRequest;
import com.example.localbuzz.business.entity.Business;
import com.example.localbuzz.business.entity.BusinessStatus;
import com.example.localbuzz.business.repository.BusinessRepository;
import com.example.localbuzz.user.entity.User;
import com.example.localbuzz.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public BusinessServiceImpl(
            BusinessRepository businessRepository,
            UserRepository userRepository
    ) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BusinessResponse createBusiness(
            CreateBusinessRequest request,
            Long ownerId
    ) {

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Business business = Business.builder()
                .name(request.getName())
                .description(request.getDescription())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .phone(request.getPhone())
                .website(request.getWebsite())
                .coverImageUrl(request.getCoverImageUrl())
                .category(request.getCategory())
                .status(BusinessStatus.PENDING)
                .active(true)
                .createdAt(LocalDateTime.now())
                .owner(owner)
                .build();

        Business savedBusiness = businessRepository.save(business);

        return BusinessResponse.builder()
                .id(savedBusiness.getId())
                .name(savedBusiness.getName())
                .description(savedBusiness.getDescription())
                .address(savedBusiness.getAddress())
                .latitude(savedBusiness.getLatitude())
                .longitude(savedBusiness.getLongitude())
                .phone(savedBusiness.getPhone())
                .website(savedBusiness.getWebsite())
                .coverImageUrl(savedBusiness.getCoverImageUrl())
                .category(savedBusiness.getCategory())
                .status(savedBusiness.getStatus())
                .active(savedBusiness.getActive())
                .createdAt(savedBusiness.getCreatedAt())
                .build();
    }

    @Override
    public List<BusinessResponse> getMyBusinesses(
            Long ownerId
    ) {

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return businessRepository.findByOwner(owner)
                .stream()
                .map(business ->
                        BusinessResponse.builder()
                                .id(business.getId())
                                .name(business.getName())
                                .description(business.getDescription())
                                .address(business.getAddress())
                                .latitude(business.getLatitude())
                                .longitude(business.getLongitude())
                                .phone(business.getPhone())
                                .website(business.getWebsite())
                                .coverImageUrl(business.getCoverImageUrl())
                                .category(business.getCategory())
                                .status(business.getStatus())
                                .active(business.getActive())
                                .createdAt(business.getCreatedAt())
                                .build()
                )
                .toList();
    }

    @Override
    public BusinessResponse approveBusiness(Long businessId) {

        Business business = businessRepository.findById(businessId)
                .orElseThrow(() ->
                        new RuntimeException("Business not found"));

        business.setStatus(BusinessStatus.APPROVED);

        Business savedBusiness = businessRepository.save(business);

        return BusinessResponse.builder()
                .id(savedBusiness.getId())
                .name(savedBusiness.getName())
                .description(savedBusiness.getDescription())
                .address(savedBusiness.getAddress())
                .latitude(savedBusiness.getLatitude())
                .longitude(savedBusiness.getLongitude())
                .phone(savedBusiness.getPhone())
                .website(savedBusiness.getWebsite())
                .coverImageUrl(savedBusiness.getCoverImageUrl())
                .category(savedBusiness.getCategory())
                .status(savedBusiness.getStatus())
                .active(savedBusiness.getActive())
                .createdAt(savedBusiness.getCreatedAt())
                .build();
    }

    @Override
    public BusinessResponse rejectBusiness(Long businessId) {

        Business business = businessRepository.findById(businessId)
                .orElseThrow(() ->
                        new RuntimeException("Business not found"));

        business.setStatus(BusinessStatus.REJECTED);

        Business savedBusiness = businessRepository.save(business);

        return BusinessResponse.builder()
                .id(savedBusiness.getId())
                .name(savedBusiness.getName())
                .description(savedBusiness.getDescription())
                .address(savedBusiness.getAddress())
                .latitude(savedBusiness.getLatitude())
                .longitude(savedBusiness.getLongitude())
                .phone(savedBusiness.getPhone())
                .website(savedBusiness.getWebsite())
                .coverImageUrl(savedBusiness.getCoverImageUrl())
                .category(savedBusiness.getCategory())
                .status(savedBusiness.getStatus())
                .active(savedBusiness.getActive())
                .createdAt(savedBusiness.getCreatedAt())
                .build();
    }
}