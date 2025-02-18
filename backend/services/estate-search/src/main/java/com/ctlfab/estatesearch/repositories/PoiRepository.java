package com.ctlfab.estatesearch.repositories;

import com.ctlfab.estatesearch.entities.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {

    /**
     * Check if the poi already exists.
     * @param lat Lat of poi.
     * @param lng Lng of poi.
     * @return  {@link Poi} if location's data exists, null otherwise.
     */
    @Query("SELECT p FROM Poi p WHERE ABS(p.lat - :lat) < 0.0001 AND ABS(p.lng - :lng) < 0.0001")
    Optional<Poi> findByLatAndLng(Float lat, Float lng);
}
