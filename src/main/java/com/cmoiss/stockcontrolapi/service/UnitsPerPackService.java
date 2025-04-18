package com.cmoiss.stockcontrolapi.service;

import com.cmoiss.stockcontrolapi.models.Product;
import com.cmoiss.stockcontrolapi.models.UnitsPerPack;
import com.cmoiss.stockcontrolapi.models.UnitsPerPackVariation;
import com.cmoiss.stockcontrolapi.repository.UnitsPerPackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnitsPerPackService {

    @Autowired
    private final UnitsPerPackRepository repository;

    private UnitsPerPack findOrCreate(Integer units) {
        return repository.findByUnits(units)
                .orElseGet(() -> repository.save(new UnitsPerPack(units)));
    }

    public Product processUnitsPerPackVariations(Product product) {
        product.getVolumeVariation().forEach(volumeVariation -> {
            List<UnitsPerPackVariation> processedVariations = volumeVariation.getUnitsPerPackVariations()
                    .stream()
                    .map(variation -> {
                        UnitsPerPack unitsPerPack = findOrCreate(variation.getUnitsPerPack().getUnits());
                        return new UnitsPerPackVariation(unitsPerPack, variation.getQuantityInExternalStock());
                    })
                    .collect(Collectors.toList());

            volumeVariation.setUnitsPerPackVariations(processedVariations);
        });

        return product;
    }
}
