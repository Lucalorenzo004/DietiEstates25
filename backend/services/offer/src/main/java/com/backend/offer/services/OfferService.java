package com.backend.offer.services;

import com.backend.offer.dto.OfferDTO;

import java.util.List;

public interface OfferService {

    OfferDTO createOffer(OfferDTO request);

    void deleteOffer(Long offerId);

    OfferDTO updateOffer(Long offerId, String status);

    List<OfferDTO> getOffersByEstateId(Long estateId);

    List<OfferDTO> getOffersByUser(String emailUser);
}
