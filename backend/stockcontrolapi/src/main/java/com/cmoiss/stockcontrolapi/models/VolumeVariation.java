package com.cmoiss.stockcontrolapi.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "volume_variations")
public class VolumeVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

//    @NonNull
//    @Column(name = "value", nullable = false)
//    private Volumes volume;
//
//    @NonNull
//    @Column(nullable = false)
//    private BigDecimal price;
}
