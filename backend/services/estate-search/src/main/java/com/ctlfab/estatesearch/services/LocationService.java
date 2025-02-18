package com.ctlfab.estatesearch.services;


import com.ctlfab.estatesearch.dto.LocationDTO;

public interface LocationService {

    /**
     * Check if the location already exists.
     * @param street Street of address.
     * @param city City of address.
     * @param postalCode Postal code of city.
     * @param county County of city.
     * @param countyCode County code of county.
     * @return {@link LocationDTO} object.
     */
    LocationDTO findByFullAddress(String street, String city, String postalCode, String county, String countyCode);
}
