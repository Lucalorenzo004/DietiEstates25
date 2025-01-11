package com.backend.offer.service;

import com.backend.offer.dto.OfferRequestDTO;
import com.backend.offer.dto.OfferResponseDTO;

public interface OfferService {

    OfferResponseDTO createOffer(OfferRequestDTO request);

    Boolean deleteOffer(Long offerId);

    OfferResponseDTO updateOffer(Long offerId, String status);
}
