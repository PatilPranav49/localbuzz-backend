package com.example.localbuzz.admin;

import com.example.localbuzz.business.dto.BusinessResponse;
import com.example.localbuzz.user.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Admin Dashboard";
    }

    @GetMapping("/businesses/pending")
    public List<BusinessResponse> getPendingBusinesses() {
        return adminService.getPendingBusinesses();
    }

    @PutMapping("/businesses/{id}/approve")
    public BusinessResponse approveBusiness(
            @PathVariable Long id
    ) {
        return adminService.approveBusiness(id);
    }

    @GetMapping("/community-admins/pending")
    public List<User> getPendingCommunityAdmins() {
        return adminService.getPendingCommunityAdmins();
    }

    @PutMapping("/community-admins/{id}/approve")
    public User approveCommunityAdmin(
            @PathVariable Long id
    ) {
        return adminService.approveCommunityAdmin(id);
    }
}