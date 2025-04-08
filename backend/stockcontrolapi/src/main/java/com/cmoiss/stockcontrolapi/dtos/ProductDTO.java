package com.cmoiss.stockcontrolapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private String name;
    private CategoryDTO category;
    private List<VolumeVariationDTO> volumeVariation;
}