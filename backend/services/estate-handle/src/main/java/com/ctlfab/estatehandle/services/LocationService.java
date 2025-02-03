package com.ctlfab.estatehandle.services;

import com.ctlfab.estatehandle.dto.LocationDTO;

public interface LocationService {

    /**
     * Saves a new location.
     *
     * @param locationDTO The {@link LocationDTO} object containing details of the location to be saved.
     * @return The saved {@link LocationDTO}
     */
    LocationDTO save(LocationDTO locationDTO);
}
