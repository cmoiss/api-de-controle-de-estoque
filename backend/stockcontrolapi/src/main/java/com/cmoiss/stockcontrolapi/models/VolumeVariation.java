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

//
//    @NonNull
//    @Column(nullable = false)
//    private BigDecimal price;
}
