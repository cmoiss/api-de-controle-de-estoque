package com.cmoiss.stockcontrolapi.service;

import com.cmoiss.stockcontrolapi.models.Product;
import com.cmoiss.stockcontrolapi.models.Volumes;
import com.cmoiss.stockcontrolapi.repository.ProductRepository;
import com.cmoiss.stockcontrolapi.repository.VolumesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VolumesService volumesService;

    @Autowired
    private UnitsPerPackService unitsPerPackService;

    public Product save(Product product) {
        // Verifica se já existe um produto com o mesmo nome
        if (repository.existsByName(product.getName())) {
            throw new IllegalArgumentException("Já existe um produto com este nome.");
        }

        product = categoryService.findOrCreateNewCategory(product);
        product = volumesService.findOrCreateNewVolume(product);
        product = unitsPerPackService.processUnitsPerPackVariations(product);

        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Produto com o ID fornecido não existe.");
        }
        repository.deleteById(id);
    }
}
