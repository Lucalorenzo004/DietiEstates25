package com.ctlfab.estatehandle.repositories;

import com.ctlfab.estatehandle.entities.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoiRepository extends JpaRepository<Poi, Long> {
}
