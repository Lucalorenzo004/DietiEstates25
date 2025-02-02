package com.ctlfab.estatehandle.services.imp;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.dto.PoiDTO;
import com.ctlfab.estatehandle.entities.Location;
import com.ctlfab.estatehandle.mappers.LocationMapper;
import com.ctlfab.estatehandle.mappers.PoiMapper;
import com.ctlfab.estatehandle.entities.Poi;
import com.ctlfab.estatehandle.repositories.PoiRepository;
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

    @Override
    public PoiDTO save(PoiDTO poiDTO, LocationDTO locationDTO) {
        log.info("Saving poi: {}", poiDTO);

        Poi poi = mapper.toEntity(poiDTO);
        poi.setLocation(locationMapper.toEntity(locationDTO));
        poi = repository.save(poi);

        poiDTO = mapper.toDTO(poi);

        log.info("Saved poi: {}", poiDTO);
        return poiDTO;
    }
}
