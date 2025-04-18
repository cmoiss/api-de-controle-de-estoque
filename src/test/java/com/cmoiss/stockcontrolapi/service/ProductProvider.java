package com.cmoiss.stockcontrolapi.service;

import com.cmoiss.stockcontrolapi.models.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class ProductProvider {
    static Stream<Product> productProvider() {
        // Unidades por pack comuns (poderia ser um repositório em produção)
        UnitsPerPack pack6 = new UnitsPerPack(6);
        UnitsPerPack pack12 = new UnitsPerPack(12);
        UnitsPerPack pack24 = new UnitsPerPack(24);
        UnitsPerPack pack1 = new UnitsPerPack(1);  // Para venda unitária
        UnitsPerPack pack4 = new UnitsPerPack(4);
        UnitsPerPack pack8 = new UnitsPerPack(8);

        return Stream.of(
                // Coca-Cola (normalmente vendida em packs maiores)
                new Product("Coca cola", new Category("Refrigerante"), List.of(
                        new VolumeVariation(new Volumes(350.0), new Price(new BigDecimal("6.99")), 5, List.of(
                                new UnitsPerPackVariation(pack12, 10),  // 10 packs de 12 unidades
                                new UnitsPerPackVariation(pack24, 5)    // 5 packs de 24 unidades
                        )),
                        new VolumeVariation(new Volumes(500.0), new Price(new BigDecimal("8.99")), 10, List.of(
                                new UnitsPerPackVariation(pack12, 15),  // 15 packs de 12 unidades
                                new UnitsPerPackVariation(pack24, 8)    // 8 packs de 24 unidades
                        ))
                )),

                // Heineken (cerveja premium, packs menores)
                new Product("Heineken", new Category("Cerveja"), List.of(
                        new VolumeVariation(new Volumes(500.0), new Price(new BigDecimal("11.99")), 5, List.of(
                                new UnitsPerPackVariation(pack6, 20),   // 20 packs de 6 unidades
                                new UnitsPerPackVariation(pack12, 10)   // 10 packs de 12 unidades
                        )),
                        new VolumeVariation(new Volumes(1000.0), new Price(new BigDecimal("15.99")), 10, List.of(
                                new UnitsPerPackVariation(pack6, 15),   // 15 packs de 6 unidades
                                new UnitsPerPackVariation(pack12, 8)    // 8 packs de 12 unidades
                        ))
                )),

                // Skol (cerveja popular, packs maiores)
                new Product("Skol", new Category("Cerveja"), List.of(
                        new VolumeVariation(new Volumes(500.0), new Price(new BigDecimal("11.99")), 5, List.of(
                                new UnitsPerPackVariation(pack12, 30),  // 30 packs de 12 unidades
                                new UnitsPerPackVariation(pack24, 15)   // 15 packs de 24 unidades
                        )),
                        new VolumeVariation(new Volumes(1000.0), new Price(new BigDecimal("15.99")), 10, List.of(
                                new UnitsPerPackVariation(pack12, 20),  // 20 packs de 12 unidades
                                new UnitsPerPackVariation(pack24, 10)   // 10 packs de 24 unidades
                        ))
                )),

                // Vinho (normalmente vendido unitário ou em packs pequenos)
                new Product("Vinho tinto", new Category("Vinho"), List.of(
                        new VolumeVariation(new Volumes(750.0), new Price(new BigDecimal("29.99")), 12, List.of(
                                new UnitsPerPackVariation(pack1, 50),   // 50 unidades avulsas
                                new UnitsPerPackVariation(pack4, 12)    // 12 packs de 4 unidades
                        )),
                        new VolumeVariation(new Volumes(1000.0), new Price(new BigDecimal("39.99")), 8, List.of(
                                new UnitsPerPackVariation(pack1, 30),    // 30 unidades avulsas
                                new UnitsPerPackVariation(pack8, 5)      // 5 packs de 8 unidades
                        ))
                ))
        );
    }
}
