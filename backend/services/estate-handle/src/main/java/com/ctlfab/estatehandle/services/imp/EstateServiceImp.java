package com.ctlfab.estatehandle.services.imp;

import com.ctlfab.estatehandle.client.HereClient;
import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.dto.LocationDTO;

import com.ctlfab.estatehandle.mappers.EstateMapper;
import com.ctlfab.estatehandle.entities.Estate;

import com.ctlfab.estatehandle.repositories.EstateRepository;

import com.ctlfab.estatehandle.services.EstateService;
import com.ctlfab.estatehandle.services.FileService;
import com.ctlfab.estatehandle.services.LocationService;

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
    private final HereClient hereClient;

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

        log.info("======> Estate {}", savedEstate);

        List<FileDTO> savedFiles = saveFiles(estateDTO.getFiles(), savedEstate);
        savedEstate.setLocation(savedLocation);
        savedEstate.setFiles(savedFiles);

        log.info("Estate {} saved successfully", savedEstate);
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

    /**
     * Save location and poi information about an estate
     * @param locationDTO The {@link LocationDTO} object containing details of the location to geocode.
     * @return A {@link LocationDTO} object containing the geolocation data
     */
    private LocationDTO saveLocation(LocationDTO locationDTO) {
        String queryStreet = locationDTO.getStreet().replaceAll("[ ,]", "+");
        String query = queryStreet + "+" +
                locationDTO.getPostalCode() + "+" +
                locationDTO.getCity() + "+" +
                locationDTO.getCity();

        locationDTO = hereClient.callGeocode(query);

        return locationService.save(locationDTO);
    }

    /**
     * Save all file of an estate
     *
     * @param fileDTOList A List of The {@link FileDTO} object containing details of the file to be saved.
     * @param estateDTO   The {@link EstateDTO} object containing details of the related to the file
     * @return List of the saved {@link FileDTO}
     */
    private List<FileDTO> saveFiles(List<FileDTO> fileDTOList, EstateDTO estateDTO) {
        List<FileDTO> savedFiles = new LinkedList<>();
        if(fileDTOList != null){
            for(FileDTO fileDTO : fileDTOList){
                savedFiles.add(fileService.save(fileDTO, estateDTO));
            }
        }

        return savedFiles;
    }

    /**
     * Deletes an existing file by its estate ID.
     * @param estateId The ID of the estate related to the file to be deleted.
     */
    private void deleteFiles(long estateId){
        List<FileDTO> filesDTO = fileService.getByEstateId(estateId);

        for (FileDTO fileDTO : filesDTO) {
            fileService.delete(fileDTO.getId());
        }
    }
}
