package com.example.localbuzz.feed.service;

import com.example.localbuzz.business.entity.BusinessStatus;
import com.example.localbuzz.feed.dto.FeedItemResponse;
import com.example.localbuzz.feed.dto.NearbyBusinessResponse;
import com.example.localbuzz.update.entity.Update;
import com.example.localbuzz.update.repository.UpdateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.localbuzz.business.repository.BusinessRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final UpdateRepository updateRepository;
    private final BusinessRepository businessRepository;
    @Override
    public List<FeedItemResponse> getFeed(
            String category,
            String type,
            String search
    ) {

        List<Update> updates =
                updateRepository.findByBusinessStatusOrderByCreatedAtDesc(
                        BusinessStatus.APPROVED
                );

        return updates.stream()

                .filter(update ->
                        category == null ||
                                update.getBusiness()
                                        .getCategory()
                                        .name()
                                        .equalsIgnoreCase(category)
                )

                .filter(update ->
                        type == null ||
                                update.getType()
                                        .name()
                                        .equalsIgnoreCase(type)
                )

                .filter(update ->
                        search == null ||
                                search.isBlank() ||

                                update.getTitle()
                                        .toLowerCase()
                                        .contains(search.toLowerCase())

                                ||

                                update.getDescription()
                                        .toLowerCase()
                                        .contains(search.toLowerCase())

                                ||

                                update.getBusiness()
                                        .getName()
                                        .toLowerCase()
                                        .contains(search.toLowerCase())
                )

                .map(update -> FeedItemResponse.builder()
                        .updateId(update.getId())

                        .businessId(
                                update.getBusiness().getId()
                        )

                        .businessName(
                                update.getBusiness().getName()
                        )

                        .category(
                                update.getBusiness().getCategory()
                        )

                        .address(
                                update.getBusiness().getAddress()
                        )

                        .coverImageUrl(
                                update.getBusiness().getCoverImageUrl()
                        )

                        .updateType(
                                update.getType()
                        )

                        .title(
                                update.getTitle()
                        )

                        .description(
                                update.getDescription()
                        )

                        .createdAt(
                                update.getCreatedAt()
                        )

                        .build())
                .toList();
    }

    @Override
    public List<NearbyBusinessResponse> getNearbyBusinesses(
            Double latitude,
            Double longitude,
            Double radiusKm
    ) {

        return businessRepository.findByStatus(BusinessStatus.APPROVED)
                .stream()
                .map(business -> {

                    double distance = calculateDistance(
                            latitude,
                            longitude,
                            business.getLatitude(),
                            business.getLongitude()
                    );

                    if (distance > radiusKm) {
                        return null;
                    }

                    String latestUpdate =
                            updateRepository
                                    .findFirstByBusinessIdOrderByCreatedAtDesc(
                                            business.getId()
                                    )
                                    .map(Update::getTitle)
                                    .orElse("No Updates");

                    return NearbyBusinessResponse.builder()
                            .businessId(business.getId())
                            .businessName(business.getName())
                            .category(business.getCategory())
                            .latitude(business.getLatitude())
                            .longitude(business.getLongitude())
                            .latestUpdate(latestUpdate)
                            .distanceKm(
                                    Math.round(distance * 100.0) / 100.0
                            )
                            .build();
                })
                .filter(java.util.Objects::nonNull)
                .toList();
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