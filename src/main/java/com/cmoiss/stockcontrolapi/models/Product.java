package com.cmoiss.stockcontrolapi.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.CascadeType.PERSIST;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "products")
public class Product {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String name;

    @Setter
    @ManyToOne
    @Cascade(PERSIST)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false)
    private List<VolumeVariation> volumeVariation = new ArrayList<>();

    public Product(String name, Category category, List<VolumeVariation> volumeVariation) {
        this.name = name;
        this.category = category;
        this.volumeVariation = volumeVariation;
    }

    public String getCategoryName() {
        return category.getName();
    }
}