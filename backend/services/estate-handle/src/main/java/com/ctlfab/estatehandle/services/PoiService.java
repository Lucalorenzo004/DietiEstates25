package com.ctlfab.estatehandle.services;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.dto.PoiDTO;

public interface PoiService {
    /**
     * Check if the POI already exists and in case it does not exist save it.
     * @param poiDTO The {@link PoiDTO} object containing details of the POI to be saved.
     * @return The saved {@link PoiDTO}.
     */
    PoiDTO save(PoiDTO poiDTO, LocationDTO locationDTO);
}
