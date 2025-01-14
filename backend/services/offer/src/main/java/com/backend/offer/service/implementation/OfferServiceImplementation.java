package com.backend.offer.service.implementation;

import com.backend.offer.dto.OfferRequestDTO;
import com.backend.offer.dto.OfferResponseDTO;
import com.backend.offer.model.Offer;
import com.backend.offer.model.Status;
import com.backend.offer.repository.OfferRepository;
import com.backend.offer.service.OfferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Slf4j
public class OfferServiceImplementation implements OfferService {
    private final OfferRepository offerRepository;
    @Override
    public OfferResponseDTO createOffer(OfferRequestDTO request) {
        log.info("Saving new offer {}", request);

        Offer offer = mapDTOToEntity(request);
        offer = offerRepository.save(offer);

        return mapEntityToDTO(offer);
    }

    @Override
    public Boolean deleteOffer(Long offerId) {
        log.info("Deleting offer by ID: {}", offerId);
        offerRepository.deleteById(offerId);

        return TRUE;
    }

    @Override
    public OfferResponseDTO updateOffer(Long offerId, String status) {
        log.info("Updating the status of the offer having ID: {}",offerId);

        Offer offer = offerRepository.getReferenceById(offerId);
        offer.setStatus(Status.valueOf(status));

        return mapEntityToDTO(offerRepository.save(offer));
    }

    @Override
    public List<OfferResponseDTO> getOffers(Long estateId, Long page, Long pageSize) {
        return null;
    }

    private OfferResponseDTO mapEntityToDTO(Offer offer) {
        return OfferResponseDTO.builder()
                .id(offer.getId())
                .price(offer.getPrice())
                .status(String.valueOf(offer.getStatus()))
                .createdAt(offer.getCreatedAt())
                .updatedAt(offer.getUpdatedAt())
                .build();
    }

    private Offer mapDTOToEntity(OfferRequestDTO request) {
        return Offer.builder()
                .id(request.getId())
                .idEstate(request.getIdEstate())
                .idUser(request.getIdUser())
                .price(request.getPrice())
                .status(Status.valueOf(request.getStatus()))
                .build();
    }
}
