package com.ctlfab.estatehandle.service.impl;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.mapper.EstateMapper;
import com.ctlfab.estatehandle.model.Estate;
import com.ctlfab.estatehandle.repository.EstateRepository;
import com.ctlfab.estatehandle.service.EstateService;
import com.ctlfab.estatehandle.service.FileService;
import com.ctlfab.estatehandle.service.LocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class EstateServiceImp implements EstateService {
    private final EstateRepository repository;
    private final LocationService locationService;
    private final FileService fileService;

    @Override
    public List<EstateDTO> getAllEstates() {
        log.info("Fetching all estates");

        List<EstateDTO> estatesDTO = new ArrayList<>();
        for(Estate estate : repository.findAll()){
            estatesDTO.add(EstateMapper.mapToDTO(estate));
        }

        log.info("Estates fetched successfully");
        return estatesDTO;
    }

    @Override
    public EstateDTO addEstate(EstateDTO estateDTO, List<FileDTO> filesDTO) {
        log.info("Saving estate {}", estateDTO);

        LocationDTO locationDTO = locationService.saveLocation(estateDTO.getLocation());
        estateDTO.setLocation(locationDTO);

        Estate estate = EstateMapper.mapToEntity(estateDTO);
        estate = repository.save(estate);

        for(FileDTO fileDTO : filesDTO){
            fileService.saveFile(fileDTO, estateDTO);
        }

        log.info("Estate {} saved successfully", estateDTO);
        return EstateMapper.mapToDTO(estate);
    }

    @Override
    public EstateDTO editEstate(EstateDTO estateDTO, List<FileDTO> filesDTO) {
        log.info("Updating estate {}", estateDTO);

        LocationDTO locationDTO = locationService.saveLocation(estateDTO.getLocation());
        estateDTO.setLocation(locationDTO);

        Estate estate = EstateMapper.mapToEntity(estateDTO);
        estate = repository.save(estate);

        for(FileDTO fileDTO : filesDTO){
            fileService.saveFile(fileDTO, estateDTO);
        }

        log.info("Estate {} updated successfully", estateDTO);
        return EstateMapper.mapToDTO(estate);
    }

    @Override
    public boolean deleteEstate(long estateId) {
        log.info("Deleting estate {}", estateId);

        repository.deleteById(estateId);

        log.info("Estate {} deleted successfully", estateId);
        return true;
    }
}
