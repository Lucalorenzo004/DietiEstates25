package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.entities.Estate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {LocationMapper.class, AddonMapper.class, FileMapper.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EstateMapper {

    /**
     * Map {@link Estate} to {@link EstateDTO}
     * @param estate {@link Estate} to map
     * @return {@link EstateDTO} object
     */
    EstateDTO toDTO(Estate estate);

    /**
     * Map {@link EstateDTO} to {@link Estate}
     * @param estateDTO {@link EstateDTO} to map
     * @return {@link Estate} object
     */
    @Mapping(target = "files", ignore = true)
    Estate toEntity(EstateDTO estateDTO);
}
