package com.ctlfab.estatehandle.repository;

import com.ctlfab.estatehandle.model.Addons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddonsRepository extends JpaRepository<Addons, Long> {
}
