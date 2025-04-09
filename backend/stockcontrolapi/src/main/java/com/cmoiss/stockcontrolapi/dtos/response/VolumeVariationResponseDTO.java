package com.cmoiss.stockcontrolapi.dtos.response;

public record VolumeVariationResponseDTO(Long id, Double volume, Double price, Integer quantityInInventory) {
}
