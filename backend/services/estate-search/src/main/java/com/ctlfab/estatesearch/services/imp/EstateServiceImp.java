package com.ctlfab.estatesearch.services.imp;

import com.ctlfab.estatesearch.dto.EstateDTO;
import com.ctlfab.estatesearch.dto.FileDTO;
import com.ctlfab.estatesearch.dto.FilterDTO;
import com.ctlfab.estatesearch.dto.LocationDTO;

import com.ctlfab.estatesearch.mappers.EstateMapper;
import com.ctlfab.estatesearch.entities.Estate;

import com.ctlfab.estatesearch.repositories.EstateRepository;

import com.ctlfab.estatesearch.services.EstateService;
import com.ctlfab.estatesearch.services.FileService;
import com.ctlfab.estatesearch.services.LocationService;

import com.ctlfab.estatesearch.specification.EstateSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional(rollbackOn = {SQLException.class})
public class EstateServiceImp implements EstateService {
    private final EstateRepository repository;
    private final EstateMapper mapper;

    @Override
    public List<EstateDTO> getAllEstates(FilterDTO filterDTO) {
        log.info("Fetching all estates");

        Specification<Estate> specification = EstateSpecification.getSpecification(filterDTO);
        System.out.println(specification);

        List<EstateDTO> estatesDTO = new ArrayList<>();
        for(Estate estate : repository.findAll(specification)){
            estatesDTO.add(mapper.toDto(estate));
        }

        log.info("Estates fetched successfully");
        return estatesDTO;
    }

    /**
     * Save location and poi information about an estate
     * @param locationDTO The {@link LocationDTO} object containing details of the location to geocode.
     * @return A {@link LocationDTO} object containing the geolocation data
     */
    private LocationDTO getLocation(LocationDTO locationDTO) {
       return null;
    }

    /**
     * Retrieve all file of an estate
     *
     * @param estateDTO   The {@link EstateDTO} object containing details of the related to the file
     * @return List of the retrieved {@link FileDTO}
     */
    private List<FileDTO> getFiles(EstateDTO estateDTO) {
        return null;
    }
}
