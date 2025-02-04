package com.ctlfab.estatesearch.repositories;

import com.ctlfab.estatesearch.entities.Addon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddonRepository extends JpaRepository<Addon, Long> {
}
