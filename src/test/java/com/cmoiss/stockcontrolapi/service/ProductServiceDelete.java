package com.cmoiss.stockcontrolapi.service;

import com.cmoiss.stockcontrolapi.models.*;
import com.cmoiss.stockcontrolapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceDeleteTest {
    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService service;

    static Stream<Product> productProvider() {
        return ProductProvider.productProvider();
    }

    @Test
    void testaExclusaoDeProdutoExistente() {
        Long productId = 1L;

        when(repository.existsById(productId)).thenReturn(true);

        service.deleteById(productId);

        verify(repository, times(1)).deleteById(productId);
    }

    @Test
    void testaExclusaoDeProdutoInexistente() {
        Long productId = 1L;

        when(repository.existsById(productId)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deleteById(productId);
        });

        assertEquals("Produto com o ID fornecido não existe.", exception.getMessage());
        verify(repository, never()).deleteById(any());
    }
}