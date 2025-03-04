package com.backend.offer.controllers.v1;

import com.backend.offer.dto.OfferRequest;
import com.backend.offer.dto.OfferResponse;
import com.backend.offer.serialization.ApiResponse;
import com.backend.offer.serialization.Meta;
import com.backend.offer.services.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/offer-api/v1/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<ApiResponse<OfferResponse>> createOffer(@RequestBody @Valid OfferRequest offerRequest){
        OfferResponse offerResponse = offerService.createOffer(offerRequest);

        Meta meta = new Meta(now(),"v1");
        String status = "offer saved";
        ApiResponse<OfferResponse> apiResponse = new ApiResponse<>(status,offerResponse,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/{offerId}")
    public ResponseEntity<ApiResponse<OfferResponse>> updateOffer(@PathVariable Long offerId,
                                                          @RequestParam(value = "status") String status){
        OfferResponse offerResponse = offerService.updateOffer(offerId,status);

        Meta meta = new Meta(now(),"v1");
        String statusResponse = "offer updated";
        ApiResponse<OfferResponse> apiResponse = new ApiResponse<>(statusResponse,offerResponse,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteOffer(@RequestParam(value = "offerId") Long offerId){
        offerService.deleteOffer(offerId);

        Meta meta = new Meta(now(),"v1");
        String status = "offer deleted";
        ApiResponse<Void> apiResponse = new ApiResponse<>(status,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{estateId}")
    public ResponseEntity<ApiResponse<List<OfferResponse>>> getOffers(@PathVariable Long estateId, @RequestParam(value = "page") Long page,
                                                       @RequestParam(value = "pageSize") Long pageSize){
        List<OfferResponse> offerResponse = offerService.getOffers(estateId,page,pageSize);

        Meta meta = new Meta(now(),"v1");
        String status = "offers retrieved";
        ApiResponse<List<OfferResponse>> apiResponse = new ApiResponse<>(status,offerResponse,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
