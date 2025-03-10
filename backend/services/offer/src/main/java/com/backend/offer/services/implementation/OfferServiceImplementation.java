package com.backend.offer.services.implementation;

import com.backend.offer.dto.OfferDTO;
import com.backend.offer.entities.Offer;
import com.backend.offer.entities.Status;
import com.backend.offer.repositories.OfferRepository;
import com.backend.offer.services.OfferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Slf4j
public class OfferServiceImplementation implements OfferService {
    private final OfferRepository offerRepository;
    @Override
    public OfferDTO createOffer(OfferDTO request) {
        log.info("Saving new offer {}", request);

        Offer offer = mapDTOToEntity(request);
        offer = offerRepository.save(offer);

        return mapEntityToDTO(offer);
    }

    @Override
    public void deleteOffer(Long offerId) {
        log.info("Deleting offer by ID: {}", offerId);
        offerRepository.deleteById(offerId);
    }

    @Override
    public OfferDTO updateOffer(Long offerId, String status) {
        if (offerId == null){
            throw new NullPointerException("invalid offer");
        }
        log.info("Updating the status of the offer having ID: {}",offerId);

        Offer offer = offerRepository.getReferenceById(offerId);
        offer.setStatus(Status.valueOf(status));

        return mapEntityToDTO(offerRepository.save(offer));
    }

    @Override
    public List<OfferDTO> getOffersByEstateId(Long estateId) {
        List<Offer> offers;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if(role.equals("ROLE_USER")) {
            String userEmail = getCurrentUser();
            offers = offerRepository.getOffers(estateId, userEmail);
        } else {
            offers = offerRepository.getOffers(estateId);
        }

        List<OfferDTO> offerResponses = new ArrayList<>();
        for (Offer offer : offers) {
            OfferDTO offerResponse = mapEntityToDTO(offer);
            offerResponses.add(offerResponse);
        }

        return offerResponses;
    }

    @Override
    public List<OfferDTO> getOffersByUser(String emailUser) {
        List<Offer> offers = offerRepository.getOffersByUser(emailUser);

        List<OfferDTO> offerResponses = new ArrayList<>();
        for (Offer offer : offers) {
            OfferDTO offerResponse = mapEntityToDTO(offer);
            offerResponses.add(offerResponse);
        }

        return offerResponses;
    }

    private OfferDTO mapEntityToDTO(Offer offer) {
        return OfferDTO.builder()
                .id(offer.getId())
                .idEstate(offer.getIdEstate())
                .price(offer.getPrice())
                .emailUser(offer.getEmailUser())
                .status(String.valueOf(offer.getStatus()))
                .updatedAt(offer.getUpdatedAt())
                .build();
    }

    private Offer mapDTOToEntity(OfferDTO request) {
        return Offer.builder()
                .id(request.getId())
                .idEstate(request.getIdEstate())
                .emailUser(request.getEmailUser())
                .price(request.getPrice())
                .status(Status.valueOf(request.getStatus()))
                .updatedAt(request.getUpdatedAt())
                .build();
    }

    private String getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
