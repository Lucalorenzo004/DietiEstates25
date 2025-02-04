package com.ctlfab.estatesearch.repositories;

import com.ctlfab.estatesearch.entities.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {
}
