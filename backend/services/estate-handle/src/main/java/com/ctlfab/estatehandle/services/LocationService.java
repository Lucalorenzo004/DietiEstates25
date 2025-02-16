package com.ctlfab.estatehandle.services;

import com.ctlfab.estatehandle.dto.LocationDTO;

public interface LocationService {

    /**
     * Check if the location already exists and in case it does not exist save it.
     * @param locationDTO The {@link LocationDTO} object containing details of the location to be saved.
     * @return The saved {@link LocationDTO}.
     */
    LocationDTO save(LocationDTO locationDTO);
}
