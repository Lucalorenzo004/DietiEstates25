package com.ctlfab.estatehandle.repository;

import com.ctlfab.estatehandle.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
