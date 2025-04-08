package com.cmoiss.stockcontrolapi.service;

import com.cmoiss.stockcontrolapi.models.Category;
import com.cmoiss.stockcontrolapi.models.Product;
import com.cmoiss.stockcontrolapi.models.Volumes;
import com.cmoiss.stockcontrolapi.models.VolumeVariation;
import com.cmoiss.stockcontrolapi.repository.ProductRepository;
import com.cmoiss.stockcontrolapi.repository.VolumesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository repository;

    @Mock
    VolumesService volumesService;

    @InjectMocks
    ProductService service;

    static Stream<Product> productProvider() {
        return Stream.of(
                new Product("Coca cola", new Category("Refrigerante"), List.of(
                        new VolumeVariation(),
                        new VolumeVariation()
                )),
                new Product("Vinho tinto", new Category("Vinho"), List.of(
                        new VolumeVariation(),
                        new VolumeVariation()
                )),
                new Product("Heineken", new Category("Cerveja"), List.of(
                        new VolumeVariation(),
                        new VolumeVariation()
                ))
        );
    }

    @Test
    void testaPersistenciaUnica() {
        Product product = new Product("Coca cola", new Category("Refrigerante"), List.of(
                new VolumeVariation(),
                new VolumeVariation()
        ));

        when(volumesService.findOrCreateNewVolume(product)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);

        Product savedProduct = service.save(product);

        verify(repository, times(1)).save(product);

        assertNotNull(savedProduct);
        assertEquals(product, savedProduct);
    }

    @Test
    void testaTentativaDePersistenciaComVolumeDuplicado() {
        Volumes v = new Volumes(500.0);

        Product p1 = new Product("Coca cola", new Category("Refrigerante"), List.of(
                new VolumeVariation(new Volumes(350.0)),
                new VolumeVariation(v)
        ));

        Product p2 = new Product("Heineken", new Category("Cerveja"), List.of(
                new VolumeVariation(v),
                new VolumeVariation(new Volumes(1000.0))
        ));

        when(volumesService.findOrCreateNewVolume(p1)).thenReturn(p1);
        when(volumesService.findOrCreateNewVolume(p2)).thenReturn(p2);
        when(repository.save(p1)).thenReturn(p1);
        when(repository.save(p2)).thenReturn(p2);

        service.save(p1);
        service.save(p2);

        verify(repository, times(1)).save(p1);
        verify(repository, times(1)).save(p2);

        VolumeVariation product1VariationWith500 = p1.getVolumeVariation().getLast();
        VolumeVariation product2VariationWith500 = p2.getVolumeVariation().getFirst();
        
        assertEquals(product1VariationWith500, product2VariationWith500);
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void testaSeEstaSalvando(Product product) {
        when(repository.save(product)).thenReturn(product);

        when(volumesService.findOrCreateNewVolume(product)).thenReturn(product);
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