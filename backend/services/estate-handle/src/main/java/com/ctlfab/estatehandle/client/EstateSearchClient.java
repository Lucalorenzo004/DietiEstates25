package com.ctlfab.estatehandle.client;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.dto.PoiDTO;
import com.ctlfab.estatehandle.serialization.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "estate-search-service", url = "http://localhost:8082/estate-search-api")
public interface EstateSearchClient {

    /**
     * Call the estate-search-service microservice and check if the location exists
     * @param location A {@link LocationDTO} object containing full address data
     * @return A {@link ResponseEntity} containing a standardized response with the LocationDTo {@link LocationDTO} data if location exists,
     * null otherwise .
     */
    @PostMapping("/v1/locations")
    ApiResponse<LocationDTO> findLocation(@RequestBody LocationDTO location);

    /**
     * Call the estate-search-service microservice and check if the poi exists
     * @param poiDTO A {@link PoiDTO} object containing lat and lng data
     * @return A {@link ResponseEntity} containing a standardized response with the PoiDTO {@link PoiDTO} data if location exists,
     * null otherwise .
     */
    @PostMapping("/v1/poi")
    ApiResponse<PoiDTO> findPoi(@RequestBody PoiDTO poiDTO);
}
