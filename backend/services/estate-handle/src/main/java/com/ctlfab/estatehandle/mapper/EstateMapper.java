package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.AddonsDTO;
import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.enumeration.EnergyClass;
import com.ctlfab.estatehandle.model.Addons;
import com.ctlfab.estatehandle.model.Estate;
import com.ctlfab.estatehandle.model.Location;
import com.ctlfab.estatehandle.repository.EstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EstateMapper {
    private final EstateRepository repository;
    private final LocationMapper locationMapper;
    private final AddonsMapper addonsMapper;


    public EstateDTO mapToDTO(Estate estate) {
        LocationDTO location = locationMapper.mapToDTO(estate.getLocation());

        List<AddonsDTO> addonsDTOList = new LinkedList<>();
        for(Addons addons : estate.getAddons()){
            addonsDTOList.add(addonsMapper.mapToDTO(addons));
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

    public Estate mapToEntity(EstateDTO estateDTO) {
        if(estateDTO.getId() != null){
            Optional<Estate> estateOptional = repository.findById(estateDTO.getId());
            if(estateOptional.isPresent()){
                return estateOptional.get();
            }
        }

        Location location = locationMapper.mapToEntity(estateDTO.getLocation());
        List<Addons> addonsList = new LinkedList<>();
        for(AddonsDTO addonsDTO : estateDTO.getAddons()){
            addonsList.add(addonsMapper.mapToEntity(addonsDTO));
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
}
