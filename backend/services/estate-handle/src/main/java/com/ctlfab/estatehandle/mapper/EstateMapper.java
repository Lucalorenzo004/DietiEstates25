package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.AddonsDTO;
import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.enumeration.EnergyClass;
import com.ctlfab.estatehandle.model.Addons;
import com.ctlfab.estatehandle.model.Estate;
import com.ctlfab.estatehandle.model.Location;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Class mapper for Estate
 * Author: Fabrizio Ciotola
 */
@Component
public class EstateMapper {

    /**
     * Map EstateDTO to Estate
     * @param estate Estate to map
     * @return Estate
     */
    public static EstateDTO mapToDTO(Estate estate) {
        LocationDTO locationDTO = LocationMapper.mapToDTO(estate.getLocation());

        List<AddonsDTO> addonsDTOList = new LinkedList<>();
        for(Addons addons : estate.getAddons()){
            addonsDTOList.add(AddonsMapper.mapToDTO(addons));
        }

        return EstateDTO.builder()
                .id(estate.getId())
                .title(estate.getTitle())
                .category(estate.getCategory())
                .description(estate.getDescription())
                .rental(estate.isRental())
                .price(estate.getPrice())
                .mtq(estate.getMtq())
                .energyClass(estate.getEnergyClass().name())
                .rooms(estate.getRooms())
                .services(estate.getServices())
                .userId(estate.getUserId())
                .location(locationDTO)
                .addons(addonsDTOList)
                .build();
    }

    /**
     * Map EstateDTO to Estate
     * @param estateDTO EstateDTO to map
     * @return Estate
     */
    public static Estate mapToEntity(EstateDTO estateDTO) {
        Location location = LocationMapper.mapToEntity(estateDTO.getLocation());

        List<Addons> addonsList = new LinkedList<>();
        for(AddonsDTO addonsDTO : estateDTO.getAddons()){
            addonsList.add(AddonsMapper.mapToEntity(addonsDTO));
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
