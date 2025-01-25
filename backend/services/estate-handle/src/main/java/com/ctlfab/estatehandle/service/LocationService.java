package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.LocationDTO;

/**
 * Location Service
 * Author: Fabrizio Ciotola
 */
public interface LocationService {
    /**
     * Save a new location
     * @param location LocationDTO to save
     * @return LocationDTO saved
     */
    LocationDTO saveLocation(LocationDTO location);

    /**
     * Update Location
     * @param location LocationDTO to update
     * @return LocationDTO updated
     */
    LocationDTO editLocation(LocationDTO location);

    /**
     * Delete Location by its ID
     * @param locationId ID of location
     * @return True if the deletion was successful
     */
    boolean deleteLocation(long locationId);
}
