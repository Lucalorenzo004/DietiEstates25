package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.AddonsDTO;
import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.model.Addons;
import com.ctlfab.estatehandle.model.Estate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Class mapper for addons
 * Author: Fabrizio Ciotola
 */
@Component
public class AddonsMapper {

    /**
     * Map Addons to AddonsDTO
     * @param addons Addons to map
     * @return AddonsDTO
     */
    public static AddonsDTO mapToDTO(Addons addons) {
        return AddonsDTO.builder()
                .id(addons.getId())
                .name(addons.getName())
                .build();
    }

    /**
     * Map AddonsDTO to Addons
     * @param addonsDTO AddonsDTO to map
     * @param estateDTOList List of estate related to the Addons
     * @return Addons
     */
    public static Addons mapToEntity(AddonsDTO addonsDTO, List<EstateDTO> estateDTOList) {
        List<Estate> estateList = new LinkedList<>();
        for(EstateDTO estateDTO : estateDTOList){
            estateList.add(EstateMapper.mapToEntity(estateDTO));
        }

        return Addons.builder()
                .id(addonsDTO.getId())
                .name(addonsDTO.getName())
                .estates(estateList)
                .build();
    }

    /**
     * Map AddonsDTO to Addons
     * @param addonsDTO AddonsDTO to map
     * @return Addons
     */
    public static Addons mapToEntity(AddonsDTO addonsDTO) {
        return Addons.builder()
                .id(addonsDTO.getId())
                .name(addonsDTO.getName())
                .build();
    }
}
