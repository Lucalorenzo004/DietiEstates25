package com.ctlfab.estatesearch.repositories;

import com.ctlfab.estatesearch.entities.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateRepository extends JpaRepository<Estate, Long>, JpaSpecificationExecutor<Estate> {
}
