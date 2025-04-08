package com.cmoiss.stockcontrolapi.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class VolumeVariationDTO {
    private Double volume;  // Valor direto (não objeto Volumes)
    private BigDecimal price; // Valor direto (não objeto Price)
    private Integer quantity;
}