package com.ctlfab.estatesearch.mappers;

import com.ctlfab.estatesearch.dto.EstateDTO;
import com.ctlfab.estatesearch.entities.Estate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {LocationMapper.class, AddonMapper.class, FileMapper.class, CategoryMapper.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EstateMapper {
    EstateDTO toDto(Estate estate);

    @Mapping(target = "files", ignore = true)
    Estate toEntity(EstateDTO estateDTO);
}
