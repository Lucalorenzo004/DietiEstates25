package com.ctlfab.estatesearch.services.imp;

import com.ctlfab.estatesearch.dto.LocationDTO;
import com.ctlfab.estatesearch.entities.Location;
import com.ctlfab.estatesearch.mappers.LocationMapper;
import com.ctlfab.estatesearch.repositories.LocationRepository;
import com.ctlfab.estatesearch.services.LocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class LocationServiceImp implements LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;

    /**
     * Check if the location already exists.
     * @param street Street of address.
     * @param city City of address.
     * @param postalCode Postal code of city.
     * @param county County of city.
     * @param countyCode County code of county.
     * @return {@link LocationDTO} object.
     */
    @Cacheable(cacheNames = "location-cache", key = "#street + #city + #postalCode + #county + #countyCode")
    @Override
    public LocationDTO findByFullAddress(String street, String city, String postalCode, String county, String countyCode) {
        log.info("Fetching location data from full address");
        Optional<Location> optionalLocation = repository.findByFullAddress(street, city, postalCode, county, countyCode);

        if (optionalLocation.isPresent()) {
            log.info("Location fetched successfully");
            return mapper.toDTO(optionalLocation.get());
        }else{
            log.info("Location not found");
            return null;
        }
    }
}
