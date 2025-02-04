package com.ctlfab.estatesearch.services.imp;

import com.ctlfab.estatesearch.dto.LocationDTO;
import com.ctlfab.estatesearch.dto.PoiDTO;
import com.ctlfab.estatesearch.mappers.LocationMapper;
import com.ctlfab.estatesearch.repositories.LocationRepository;
import com.ctlfab.estatesearch.services.LocationService;
import com.ctlfab.estatesearch.services.PoiService;
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

    private final PoiService poiService;


    private List<PoiDTO> getPoi(LocationDTO locationDTO){
       return null;
    }
}
