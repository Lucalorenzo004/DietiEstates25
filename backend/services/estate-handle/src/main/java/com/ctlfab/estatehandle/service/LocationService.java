package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.LocationDTO;

public interface LocationService {
    LocationDTO saveLocation(LocationDTO location);
    LocationDTO editLocation(LocationDTO location);
    boolean deleteLocation(long locationId);
}
