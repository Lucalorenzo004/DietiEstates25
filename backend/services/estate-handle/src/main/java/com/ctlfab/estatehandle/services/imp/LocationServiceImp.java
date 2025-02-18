package com.ctlfab.estatehandle.services.imp;

import com.ctlfab.estatehandle.client.EstateSearchClient;
import com.ctlfab.estatehandle.client.HereClient;
import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.dto.PoiDTO;
import com.ctlfab.estatehandle.mappers.LocationMapper;
import com.ctlfab.estatehandle.entities.Location;
import com.ctlfab.estatehandle.repositories.LocationRepository;
import com.ctlfab.estatehandle.serialization.ApiResponse;
import com.ctlfab.estatehandle.services.LocationService;
import com.ctlfab.estatehandle.services.PoiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class LocationServiceImp implements LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;

    private final HereClient hereClient;
    private final PoiService poiService;
    private final EstateSearchClient estateSearchClient;

    /**
     * Check if the location already exists and in case it does not exist save it.
     * @param locationDTO The {@link LocationDTO} object containing details of the location to be saved.
     * @return The saved {@link LocationDTO}.
     */
    @Override
    public LocationDTO save(LocationDTO locationDTO) {
        log.info("Saving location {}", locationDTO);

        ApiResponse<LocationDTO> response = estateSearchClient.findLocation(locationDTO);
        if(response.getData() != null){
            locationDTO = response.getData();
        }else{
            Location location = mapper.toEntity(locationDTO);
            location = repository.save(location);
            locationDTO = mapper.toDTO(location);
            locationDTO.setPoi(savePoi(locationDTO));
        }

        log.info("Location {} saved successfully", locationDTO);
        return locationDTO;
    }

    /**
     * Call api here to find all poi within 500m radius with center in {@link LocationDTO} object and saves them.
     * @param locationDTO {@link LocationDTO}.
     * @return List of the saved {@link PoiDTO}.
     */
    private List<PoiDTO> savePoi(LocationDTO locationDTO){
        List<PoiDTO> poiDTOList = hereClient.callBrowser(locationDTO.getLat(), locationDTO.getLng());

        List<PoiDTO> savedPoiDTOList = new LinkedList<>();
        for (PoiDTO poiDTO : poiDTOList) {
            savedPoiDTOList.add(poiService.save(poiDTO, locationDTO));
        }

        return savedPoiDTOList;
    }
}
