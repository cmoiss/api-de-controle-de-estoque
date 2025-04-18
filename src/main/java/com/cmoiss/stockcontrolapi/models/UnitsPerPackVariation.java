package com.cmoiss.stockcontrolapi.models;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "units_per_pack_variations")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class UnitsPerPackVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "units_per_pack_id", nullable = false)
    private UnitsPerPack unitsPerPack;

    @NonNull
    @Column(name = "quantity_in_external_stock", nullable = false)
    private Integer quantityInExternalStock;

//    @NonNull
//    @Setter
//    @ManyToOne
//    @JoinColumn(name = "volume_variation_id", nullable = false)
//    private VolumeVariation volumeVariation;
}