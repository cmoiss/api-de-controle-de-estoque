package com.cmoiss.stockcontrolapi.dtos.response;

import com.cmoiss.stockcontrolapi.models.VolumeVariation;

import java.util.List;

public record ProductResponseDTO(Long id, String name, String category, List<VolumeVariationResponseDTO> volumeVariations) {
}
