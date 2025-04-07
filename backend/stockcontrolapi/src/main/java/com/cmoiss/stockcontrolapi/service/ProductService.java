package com.cmoiss.stockcontrolapi.service;

import com.cmoiss.stockcontrolapi.models.Product;
import com.cmoiss.stockcontrolapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    public Product save(Product product) {
        // Verifica se já existe um produto com o mesmo nome
        if (repository.existsByName(product.getName())) {
            throw new IllegalArgumentException("Já existe um produto com este nome.");
        }

        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
