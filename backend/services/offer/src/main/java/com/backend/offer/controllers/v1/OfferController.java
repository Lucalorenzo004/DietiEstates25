package com.backend.offer.controllers.v1;

import com.backend.offer.dto.OfferDTO;
import com.backend.offer.serialization.ApiResponse;
import com.backend.offer.serialization.Meta;
import com.backend.offer.services.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static java.time.LocalDateTime.now;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/offer-api/v1/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<ApiResponse<OfferDTO>> createOffer(@RequestBody @Valid OfferDTO offerDTO){
        OfferDTO savedOfferDTO = offerService.createOffer(offerDTO);

        Meta meta = new Meta(now(),"v1");
        String status = "offer saved";
        ApiResponse<OfferDTO> apiResponse = new ApiResponse<>(status, savedOfferDTO,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/{offerId}")
    public ResponseEntity<ApiResponse<OfferDTO>> updateOffer(@PathVariable Long offerId,
                                                          @RequestParam(value = "status") String status){
        OfferDTO offerDTO = offerService.updateOffer(offerId, status);

        Meta meta = new Meta(now(),"v1");
        String statusResponse = "offer updated";
        ApiResponse<OfferDTO> apiResponse = new ApiResponse<>(statusResponse, offerDTO, meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteOffer(@RequestParam(value = "offerId") Long offerId){
        offerService.deleteOffer(offerId);

        Meta meta = new Meta(now(),"v1");
        String status = "offer deleted";
        ApiResponse<Void> apiResponse = new ApiResponse<>(status, meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{estateId}")
    public ResponseEntity<ApiResponse<List<OfferDTO>>> getOffersByEstateId(@PathVariable Long estateId){
        List<OfferDTO> offerDTO = offerService.getOffersByEstateId(estateId);

        Meta meta = new Meta(now(),"v1");
        String status = "offers retrieved";
        ApiResponse<List<OfferDTO>> apiResponse = new ApiResponse<>(status, offerDTO, meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OfferDTO>>> getOffersByUser(@RequestParam(value = "user") String email){
        List<OfferDTO> offerDTO = offerService.getOffersByUser(email);

        Meta meta = new Meta(now(),"v1");
        String status = "History offers retrieved";
        ApiResponse<List<OfferDTO>> apiResponse = new ApiResponse<>(status, offerDTO, meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
