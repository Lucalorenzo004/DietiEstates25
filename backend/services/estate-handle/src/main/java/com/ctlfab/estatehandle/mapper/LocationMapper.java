package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.model.Location;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Class mapper for location
 * Author: Fabrizio Ciotola
 */
@Component
public class LocationMapper {

    /**
     * Map Location to LocationDTO
     * @param location Location to map
     * @return LocationDTO
     */
    public static LocationDTO mapToDTO(Location location){
        return LocationDTO.builder()
                .id(location.getId())
                .addressLine1(location.getAddressLine1())
                .addressLine2(location.getAddressLine2())
                .build();
    }

    /**
     * Map LocationDTO to Location
     * @param locationDTO LocationDTO to map
     * @param createdAt Timestamp of creation
     * @param updateAt Timestamp of last update
     * @return Location
     */
    public static Location mapToEntity(LocationDTO locationDTO, Timestamp createdAt, Timestamp updateAt){
        return Location.builder()
                .id(locationDTO.getId())
                .addressLine1(locationDTO.getAddressLine1())
                .addressLine2(locationDTO.getAddressLine2())
                .createdAt(createdAt)
                .updatedAt(updateAt)
                .build();
    }

    /**
     * Map LocationDTO to Location
     * @param locationDTO LocationDTO to map
     * @return Location
     */
    public static Location mapToEntity(LocationDTO locationDTO){
        return Location.builder()
                .addressLine1(locationDTO.getAddressLine1())
                .addressLine2(locationDTO.getAddressLine2())
                .build();
    }
}
