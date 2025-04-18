package com.cmoiss.stockcontrolapi.dtos.mapper;

import com.cmoiss.stockcontrolapi.dtos.request.*;
import com.cmoiss.stockcontrolapi.dtos.response.*;
import com.cmoiss.stockcontrolapi.models.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private UnitsPerPackVariation toUnitsPerPackVariation(UnitsPerPackVariationRequestDTO dto) {
        return new UnitsPerPackVariation(
                new UnitsPerPack(dto.unitsPerPack()),
                dto.externalQuantity()
        );
    }

    private VolumeVariation toVolumeVariation(VolumeVariationRequestDTO dto) {
        List<UnitsPerPackVariation> packVariations = dto.unitsPerPackVariations().stream()
                .map(this::toUnitsPerPackVariation)
                .collect(Collectors.toList());

        return new VolumeVariation(
                new Volumes(dto.volume()),
                new Price(dto.price()),
                dto.internalQuantity(),
                packVariations
        );
    }

    public Product toEntity(ProductRequestDTO dto) {
        List<VolumeVariation> variations = dto.volumeVariations().stream()
                .map(this::toVolumeVariation)
                .collect(Collectors.toList());

        return new Product(
                dto.name(),
                new Category(dto.getCategoryName()),
                variations
        );
    }

    private UnitsPerPackVariationResponseDTO toUnitsPerPackVariationResponseDTO(UnitsPerPackVariation variation) {
        return new UnitsPerPackVariationResponseDTO(
                variation.getId(),
                variation.getUnitsPerPack().getUnits(),
                variation.getQuantityInExternalStock()
        );
    }

    private VolumeVariationResponseDTO toVolumeVariationResponseDTO(VolumeVariation volumeVariation) {
        List<UnitsPerPackVariationResponseDTO> packVariations = volumeVariation.getUnitsPerPackVariations().stream()
                .map(this::toUnitsPerPackVariationResponseDTO)
                .collect(Collectors.toList());

        return new VolumeVariationResponseDTO(
                volumeVariation.getId(),
                volumeVariation.getVolume(),
                volumeVariation.getPrice(),
                volumeVariation.getQuantityInInventory(),
                packVariations
        );
    }

    public ProductResponseDTO toResponseDTO(Product product) {
        List<VolumeVariationResponseDTO> variations = product.getVolumeVariation().stream()
                .map(this::toVolumeVariationResponseDTO)
                .collect(Collectors.toList());

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getCategoryName(),
                variations
        );
    }
}