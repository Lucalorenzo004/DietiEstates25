package com.backend.offer.service;

import com.backend.offer.dto.OfferRequest;
import com.backend.offer.dto.OfferResponse;

import java.util.List;

public interface OfferService {

    OfferResponse createOffer(OfferRequest request);

    Boolean deleteOffer(Long offerId);

    OfferResponse updateOffer(Long offerId, String status);

    List<OfferResponse> getOffers(Long estateId, Long page, Long pageSize);

}
