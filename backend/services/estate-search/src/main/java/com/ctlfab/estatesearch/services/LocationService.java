package com.ctlfab.estatesearch.services;


import com.ctlfab.estatesearch.dto.LocationDTO;

public interface LocationService {

    LocationDTO findByFullAddress(String street, String city, String postalCode, String county, String countyCode);
}
