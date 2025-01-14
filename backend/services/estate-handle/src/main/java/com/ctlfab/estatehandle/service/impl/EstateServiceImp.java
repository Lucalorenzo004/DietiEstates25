package com.ctlfab.estatehandle.service.impl;

import com.ctlfab.estatehandle.dto.AddonsDTO;
import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.enumeration.EnergyClass;
import com.ctlfab.estatehandle.model.Addons;
import com.ctlfab.estatehandle.model.Estate;
import com.ctlfab.estatehandle.model.Location;
import com.ctlfab.estatehandle.repository.EstateRepository;
import com.ctlfab.estatehandle.service.EstateService;
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
    private final EstateRepository estateRepository;

    @Override
    public List<EstateDTO> getAllEstates() {
        log.info("Fetching all estates");

        List<EstateDTO> estatesDTO = new ArrayList<>();
        for(Estate estate : estateRepository.findAll()){
            estatesDTO.add(mapToDTO(estate));
        }

        log.info("Estates fetched successfully");
        return estatesDTO;
    }

    @Override
    public EstateDTO addEstate(EstateDTO estateDTO) {
        log.info("Saving estate {}", estateDTO);

        Estate estate = mapToEntity(estateDTO);
        estate = estateRepository.save(estate);

        log.info("Estate {} saved successfully", estateDTO);
        return mapToDTO(estate);
    }

    @Override
    public EstateDTO editEstate(EstateDTO estateDTO) {
        log.info("Updating estate {}", estateDTO);

        Estate estate = mapToEntity(estateDTO);
        estate = estateRepository.save(estate);

        log.info("Estate {} updated successfully", estateDTO);
        return mapToDTO(estate);
    }

    @Override
    public boolean deleteEstate(long estateId) {
        log.info("Deleting estate {}", estateId);

        estateRepository.deleteById(estateId);

        log.info("Estate {} deleted successfully", estateId);
        return true;
    }

    private Estate mapToEntity(EstateDTO estateDTO) {
        Location location = Location.builder()
                .id(estateDTO.getLocation().getId())
                .addressLine1(estateDTO.getLocation().getAddressLine1())
                .addressLine2(estateDTO.getLocation().getAddressLine2())
                .build();

        List<Addons> addonsList = new ArrayList<>();
        for(AddonsDTO addonsDTO : estateDTO.getAddons()){
            Addons addons = Addons.builder()
                    .id(addonsDTO.getId())
                    .name(addonsDTO.getName())
                    .build();

            addonsList.add(addons);
        }

        return Estate.builder()
                .id(estateDTO.getId())
                .title(estateDTO.getTitle())
                .category(estateDTO.getCategory())
                .description(estateDTO.getDescription())
                .rental(estateDTO.isRental())
                .price(estateDTO.getPrice())
                .mtq(estateDTO.getMtq())
                .energyClass(EnergyClass.valueOf(estateDTO.getEnergyClass()))
                .rooms(estateDTO.getRooms())
                .services(estateDTO.getServices())
                .userId(estateDTO.getUserId())
                .location(location)
                .addons(addonsList)
                .build();
    }

    private EstateDTO mapToDTO(Estate estate) {
        LocationDTO location = LocationDTO.builder()
                .id(estate.getLocation().getId())
                .addressLine1(estate.getLocation().getAddressLine1())
                .addressLine2(estate.getLocation().getAddressLine2())
                .build();

        List<AddonsDTO> addonsDTOList = new ArrayList<>();
        for(Addons addons : estate.getAddons()){
            AddonsDTO addonsDTO = AddonsDTO.builder()
                    .id(addons.getId())
                    .name(addons.getName())
                    .build();

            addonsDTOList.add(addonsDTO);
        }

       return EstateDTO.builder()
                .id(estate.getId())
                .title(estate.getTitle())
                .category(estate.getCategory())
                .description(estate.getDescription())
                .rental(estate.isRental())
                .price(estate.getPrice())
                .mtq(estate.getMtq())
                .energyClass(String.valueOf(estate.getEnergyClass()))
                .rooms(estate.getRooms())
                .services(estate.getServices())
                .userId(estate.getUserId())
                .location(location)
                .addons(addonsDTOList)
                .build();
    }
}
