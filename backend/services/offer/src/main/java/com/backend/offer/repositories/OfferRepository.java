package com.backend.offer.repository;

import com.backend.offer.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    @Query(value = "SELECT * FROM offer WHERE estate_id = :estateId AND user_id = :userId " +
            "ORDER BY updated_at DESC LIMIT :pageSize OFFSET :pageable",nativeQuery = true)
    List<Offer> getOffers(Long estateId, Long userId, Long pageable, Long pageSize);

    @Query(value = "SELECT * FROM offer WHERE estate_id = :estateId " +
            "ORDER BY updated_at DESC LIMIT :pageSize OFFSET :pageable",nativeQuery = true)
    List<Offer> getOffers(Long estateId,Long pageable,Long pageSize);
}
