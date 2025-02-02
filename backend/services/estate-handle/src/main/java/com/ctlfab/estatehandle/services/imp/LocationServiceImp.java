package com.ctlfab.estatehandle.services.imp;

import com.ctlfab.estatehandle.client.HereClient;
import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.dto.PoiDTO;
import com.ctlfab.estatehandle.mappers.LocationMapper;
import com.ctlfab.estatehandle.entities.Location;
import com.ctlfab.estatehandle.repositories.LocationRepository;
import com.ctlfab.estatehandle.services.LocationService;
import com.ctlfab.estatehandle.services.PoiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public LocationDTO save(LocationDTO locationDTO) {
        log.info("Saving location {}", locationDTO);

        Location location = mapper.toEntity(locationDTO);
        location = repository.save(location);
        locationDTO = mapper.toDto(location);

        locationDTO.setPoi(savePoi(locationDTO));

        log.info("Location {} saved successfully", locationDTO);
        return locationDTO;
    }

    private List<PoiDTO> savePoi(LocationDTO locationDTO){
        List<PoiDTO> poiDTOList = hereClient.callBrowser(locationDTO.getLat(), locationDTO.getLng());

        List<PoiDTO> savedPoiDTOList = new LinkedList<>();
        for (PoiDTO poiDTO : poiDTOList) {
            savedPoiDTOList.add(poiService.save(poiDTO, locationDTO));
        }

        return savedPoiDTOList;
    }
}
