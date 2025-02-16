package com.ctlfab.estatesearch.controllers.v1;

import com.ctlfab.estatesearch.dto.PoiDTO;
import com.ctlfab.estatesearch.serialization.ApiResponse;
import com.ctlfab.estatesearch.serialization.Meta;
import com.ctlfab.estatesearch.services.PoiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-search-api/v1/poi")
public class PoiController {
    private final PoiService service;

    /**
     * Handles HTTP POST requests to fetch poi if exists.
     * @param poiDTO {@link PoiDTO} data of poi to fetch.
     * @return A {@link ResponseEntity} containing a standardized response with a {@link PoiDTO} data if exists,
     * null otherwise.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<PoiDTO>> getPoiByLatAndLng(@RequestBody @Valid PoiDTO poiDTO) {
        Meta meta = new Meta(now(), "v1");
        String status = "Poi retrieved";

        PoiDTO retrievedPoi = service.findByLatAndLng(poiDTO.getLat(), poiDTO.getLng());

        if (retrievedPoi == null) {
            status = "Not found";
        }
        ApiResponse<PoiDTO> response = new ApiResponse<>(status, retrievedPoi, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
