package com.ctlfab.estatesearch.repositories;

import com.ctlfab.estatesearch.entities.Estate;
import com.ctlfab.estatesearch.entities.FavoriteEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteEstateRepository extends JpaRepository<FavoriteEstate, Long>{
}
