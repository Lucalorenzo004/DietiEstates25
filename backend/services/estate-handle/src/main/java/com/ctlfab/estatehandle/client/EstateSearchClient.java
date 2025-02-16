package com.ctlfab.estatehandle.client;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.dto.PoiDTO;
import com.ctlfab.estatehandle.serialization.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "estate-search-service", url = "http://localhost:8082/estate-search-api")
public interface EstateSearchClient {

    @PostMapping("/v1/locations")
    ApiResponse<LocationDTO> findLocationByFullAddress(@RequestBody LocationDTO location);

    @PostMapping("/v1/poi")
    ApiResponse<PoiDTO> findPoiByLatAndLng(@RequestBody PoiDTO poiDTO);
}
