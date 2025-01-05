package com.ctlfab.estatehandle.repository;

import com.ctlfab.estatehandle.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstateRepository extends JpaRepository<Estate, Long> {
}
