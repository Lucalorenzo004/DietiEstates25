package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.LocationDTO;

public interface LocationService {

    /**
     * Saves a new location.
     *
     * @param locationDTO The {@link LocationDTO} object containing details of the location to be saved.
     * @return The saved {@link LocationDTO}
     */
    LocationDTO save(LocationDTO locationDTO);

    /**
     * Deletes an existing location by its ID.
     *
     * @param locationId The ID of the location to be deleted.
     * @return {@code true} if the location was successfully deleted, {@code false} otherwise.
     */
    boolean deleteLocation(long locationId);
}
