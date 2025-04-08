package com.cmoiss.stockcontrolapi.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
public class ProductDTO {
    @Getter
    private String name;
    private CategoryDTO category;

    @Getter
    private List<VolumeVariationDTO> volumeVariation;

    public String getCategoryName() {
        return category.getName();
    }
}