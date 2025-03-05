package com.backend.offer.repositories;

import com.backend.offer.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    @Query("SELECT o FROM Offer o WHERE o.idEstate = :estateId AND o.emailUser = :userEmail ORDER BY o.updatedAt DESC")
    List<Offer> getOffers(Long estateId, String userEmail);

    @Query("SELECT o FROM Offer o WHERE o.idEstate = :estateId ORDER BY o.updatedAt DESC ")
    List<Offer> getOffers(Long estateId);

    @Query("SELECT o FROM Offer o WHERE o.emailUser = :emailUser ORDER BY o.updatedAt DESC ")
    List<Offer> getOffersByUser(String emailUser);
}
