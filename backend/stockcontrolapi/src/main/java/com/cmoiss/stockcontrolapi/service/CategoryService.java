package com.cmoiss.stockcontrolapi.service;

import com.cmoiss.stockcontrolapi.models.Category;
import com.cmoiss.stockcontrolapi.models.Product;
import com.cmoiss.stockcontrolapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Product findOrCreateNewCategory(Product product) {
        repository.findByName(product.getCategoryName())
                .ifPresentOrElse(
                        product::setCategory,
                        () -> {
                            // Se a categoria não existir, cria uma nova
                            Category newCategory = new Category(product.getCategoryName());

                            product.setCategory(newCategory);
                            repository.save(newCategory);
                        }
                );

        return product;
    }
}
