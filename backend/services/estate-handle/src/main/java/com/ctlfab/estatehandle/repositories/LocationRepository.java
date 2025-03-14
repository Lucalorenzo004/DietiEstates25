package com.ctlfab.estatehandle.repositories;

import com.ctlfab.estatehandle.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
