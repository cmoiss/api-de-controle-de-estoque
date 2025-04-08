package com.cmoiss.stockcontrolapi.controller;

import com.cmoiss.stockcontrolapi.models.Product;
import com.cmoiss.stockcontrolapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping()
    public List<Product> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return service.save(product);
    }
}
