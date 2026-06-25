package com.example.localbuzz.community.controller;

import com.example.localbuzz.community.dto.CommunityPostRequest;
import com.example.localbuzz.community.dto.CommunityPostResponse;
import com.example.localbuzz.community.service.CommunityService;
import com.example.localbuzz.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping
    public CommunityPostResponse createPost(
            @RequestBody CommunityPostRequest request,
            Authentication authentication
    ) {

        CustomUserDetails userDetails =
                (CustomUserDetails)
                        authentication.getPrincipal();

        return communityService.createPost(
                request,
                userDetails.getId()
        );
    }

    @GetMapping
    public List<CommunityPostResponse> getPosts(
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam(defaultValue = "10")
            Double radius
    ) {

        return communityService.getPosts(
                lat,
                lng,
                radius
        );
    }

    @DeleteMapping("/{id}")
    public void deletePost(
            @PathVariable Long id,
            Authentication authentication
    ) {

        CustomUserDetails userDetails =
                (CustomUserDetails)
                        authentication.getPrincipal();

        communityService.deletePost(
                id,
                userDetails.getId()
        );
    }
}