package com.example.localbuzz.update.service;

import com.example.localbuzz.business.entity.Business;
import com.example.localbuzz.business.repository.BusinessRepository;
import com.example.localbuzz.update.dto.CreateUpdateRequest;
import com.example.localbuzz.update.dto.UpdateResponse;
import com.example.localbuzz.update.entity.Update;
import com.example.localbuzz.update.repository.UpdateRepository;
import com.example.localbuzz.user.entity.User;
import com.example.localbuzz.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateServiceImpl
        implements UpdateService {

    private final UpdateRepository updateRepository;
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public UpdateServiceImpl(
            UpdateRepository updateRepository,
            BusinessRepository businessRepository,
            UserRepository userRepository
    ) {
        this.updateRepository = updateRepository;
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UpdateResponse createUpdate(
            CreateUpdateRequest request,
            String email
    ) {

        User owner = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Business business = businessRepository
                .findByIdAndOwner(
                        request.getBusinessId(),
                        owner
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Business not found or not owned by user"
                        ));

        Update update = new Update();

        update.setTitle(request.getTitle());
        update.setDescription(request.getDescription());
        update.setType(request.getType());
        update.setBusiness(business);

        updateRepository.save(update);

        return mapToResponse(update);
    }

    @Override
    public List<UpdateResponse> getBusinessUpdates(
            Long businessId
    ) {

        return updateRepository
                .findByBusinessIdOrderByCreatedAtDesc(
                        businessId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private UpdateResponse mapToResponse(
            Update update
    ) {

        UpdateResponse response =
                new UpdateResponse();

        response.setId(update.getId());
        response.setTitle(update.getTitle());
        response.setDescription(update.getDescription());
        response.setType(update.getType());
        response.setCreatedAt(update.getCreatedAt());

        response.setBusinessId(
                update.getBusiness().getId()
        );

        response.setBusinessName(
                update.getBusiness().getName()
        );

        return response;
    }
}