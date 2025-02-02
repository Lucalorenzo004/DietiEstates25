package com.ctlfab.estatehandle.repositories;

import com.ctlfab.estatehandle.entities.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateRepository extends JpaRepository<Estate, Long> {
}
