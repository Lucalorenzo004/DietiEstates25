package com.ctlfab.estatehandle.services.imp;

import com.ctlfab.estatehandle.client.HereClient;
import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FavoriteEstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.dto.LocationDTO;

import com.ctlfab.estatehandle.entities.Estate;
import com.ctlfab.estatehandle.entities.FavoriteEstate;
import com.ctlfab.estatehandle.mappers.EstateMapper;

import com.ctlfab.estatehandle.mappers.FavoriteEstateMapper;
import com.ctlfab.estatehandle.repositories.EstateRepository;

import com.ctlfab.estatehandle.repositories.FavoriteEstateRepository;
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
    private final FavoriteEstateMapper favoriteMapper;

    private final LocationService locationService;
    private final FavoriteEstateRepository favoriteRepository;
    private final FileService fileService;
    private final HereClient hereClient;

    /**
     * Saves a new estate.
     * @param estateDTO The {@link EstateDTO} object containing details of the estate to be saved.
     * @return The saved {@link EstateDTO}.
     */
    @Override
    public EstateDTO save(EstateDTO estateDTO) {
        log.info("Saving estate {}", estateDTO);

        LocationDTO savedLocation = saveLocation(estateDTO.getLocation());
        estateDTO.setLocation(savedLocation);

        Estate estate = mapper.toEntity(estateDTO);
        estate = repository.save(estate);
        EstateDTO savedEstate = mapper.toDTO(estate);

        List<FileDTO> savedFiles = saveFiles(estateDTO.getFiles(), savedEstate);
        savedEstate.setLocation(savedLocation);
        savedEstate.setFiles(savedFiles);

        log.info("Estate {} saved successfully", savedEstate);
        return savedEstate;
    }

    /**
     * Deletes an estate by its ID.
     * @param estateId The ID of the estate to be deleted.
     * @return {@code true} if the estate was successfully deleted, {@code false} otherwise.
     */
    @Override
    public boolean delete(long estateId) {
        log.info("Deleting estate {}", estateId);

        repository.deleteById(estateId);

        log.info("Estate {} deleted successfully", estateId);
        return true;
    }

    /**
     * Add or Remove favorite relationship between user and estate.
     * @param favoriteEstateDTO data about relationship.
     * @return {@code true} if the estate was successfully added o removed to favorite, {@code false} otherwise.
     */
    @Override
    public boolean favorite(FavoriteEstateDTO favoriteEstateDTO) {
        log.info("Adding estate {} to user {} favorites", favoriteEstateDTO.getEstateId(), favoriteEstateDTO.getUserId());

        if(favoriteEstateDTO.isAddToFavorite()){
            FavoriteEstate favoriteEstate = favoriteMapper.toEntity(favoriteEstateDTO);
            favoriteRepository.save(favoriteEstate);
        }else{
            favoriteRepository.removeToFavorite(favoriteEstateDTO.getEstateId(), favoriteEstateDTO.getUserId());
        }

        log.info("Relationship modified successfully");
        return false;
    }

    /**
     * Save estate's location and poi data
     * @param locationDTO {@link LocationDTO} object containing details of the location to geocode.
     * @return {@link LocationDTO} object containing the geolocation data.
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
     * Save all estate's files.
     * @param fileDTOList A List of The {@link List<FileDTO>} object containing details of the file to be saved.
     * @param estateDTO   The {@link EstateDTO} object containing details of the related to the file.
     * @return List of the saved {@link List<FileDTO>}.
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
}
