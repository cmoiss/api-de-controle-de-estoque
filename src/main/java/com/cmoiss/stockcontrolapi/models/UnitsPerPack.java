package com.cmoiss.stockcontrolapi.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "units_per_pack")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class UnitsPerPack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private Integer units;
}