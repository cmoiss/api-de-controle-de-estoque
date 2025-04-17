package com.cmoiss.stockcontrolapi.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "volume_variations")
public class VolumeVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NonNull
    @Setter
    @ManyToOne
    @JoinColumn(name = "volume_value", nullable = false)
    private Volumes volumeValue;

    @NonNull
    @Embedded
    @Column(nullable = false)
    private Price price;

    @NonNull
    @Column(name = "quantity_in_internal_stock" , nullable = false)
    private Integer quantityInInventory;

    public Double getVolume() {
        return volumeValue.getVolume();
    }

    public Double getPrice() {
        return price.getPrice().doubleValue();
    }
}
