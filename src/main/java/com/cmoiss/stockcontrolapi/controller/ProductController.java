package com.cmoiss.stockcontrolapi.controller;

import com.cmoiss.stockcontrolapi.dtos.mapper.ProductMapper;
import com.cmoiss.stockcontrolapi.dtos.request.ProductRequestDTO;
import com.cmoiss.stockcontrolapi.dtos.response.ProductResponseDTO;
import com.cmoiss.stockcontrolapi.models.Product;
import com.cmoiss.stockcontrolapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping()
    public List<ProductResponseDTO> getAll() {
        List<Product> products = service.getAll();
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();

        products.forEach(product -> {
            productResponseDTOList.add(productMapper.toResponseDTO(product));
        });


        return productResponseDTOList;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        service.save(product);
        return ResponseEntity.status(CREATED).body(productMapper.toResponseDTO(product));
    }
}
