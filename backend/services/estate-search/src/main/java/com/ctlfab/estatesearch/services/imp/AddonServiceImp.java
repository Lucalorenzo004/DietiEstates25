package com.ctlfab.estatesearch.services.imp;

import com.ctlfab.estatesearch.mappers.AddonMapper;
import com.ctlfab.estatesearch.repositories.AddonRepository;
import com.ctlfab.estatesearch.services.AddonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class AddonServiceImp implements AddonService {
    private final AddonRepository repository;
    private final AddonMapper mapper;

}
