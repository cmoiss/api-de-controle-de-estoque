package com.cmoiss.stockcontrolapi.controller;

import com.cmoiss.stockcontrolapi.dtos.mapper.ProductMapper;
import com.cmoiss.stockcontrolapi.dtos.request.ProductDTO;
import com.cmoiss.stockcontrolapi.models.Product;
import com.cmoiss.stockcontrolapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping()
    public List<Product> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        service.save(product);
        return ResponseEntity.status(CREATED).body(product);
    }
}
