package com.ctlfab.estatesearch.services.imp;

import com.ctlfab.estatesearch.dto.PoiDTO;
import com.ctlfab.estatesearch.entities.Poi;
import com.ctlfab.estatesearch.mappers.PoiMapper;
import com.ctlfab.estatesearch.repositories.PoiRepository;
import com.ctlfab.estatesearch.services.PoiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class PoiServiceImp implements PoiService {
    private final PoiRepository repository;
    private final PoiMapper mapper;

    /**
     * Check if the poi already exists.
     * @param lat Lat of poi.
     * @param lng Lng of poi.
     * @return  {@link Poi} if location's data exists, null otherwise.
     */
    @Cacheable(cacheNames = "poi-cache", key = "#lat + #lng")
    @Override
    public PoiDTO findByLatAndLng(Float lat, Float lng) {
        log.info("Fetching poi data from lat and lng");
        Optional<Poi> optionalPoi = repository.findByLatAndLng(lat, lng);

        if (optionalPoi.isPresent()) {
            log.info("Poi fetched successfully");
            return mapper.toDTO(optionalPoi.get());
        }

        log.info("Poi not found");
        return null;
    }
}
