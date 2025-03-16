package com.backend.offer.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.backend.offer.dto.OfferDTO;
import com.backend.offer.entities.Offer;
import com.backend.offer.entities.Status;
import com.backend.offer.repositories.OfferRepository;
import com.backend.offer.services.implementation.OfferServiceImplementation;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferServiceImplementation offerService;

    private Offer offer;

    @BeforeEach
    void setUp() {
        offer = new Offer();
        offer.setId(1L);
        offer.setStatus(Status.DELIVERED);
        offer.setPrice(100F);
    }

    @Test //OCE1-SCE2
    void testUpdateOfferFound() {
        Long offerId = 1L;
        String newStatus = "DECLINED";

        when(offerRepository.getReferenceById(offerId)).thenReturn(offer);
        when(offerRepository.save(any(Offer.class))).thenReturn(offer);

        OfferDTO response = offerService.updateOffer(offerId, newStatus);

        assertNotNull(response);
        assertEquals(Status.DECLINED, offer.getStatus());
    }

    @Test //OCE2-SCE1
    void testUpdateOfferNotFound() {
        Long offerId = -1L;
        String newStatus = "DELIVERED";

        when(offerRepository.getReferenceById(offerId)).thenThrow(new EntityNotFoundException("Offer not found"));

        assertThrows(EntityNotFoundException.class, () -> offerService.updateOffer(offerId, newStatus));
    }

    @Test //OCE1-SCE4
    void testUpdateOfferWithCounterOffer() {
        Long offerId = 1L;
        String newStatus = "COUNTEROFFER";

        when(offerRepository.getReferenceById(offerId)).thenReturn(offer);
        when(offerRepository.save(any(Offer.class))).thenReturn(offer);

        OfferDTO response = offerService.updateOffer(offerId, newStatus);

        assertNotNull(response);
        assertEquals(Status.COUNTEROFFER, offer.getStatus());
    }

    @Test //OCE1-SCE3
    void testUpdateOfferWithAccepted() {
        Long offerId = 1L;
        String newStatus = "ACCEPTED";

        when(offerRepository.getReferenceById(offerId)).thenReturn(offer);
        when(offerRepository.save(any(Offer.class))).thenReturn(offer);

        OfferDTO response = offerService.updateOffer(offerId, newStatus);

        assertNotNull(response);
        assertEquals(Status.ACCEPTED, offer.getStatus());
    }

    @Test //OCE1-SCE5
    void testUpdateOfferWithNullStatus() {
        Long offerId = 1L;
        String newStatus = null;

        when(offerRepository.getReferenceById(offerId)).thenReturn(offer);

        assertThrows(NullPointerException.class, () -> offerService.updateOffer(offerId, newStatus));
    }

    @Test //OCE3-SCE6
    void testUpdateOfferWithNullOfferIDAndEmptyStatus() {
        String newStatus = "";

        assertThrows(NullPointerException.class, () -> offerService.updateOffer(null, newStatus));
    }

    @Test //OCE1-SCE7
    void testUpdateOfferWithRandomStatus() {
        Long offerId = 1L;
        String newStatus = "DELIVERATO";

        when(offerRepository.getReferenceById(offerId)).thenReturn(offer);

        assertThrows(IllegalArgumentException.class, () -> offerService.updateOffer(offerId, newStatus));
    }
}
