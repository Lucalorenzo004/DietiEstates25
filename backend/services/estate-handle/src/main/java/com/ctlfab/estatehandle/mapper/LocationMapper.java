package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.model.Location;
import com.ctlfab.estatehandle.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LocationMapper {
    private final LocationRepository repository;

    public LocationDTO mapToDTO(Location location){
        return LocationDTO.builder()
                .id(location.getId())
                .addressLine1(location.getAddressLine1())
                .addressLine2(location.getAddressLine2())
                .build();
    }

    public Location mapToEntity(LocationDTO locationDTO){
        if(locationDTO.getId() != null){
            Optional<Location> locationOptional = repository.findById(locationDTO.getId());
            if(locationOptional.isPresent()){
                return locationOptional.get();
            }
        }

        return Location.builder()
                .addressLine1(locationDTO.getAddressLine1())
                .addressLine2(locationDTO.getAddressLine2())
                .build();
    }
}
