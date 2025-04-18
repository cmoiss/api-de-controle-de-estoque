package com.cmoiss.stockcontrolapi.dtos.request;

import java.util.List;

public record VolumeVariationRequestDTO(
        Double volume,
        Double price,
        Integer internalQuantity,
        List<UnitsPerPackVariationRequestDTO> unitsPerPackVariations
) {
}