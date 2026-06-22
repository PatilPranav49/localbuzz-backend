package com.example.localbuzz.update.dto;

import com.example.localbuzz.update.entity.UpdateType;

public class CreateUpdateRequest {

    private Long businessId;
    private String title;
    private String description;
    private UpdateType type;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UpdateType getType() {
        return type;
    }

    public void setType(UpdateType type) {
        this.type = type;
    }
}