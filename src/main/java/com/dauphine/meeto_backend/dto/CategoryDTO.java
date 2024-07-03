package com.dauphine.meeto_backend.dto;

import java.util.UUID;

public class CategoryDTO {

    private UUID id;
    private String name;

    public CategoryDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
