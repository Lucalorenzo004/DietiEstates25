package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.AddonDTO;
import com.ctlfab.estatehandle.entities.Addon;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = EstateMapper.class,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AddonMapper {

    /**
     * Map {@link AddonDTO} to {@link Addon}
     * @param addonDTO {@link AddonDTO} to map
     * @return {@link Addon} object
     */
    Addon toEntity(AddonDTO addonDTO);

    /**
     * Map {@link Addon} to {@link AddonDTO}
     * @param addon {@link Addon} to map
     * @return {@link AddonDTO} object
     */
    AddonDTO toDTO(Addon addon);
}
