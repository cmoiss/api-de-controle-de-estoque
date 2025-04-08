package com.cmoiss.stockcontrolapi.dtos.mapper;

import com.cmoiss.stockcontrolapi.dtos.request.ProductDTO;
import com.cmoiss.stockcontrolapi.dtos.request.VolumeVariationDTO;
import com.cmoiss.stockcontrolapi.models.*;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

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

    private VolumeVariation toVolumeVariation(VolumeVariationDTO dto) {
        return new VolumeVariation(
                new Volumes(dto.getVolume()),
                new Price(dto.getPrice()),
                dto.getQuantity()
        );
    }
}