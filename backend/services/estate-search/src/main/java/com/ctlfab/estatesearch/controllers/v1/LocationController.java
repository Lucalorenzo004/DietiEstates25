package com.ctlfab.estatesearch.controllers.v1;

import com.ctlfab.estatesearch.dto.LocationDTO;

import com.ctlfab.estatesearch.serialization.ApiResponse;
import com.ctlfab.estatesearch.serialization.Meta;
import com.ctlfab.estatesearch.services.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-search-api/v1/locations")
public class LocationController {
    private final LocationService service;

    /**
     * Handles HTTP POST requests to fetch location if exists.
     * @param locationDTO {@link LocationDTO} data of location to fetch.
     * @return A {@link ResponseEntity} containing a standardized response with a {@link LocationDTO} data if exists,
     * null otherwise.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<LocationDTO>> getLocation(@RequestBody @Valid LocationDTO locationDTO) {
        Meta meta = new Meta(now(), "v1");
        String status = "Location retrieved";

        LocationDTO retrievedLocation = service.findByFullAddress(
                locationDTO.getStreet(),
                locationDTO.getCity(),
                locationDTO.getPostalCode(),
                locationDTO.getCounty(),
                locationDTO.getCountyCode()
        );

        if (retrievedLocation == null) {
            status = "Not found";
        }
        ApiResponse<LocationDTO> response = new ApiResponse<>(status, retrievedLocation, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
