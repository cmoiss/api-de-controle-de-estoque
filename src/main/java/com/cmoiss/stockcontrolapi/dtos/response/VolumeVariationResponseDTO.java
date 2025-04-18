package com.cmoiss.stockcontrolapi.dtos.response;

import java.util.List;

public record VolumeVariationResponseDTO(
        Long id,
        Double volume,
        Double price,
        Integer internalQuantity,
        List<UnitsPerPackVariationResponseDTO> unitsPerPackVariations
) {
}