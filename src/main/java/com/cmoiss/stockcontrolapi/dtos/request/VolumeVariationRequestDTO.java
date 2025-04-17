package com.cmoiss.stockcontrolapi.dtos.request;

public record VolumeVariationRequestDTO(Double volume, Double price, Integer internalQuantity) {
}