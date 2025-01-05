package com.ctlfab.estatehandle.service.impl;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.repository.EstateRepository;
import com.ctlfab.estatehandle.service.EstateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class EstateServiceImp implements EstateService {
    private final EstateRepository estateRepository;
    private static final Logger logger = LoggerFactory.getLogger(EstateServiceImp.class);

    @Override
    public List<EstateDTO> getEstatesByEmplyeeId(long emplyeeId) {
        return List.of();
    }

    @Override
    public EstateDTO addEstate(EstateDTO estate) {
        logger.info("Saving estate {}", estate);

        logger.info("Estate {} saved", estate.getId());
        return null;
    }

    @Override
    public EstateDTO editEstate(EstateDTO estate) {
        logger.info("Updating estate {}", estate);

        logger.info("Estate {} updated", estate.getId());
        return null;
    }

    @Override
    public boolean deleteEstate(long estateId) {
        logger.info("Deleting estate {}", estateId);

        estateRepository.deleteById(estateId);
        logger.info("Estate {} deleted", estateId);
        return true;
    }
}
