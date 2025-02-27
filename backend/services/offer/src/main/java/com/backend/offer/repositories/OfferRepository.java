package com.backend.offer.repositories;

import com.backend.offer.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    @Query(value = "SELECT * FROM offer WHERE estate_id = :estateId AND user_email = :userEmail " +
            "ORDER BY updated_at DESC LIMIT :pageSize OFFSET :pageable",nativeQuery = true)
    List<Offer> getOffers(Long estateId, String userEmail, Long pageable, Long pageSize);

    @Query(value = "SELECT * FROM offer WHERE estate_id = :estateId " +
            "ORDER BY updated_at DESC LIMIT :pageSize OFFSET :pageable",nativeQuery = true)
    List<Offer> getOffers(Long estateId,Long pageable,Long pageSize);
}
