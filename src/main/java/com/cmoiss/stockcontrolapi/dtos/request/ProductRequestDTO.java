package com.cmoiss.stockcontrolapi.dtos.request;

import java.util.List;

public record ProductRequestDTO(
        String name,
        String category,
        List<VolumeVariationRequestDTO> volumeVariations
) {
    public String getCategoryName() {
        return category;
    }
}
