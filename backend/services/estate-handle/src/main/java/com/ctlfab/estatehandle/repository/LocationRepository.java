package com.ctlfab.estatehandle.repository;

import com.ctlfab.estatehandle.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE id = :id")
    Optional<Location> findById(String id);
}
