package com.cmoiss.stockcontrolapi.dtos.mapper;

import com.cmoiss.stockcontrolapi.dtos.request.ProductRequestDTO;
import com.cmoiss.stockcontrolapi.dtos.request.VolumeVariationRequestDTO;
import com.cmoiss.stockcontrolapi.dtos.response.ProductResponseDTO;
import com.cmoiss.stockcontrolapi.dtos.response.VolumeVariationResponseDTO;
import com.cmoiss.stockcontrolapi.models.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private VolumeVariation toVolumeVariation(VolumeVariationRequestDTO dto) {
        return new VolumeVariation(
                new Volumes(dto.volume()),
                new Price(dto.price()),
                dto.internalQuantity()
        );
    }

    public Product toEntity(ProductRequestDTO dto) {
        List<VolumeVariation> variations = dto.volumeVariations().stream()
                .map(this::toVolumeVariation)
                .toList();

        return new Product(
                dto.name(),
                new Category(dto.getCategoryName()),
                variations
        );
    }

    public ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getCategoryName(),
                product.getVolumeVariation().stream()
                        .map(volumeVariation -> new VolumeVariationResponseDTO(
                                volumeVariation.getId(),
                                volumeVariation.getVolume(),
                                volumeVariation.getPrice(),
                                volumeVariation.getQuantityInInventory()
                        )).collect(Collectors.toList())
        );
    }
}