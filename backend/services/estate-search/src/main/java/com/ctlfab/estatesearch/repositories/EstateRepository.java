package com.ctlfab.estatesearch.repositories;

import com.ctlfab.estatesearch.entities.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstateRepository extends JpaRepository<Estate, Long>, JpaSpecificationExecutor<Estate> {

    @Query("SELECT e FROM Estate e JOIN FavoriteEstate fe ON e.id = fe.estateId WHERE fe.userId = :userId")
    List<Estate> getFavoriteEstate(Long userId);
}
