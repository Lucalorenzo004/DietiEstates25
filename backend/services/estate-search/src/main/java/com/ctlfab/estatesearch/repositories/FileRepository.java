package com.ctlfab.estatesearch.repositories;

import com.ctlfab.estatesearch.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT f FROM File f WHERE f.estate.id = :estateId")
    List<File> findAllFileByEstateId(long estateId);
}
