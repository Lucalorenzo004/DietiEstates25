package com.backend.offer.controller;

import com.backend.offer.dto.OfferRequest;
import com.backend.offer.serialization.Response;
import com.backend.offer.service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/offer-api/v1/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<Response> createOffer(@RequestBody @Valid OfferRequest offerRequest){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("offer",offerService.createOffer(offerRequest)))
                        .message("offer saved")
                        .httpStatus(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @PatchMapping("/{offerId}")
    public ResponseEntity<Response> updateOffer(@PathVariable Long offerId,
                                                @RequestParam(value = "status") String status){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("offer",offerService.updateOffer(offerId,status)))
                        .message("offer updated")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping
    public ResponseEntity<Response> deleteOffer(@RequestParam(value = "offerId") Long offerId){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("offer",offerService.deleteOffer(offerId)))
                        .message("offer deleted")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/{estateId}")
    public ResponseEntity<Response> getOffers(@PathVariable Long estateId, @RequestParam(value = "page") Long page,
                                              @RequestParam(value = "pageSize") Long pageSize){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("offer",offerService.getOffers(estateId, page, pageSize)))
                        .message("offers retrieved")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


}
