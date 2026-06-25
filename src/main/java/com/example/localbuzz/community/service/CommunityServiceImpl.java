package com.example.localbuzz.community.service;

import com.example.localbuzz.community.dto.CommunityPostRequest;
import com.example.localbuzz.community.dto.CommunityPostResponse;
import com.example.localbuzz.community.entity.CommunityPost;
import com.example.localbuzz.community.repository.CommunityPostRepository;
import com.example.localbuzz.user.entity.Role;
import com.example.localbuzz.user.entity.User;
import com.example.localbuzz.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityPostRepository communityPostRepository;
    private final UserRepository userRepository;

    @Override
    public CommunityPostResponse createPost(
            CommunityPostRequest request,
            Long userId
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (user.getRole() != Role.COMMUNITY_ADMIN
                && user.getRole() != Role.ADMIN) {

            throw new RuntimeException(
                    "Only Community Admin can post");
        }

        CommunityPost post = new CommunityPost();

        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setType(request.getType());
        post.setLatitude(request.getLatitude());
        post.setLongitude(request.getLongitude());
        post.setCreatedBy(user);

        CommunityPost saved =
                communityPostRepository.save(post);

        return mapToResponse(saved);
    }

    @Override
    public List<CommunityPostResponse> getPosts(
            Double latitude,
            Double longitude,
            Double radiusKm
    ) {

        return communityPostRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .filter(post -> {

                    double distance =
                            calculateDistance(
                                    latitude,
                                    longitude,
                                    post.getLatitude(),
                                    post.getLongitude()
                            );

                    return distance <= radiusKm;
                })
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deletePost(
            Long postId,
            Long userId
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        CommunityPost post =
                communityPostRepository.findById(postId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Post not found"));

        if (user.getRole() == Role.ADMIN
                || post.getCreatedBy()
                .getId()
                .equals(userId)) {

            communityPostRepository.delete(post);
            return;
        }

        throw new RuntimeException(
                "Not authorized");
    }

    private CommunityPostResponse mapToResponse(
            CommunityPost post
    ) {

        return CommunityPostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .type(post.getType())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .authorName(
                        post.getCreatedBy().getName()
                )
                .createdAt(post.getCreatedAt())
                .build();
    }

    private double calculateDistance(
            double lat1,
            double lon1,
            double lat2,
            double lon2
    ) {

        final int EARTH_RADIUS = 6371;

        double latDistance =
                Math.toRadians(lat2 - lat1);

        double lonDistance =
                Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(latDistance / 2)
                        * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2)
                        * Math.sin(lonDistance / 2);

        double c =
                2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a)
                );

        return EARTH_RADIUS * c;
    }
}