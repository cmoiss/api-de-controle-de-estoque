package com.cmoiss.stockcontrolapi.repository;

import com.cmoiss.stockcontrolapi.models.UnitsPerPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitsPerPackRepository extends JpaRepository<UnitsPerPack, Long> {

    /**
     * Busca um UnitsPerPack pelo número de unidades
     * @param units Número de unidades por pack
     * @return Optional contendo o UnitsPerPack se encontrado
     */
    Optional<UnitsPerPack> findByUnits(Integer units);
}