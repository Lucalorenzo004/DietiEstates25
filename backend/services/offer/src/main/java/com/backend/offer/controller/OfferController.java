package com.backend.offer.controller;

import com.backend.offer.dto.OfferRequestDTO;
import com.backend.offer.model.Response;
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
    public ResponseEntity<Response> createOffer(@RequestBody @Valid OfferRequestDTO offerRequestDTO){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("offer",offerService.createOffer(offerRequestDTO)))
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
                        .data(Map.of("user",offerService.updateOffer(offerId,status)))
                        .message("user updated")
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

}
