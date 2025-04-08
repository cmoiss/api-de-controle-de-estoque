package com.cmoiss.stockcontrolapi.repository;

import com.cmoiss.stockcontrolapi.models.Volumes;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VolumesRepository extends JpaRepository<Volumes, Long> {

    Optional<Volumes> findByVolume(@NonNull Double volume);
}
