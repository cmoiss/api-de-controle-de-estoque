package com.cmoiss.stockcontrolapi.service;

import com.cmoiss.stockcontrolapi.models.*;
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
class ProductServiceSaveTest {
    @Mock
    ProductRepository repository;

    @Mock
    CategoryService categoryService;

    @Mock
    VolumesService volumesService;

    @Mock
    UnitsPerPackService unitsPerPackService;

    @InjectMocks
    ProductService service;

    static Stream<Product> productProvider() {
        return ProductProvider.productProvider();
    }

    private void configureCommonSaveStubs(Product product) {
        when(categoryService.findOrCreateNewCategory(product)).thenReturn(product);
        when(volumesService.findOrCreateNewVolume(product)).thenReturn(product);
        when(unitsPerPackService.processUnitsPerPackVariations(product)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);
    }

    @Test
    void testaPersistenciaUnicoValor() {
        Product product = productProvider().findFirst().orElseThrow(() -> new IllegalArgumentException("No product found"));

        configureCommonSaveStubs(product);

        Product savedProduct = service.save(product);

        verify(repository, times(1)).save(product);

        assertNotNull(savedProduct);
        assertEquals(product, savedProduct);
    }

    @Test
    void testaTentativaDePersistenciaComCategoriaDuplicada() {
        Product p1 = productProvider().filter(product -> product.getName().equals("Heineken")).findFirst().orElse(null);
        Product p2 = productProvider().filter(product -> product.getName().equals("Skol")).findFirst().orElse(null);

        configureCommonSaveStubs(p1);
        configureCommonSaveStubs(p2);

        service.save(p1);
        service.save(p2);

        verify(repository, times(1)).save(p1);
        verify(repository, times(1)).save(p2);

        String firstProductCategoryName = p1.getCategoryName();
        String secondProductCategoryName = p2.getCategoryName();

        assertEquals(firstProductCategoryName, secondProductCategoryName);
    }

    @Test
    void testaTentativaDePersistenciaComVolumeDuplicado() {
        Product p1 = productProvider().toList().getFirst();
        Product p2 = productProvider().toList().get(1);

        configureCommonSaveStubs(p1);
        configureCommonSaveStubs(p2);

        service.save(p1);
        service.save(p2);

        verify(repository, times(1)).save(p1);
        verify(repository, times(1)).save(p2);

        Volumes product1VariationWith500 = p1.getVolumeVariation().getLast().getVolumeValue();
        Volumes product2VariationWith500 = p2.getVolumeVariation().getFirst().getVolumeValue();

        assertEquals(product1VariationWith500, product2VariationWith500);
    }

    @Test
    void testaTentaTivaDePersistenciaDeUnicoProdutoComUnitPerPackDuplicado() {
        Product p1 = productProvider().toList().getFirst();

        configureCommonSaveStubs(p1);

        service.save(p1);

        verify(repository, times(1)).save(p1);

        UnitsPerPack u1 = p1.getVolumeVariation().getFirst().getUnitsPerPackVariations().getFirst().getUnitsPerPack();
        UnitsPerPack u2 = p1.getVolumeVariation().getLast().getUnitsPerPackVariations().getFirst().getUnitsPerPack();

        assertEquals(u1, u2);
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void testaSeEstaSalvando(Product product) {
        configureCommonSaveStubs(product);

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
}