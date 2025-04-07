package com.cmoiss.stockcontrolapi.service;

import com.cmoiss.stockcontrolapi.models.Category;
import com.cmoiss.stockcontrolapi.models.Product;
import com.cmoiss.stockcontrolapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService service;

    static Stream<Product> productProvider() {
        return Stream.of(
                new Product("Coca cola", new Category("Refrigerante")),
                new Product("Vinho tinto", new Category("Vinho")),
                new Product("Heineken", new Category("Cerveja"))
        );
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void testaSeEstaSalvando(Product product) {
        when(repository.save(product)).thenReturn(product);

        Product savedProduct = service.save(product);

        verify(repository).save(product);

        assertEquals(product, savedProduct);
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void testaSeExcecaoEstaSendoLancadaAoTentarSalvarProdutoDuplicado(Product product) {
        // Configura o mock para retornar true quando verificar se o nome existe
        when(repository.existsByName(product.getName())).thenReturn(true);

        // Verifica se a exceção é lançada
        assertThrows(IllegalArgumentException.class, () -> {
            service.save(product);
        });

        // Opcional: verifica a mensagem da exceção
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.save(product);
        });
        assertEquals("Já existe um produto com este nome.", exception.getMessage());

        // Verifica que o método save nunca foi chamado
        verify(repository, never()).save(any());
    }

    @Test
    void getAll() {
    }

    @Test
    void deleteById() {
    }
}