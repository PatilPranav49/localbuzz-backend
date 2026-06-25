package com.example.localbuzz.admin;

import com.example.localbuzz.business.dto.BusinessResponse;
import com.example.localbuzz.user.entity.User;

import java.util.List;

public interface AdminService {

    List<BusinessResponse> getPendingBusinesses();

    BusinessResponse approveBusiness(Long businessId);

    List<User> getPendingCommunityAdmins();

    User approveCommunityAdmin(Long userId);
}