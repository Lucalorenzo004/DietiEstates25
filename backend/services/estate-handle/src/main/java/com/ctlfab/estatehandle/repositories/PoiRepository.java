package com.ctlfab.estatehandle.repositories;

import com.ctlfab.estatehandle.entities.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {
    @Query("SELECT p FROM Poi p WHERE p.lat = :lat AND p.lng = :lng")
    Optional<Poi> findByLatAndLng(Float lat, Float lng);
}
