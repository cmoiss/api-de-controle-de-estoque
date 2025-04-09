package com.cmoiss.stockcontrolapi.dtos.mapper;

import com.cmoiss.stockcontrolapi.dtos.request.ProductDTO;
import com.cmoiss.stockcontrolapi.dtos.request.VolumeVariationDTO;
import com.cmoiss.stockcontrolapi.dtos.response.ProductResponseDTO;
import com.cmoiss.stockcontrolapi.dtos.response.VolumeVariationResponseDTO;
import com.cmoiss.stockcontrolapi.models.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private VolumeVariation toVolumeVariation(VolumeVariationDTO dto) {
        return new VolumeVariation(
                new Volumes(dto.getVolume()),
                new Price(dto.getPrice()),
                dto.getQuantity()
        );
    }

    public Product toEntity(ProductDTO dto) {
        List<VolumeVariation> variations = dto.getVolumeVariation().stream()
                .map(this::toVolumeVariation)
                .collect(Collectors.toList());

        return new Product(
                dto.getName(),
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