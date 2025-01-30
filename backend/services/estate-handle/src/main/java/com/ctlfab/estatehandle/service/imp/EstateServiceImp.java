package com.ctlfab.estatehandle.service.imp;

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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional(rollbackOn = {SQLException.class})
public class EstateServiceImp implements EstateService {
    private final EstateRepository repository;
    private final EstateMapper mapper;

    private final LocationService locationService;
    private final FileService fileService;

    @Override
    public List<EstateDTO> getAllEstates() {
        log.info("Fetching all estates");

        List<EstateDTO> estatesDTO = new ArrayList<>();
        for(Estate estate : repository.findAll()){
            estatesDTO.add(mapper.toDto(estate));
        }

        log.info("Estates fetched successfully");
        return estatesDTO;
    }

    @Override
    public EstateDTO save(EstateDTO estateDTO) {
        log.info("Saving estate {}", estateDTO);

        LocationDTO savedLocation = saveLocation(estateDTO.getLocation());
        estateDTO.setLocation(savedLocation);

        Estate estate = mapper.toEntity(estateDTO);
        estate = repository.save(estate);
        EstateDTO savedEstate = mapper.toDto(estate);

        List<FileDTO> savedFiles = saveFiles(estateDTO.getFiles(), estateDTO);
        savedEstate.setFiles(savedFiles);

        log.info("Estate {} saved successfully", estateDTO);
        return savedEstate;
    }


    @Override
    public boolean delete(long estateId) {
        log.info("Deleting estate {}", estateId);

        deleteFiles(estateId);
        repository.deleteById(estateId);

        log.info("Estate {} deleted successfully", estateId);
        return true;
    }

    private LocationDTO saveLocation(LocationDTO locationDTO) {
        return locationService.save(locationDTO);
    }

    private List<FileDTO> saveFiles(List<FileDTO> fileDTOList, EstateDTO estateDTO) {
        List<FileDTO> savedFiles = new LinkedList<>();
        for(FileDTO fileDTO : fileDTOList){
            savedFiles.add(fileService.save(fileDTO, estateDTO));
        }

        return savedFiles;
    }

    private void deleteFiles(long estateId){
        List<FileDTO> filesDTO = fileService.getByEstateId(estateId);

        for (FileDTO fileDTO : filesDTO) {
            fileService.delete(fileDTO.getId());
        }
    }
}
