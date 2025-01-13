package com.ctlfab.estatehandle.service.impl;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.model.Location;
import com.ctlfab.estatehandle.repository.LocationRepository;
import com.ctlfab.estatehandle.service.LocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class LocationServiceImp implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public LocationDTO saveLocation(LocationDTO locationDTO) {
        log.info("Saving location {}", locationDTO);

        Location location = mapToEntity(locationDTO);
        location = locationRepository.save(location);

        log.info("Location {} saved successfully", locationDTO);
        return mapToDto(location);
    }

    @Override
    public LocationDTO editLocation(LocationDTO locationDTO) {
        log.info("Updating location {}", locationDTO);

        Location location = mapToEntity(locationDTO);
        location = locationRepository.save(location);

        log.info("Location {} updated successfully", locationDTO);
        return mapToDto(location);
    }

    @Override
    public boolean deleteLocation(long locationId) {
        log.info("Deleting location {}", locationId);

        locationRepository.deleteById(locationId);

        log.info("Location {} deleted successfully", locationId);
        return true;
    }

    private Location mapToEntity(LocationDTO locationDTO) {
        return Location.builder()
                .id(locationDTO.getId())
                .addressLine1(locationDTO.getAddressLine1())
                .addressLine2(locationDTO.getAddressLine2())
                .build();
    }
    private LocationDTO mapToDto(Location location) {
        return LocationDTO.builder()
                .id(location.getId())
                .addressLine1(location.getAddressLine1())
                .addressLine2(location.getAddressLine2())
                .build();
    }
}
