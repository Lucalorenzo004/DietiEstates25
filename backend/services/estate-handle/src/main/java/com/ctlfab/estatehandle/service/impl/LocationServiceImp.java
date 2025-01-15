package com.ctlfab.estatehandle.service.impl;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.mapper.LocationMapper;
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
    private final LocationRepository repository;
    private final LocationMapper mapper;

    @Override
    public LocationDTO saveLocation(LocationDTO locationDTO) {
        log.info("Saving location {}", locationDTO);

        Location location = mapper.mapToEntity(locationDTO);
        location = repository.save(location);

        log.info("Location {} saved successfully", locationDTO);
        return mapper.mapToDTO(location);
    }

    @Override
    public LocationDTO editLocation(LocationDTO locationDTO) {
        log.info("Updating location {}", locationDTO);

        Location location = mapper.mapToEntity(locationDTO);
        location = repository.save(location);

        log.info("Location {} updated successfully", locationDTO);
        return mapper.mapToDTO(location);
    }

    @Override
    public boolean deleteLocation(long locationId) {
        log.info("Deleting location {}", locationId);

        repository.deleteById(locationId);

        log.info("Location {} deleted successfully", locationId);
        return true;
    }
}
