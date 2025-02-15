package com.ctlfab.estatesearch.services.imp;

import com.ctlfab.estatesearch.mappers.LocationMapper;
import com.ctlfab.estatesearch.mappers.PoiMapper;
import com.ctlfab.estatesearch.repositories.PoiRepository;
import com.ctlfab.estatesearch.services.PoiService;
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

}
