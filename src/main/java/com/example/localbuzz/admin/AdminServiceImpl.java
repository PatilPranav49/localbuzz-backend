package com.example.localbuzz.admin;

import com.example.localbuzz.business.dto.BusinessResponse;
import com.example.localbuzz.business.entity.Business;
import com.example.localbuzz.business.entity.BusinessStatus;
import com.example.localbuzz.business.repository.BusinessRepository;
import com.example.localbuzz.user.entity.Role;
import com.example.localbuzz.user.entity.User;
import com.example.localbuzz.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(
            BusinessRepository businessRepository,
            UserRepository userRepository
    ) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BusinessResponse> getPendingBusinesses() {

        return businessRepository.findByStatus(BusinessStatus.PENDING)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BusinessResponse approveBusiness(Long businessId) {

        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        business.setStatus(BusinessStatus.APPROVED);

        businessRepository.save(business);

        return mapToResponse(business);
    }

    @Override
    public List<User> getPendingCommunityAdmins() {

        return userRepository.findByRoleAndApproved(
                Role.COMMUNITY_ADMIN,
                false
        );
    }

    @Override
    public User approveCommunityAdmin(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setApproved(true);

        return userRepository.save(user);
    }

    private BusinessResponse mapToResponse(Business business) {

        return BusinessResponse.builder()
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
                .build();
    }
}