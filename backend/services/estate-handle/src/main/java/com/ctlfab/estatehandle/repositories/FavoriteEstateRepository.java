package com.ctlfab.estatehandle.repositories;

import com.ctlfab.estatehandle.entities.FavoriteEstate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteEstateRepository extends JpaRepository<FavoriteEstate, Long>{
    /**
     * Remove favorite relationship between user and estate.
     * @param estateId The ID of the estate.
     * @param userId The ID of the user.
     * @return {@code Integer} 1 if deletion was successfully, 0 otherwise.
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM FavoriteEstate fe WHERE fe.estate.id = :estateId AND fe.userId = :userId")
    int removeToFavorite(Long estateId, Long userId);
}
