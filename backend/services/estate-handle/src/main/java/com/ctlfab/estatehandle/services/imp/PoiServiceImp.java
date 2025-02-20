package com.ctlfab.estatehandle.services.imp;

import com.ctlfab.estatehandle.client.EstateSearchClient;
import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.dto.PoiDTO;
import com.ctlfab.estatehandle.mappers.LocationMapper;
import com.ctlfab.estatehandle.mappers.PoiMapper;
import com.ctlfab.estatehandle.entities.Poi;
import com.ctlfab.estatehandle.repositories.PoiRepository;
import com.ctlfab.estatehandle.serialization.ApiResponse;
import com.ctlfab.estatehandle.services.PoiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class PoiServiceImp implements PoiService {
    private final PoiRepository repository;
    private final PoiMapper mapper;
    private final LocationMapper locationMapper;
    private final EstateSearchClient estateSearchClient;

    /**
     * Check if the POI already exists and in case it does not exist save it.
     * @param poiDTO The {@link PoiDTO} object containing details of the POI to be saved.
     * @return The saved {@link PoiDTO}.
     */
    @Override
    public PoiDTO save(PoiDTO poiDTO, LocationDTO locationDTO) {
        log.info("Saving poi: {}", poiDTO);

        ApiResponse<PoiDTO> response = estateSearchClient.findPoi(poiDTO);
        if(response.getData() != null){
            poiDTO = response.getData();
        }else{
            Poi poi = mapper.toEntity(poiDTO);
            poi.setLocation(locationMapper.toEntity(locationDTO));
            poi = repository.save(poi);
            poiDTO = mapper.toDTO(poi);
        }

        log.info("Saved poi: {}", poiDTO);
        return poiDTO;
    }
}
