package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.entities.Estate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {LocationMapper.class, AddonsMapper.class, FileMapper.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EstateMapper {
    EstateDTO toDto(Estate estate);

    @Mapping(target = "files", ignore = true)
    Estate toEntity(EstateDTO estateDTO);
}
