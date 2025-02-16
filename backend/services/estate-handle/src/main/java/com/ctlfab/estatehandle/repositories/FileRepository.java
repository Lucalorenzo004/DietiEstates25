package com.ctlfab.estatehandle.repositories;

import com.ctlfab.estatehandle.entities.File;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    /**
     * Deletes files that have the name passed as an argument.
     * @param file File's name.
     * @return {@link Integer} 1 if delete was successfully, 0 otherwise.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM File f WHERE f.name = :file")
    int  deleteByName(String file);
}
