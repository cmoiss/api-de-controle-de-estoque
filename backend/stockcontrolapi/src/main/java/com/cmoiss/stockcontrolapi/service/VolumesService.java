package com.cmoiss.stockcontrolapi.service;

import com.cmoiss.stockcontrolapi.models.Product;
import com.cmoiss.stockcontrolapi.models.Volumes;
import com.cmoiss.stockcontrolapi.repository.VolumesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VolumesService {
    @Autowired
    private VolumesRepository volumesRepository;

    public Product findOrCreateNewVolume(Product product) {
        // Em cada volumeVariation, verifica se o volume já existe no banco de dados
        product.getVolumeVariation()
                .forEach(volumeVariation -> {
                            Volumes volume = volumeVariation.getVolumeValue();
                            Optional<Volumes> existingVolume = volumesRepository.findByVolume(volume.getVolume());

                            existingVolume.ifPresentOrElse(
                                    // Se existir, atualiza o volumeVariation com o existente
                                    volumeVariation::setVolumeValue,
                                    // Se não existir, salva o novo e atualiza o volumeVariation
                                    () -> {
                                        volumeVariation.setVolumeValue(volume);
                                        volumesRepository.save(volume);
                                    }
                            );
                        }
                );

        return product;
    }
}
