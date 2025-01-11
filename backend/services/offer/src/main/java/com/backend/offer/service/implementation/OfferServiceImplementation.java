package com.backend.offer.service.implementation;

import com.backend.offer.dto.OfferRequestDTO;
import com.backend.offer.dto.OfferResponseDTO;
import com.backend.offer.service.OfferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class OfferServiceImplementation implements OfferService {
    @Override
    public OfferResponseDTO createOffer(OfferRequestDTO offerRequestDTO) {
        return null;
    }

    @Override
    public Boolean deleteOffer(Long offerId) {
        return null;
    }

    @Override
    public OfferResponseDTO updateOffer(Long offerId, String status) {
        return null;
    }
}
