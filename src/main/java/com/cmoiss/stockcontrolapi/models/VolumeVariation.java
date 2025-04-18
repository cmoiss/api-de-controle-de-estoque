package com.cmoiss.stockcontrolapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "volume_variations")
public class VolumeVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "volume_value", nullable = false)
    private Volumes volumeValue;

    @Embedded
    @Column(nullable = false)
    private Price price;

    @Column(name = "quantity_in_internal_stock", nullable = false)
    private Integer quantityInInventory;

    @Setter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "volume_variation_id")
    private List<UnitsPerPackVariation> unitsPerPackVariations = new ArrayList<>();

    public VolumeVariation(Volumes volumeValue, Price price, Integer quantityInInventory) {
        this.volumeValue = volumeValue;
        this.price = price;
        this.quantityInInventory = quantityInInventory;
    }

    public VolumeVariation(Volumes volumeValue, Price price, Integer quantityInInventory, List<UnitsPerPackVariation> unitsPerPackVariations) {
        this(volumeValue, price, quantityInInventory);
        this.unitsPerPackVariations = unitsPerPackVariations;
    }

    public Double getVolume() {
        return volumeValue.getVolume();
    }

    public Double getPrice() {
        return price.getPrice().doubleValue();
    }
}