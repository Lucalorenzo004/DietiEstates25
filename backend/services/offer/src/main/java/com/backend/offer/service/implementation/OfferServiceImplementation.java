package com.backend.offer.service.implementation;

import com.backend.offer.dto.OfferRequest;
import com.backend.offer.dto.OfferResponse;
import com.backend.offer.model.Offer;
import com.backend.offer.model.Status;
import com.backend.offer.repository.OfferRepository;
import com.backend.offer.service.OfferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Slf4j
public class OfferServiceImplementation implements OfferService {
    private final OfferRepository offerRepository;
    @Override
    public OfferResponse createOffer(OfferRequest request) {
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
    public OfferResponse updateOffer(Long offerId, String status) {
        log.info("Updating the status of the offer having ID: {}",offerId);

        Offer offer = offerRepository.getReferenceById(offerId);
        offer.setStatus(Status.valueOf(status));

        return mapEntityToDTO(offerRepository.save(offer));
    }

    @Override
    public List<OfferResponse> getOffers(Long estateId, Long page, Long pageSize) {
        List<Offer> offers;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long pageable = (page - 1) * pageSize;
        String role = authentication.getAuthorities().iterator().toString();

        if(role.equals("ROLE_USER")) {
            Long userId = getCurrentUserId();
            offers = offerRepository.getOffers(estateId, userId, pageable, pageSize);
        } else {
            offers = offerRepository.getOffers(estateId, pageable, pageSize);
        }

        List<OfferResponse> offerResponses = new ArrayList<>();
        for (Offer offer : offers) {
            OfferResponse offerResponse = mapEntityToDTO(offer);
            offerResponses.add(offerResponse);
        }

        return offerResponses;
    }

    private OfferResponse mapEntityToDTO(Offer offer) {
        return OfferResponse.builder()
                .id(offer.getId())
                .price(offer.getPrice())
                .status(String.valueOf(offer.getStatus()))
                .createdAt(offer.getCreatedAt())
                .updatedAt(offer.getUpdatedAt())
                .build();
    }

    private Offer mapDTOToEntity(OfferRequest request) {
        return Offer.builder()
                .id(request.getId())
                .idEstate(request.getIdEstate())
                .idUser(request.getIdUser())
                .price(request.getPrice())
                .status(Status.valueOf(request.getStatus()))
                .build();
    }

    private Long getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<?, ?> details = (Map<?, ?>) authentication.getDetails();

        if (details.containsKey("userId") && details.get("userId") instanceof Long) {
            return (Long) details.get("userId");
        }

        return null;
    }
}
