package com.ctlfab.estatesearch.mappers;

import com.ctlfab.estatesearch.dto.AddonDTO;
import com.ctlfab.estatesearch.entities.Addon;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = EstateMapper.class,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AddonMapper {
    Addon toEntity(AddonDTO addonDTO);

    AddonDTO toDTO(Addon addon);
}
