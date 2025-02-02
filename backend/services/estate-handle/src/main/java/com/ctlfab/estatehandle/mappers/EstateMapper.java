package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.entities.Estate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        uses = {LocationMapper.class, AddonsMapper.class, FileMapper.class},
        componentModel = "spring"
)
public interface EstateMapper {
    EstateDTO toDto(Estate estate);

    @Mapping(target = "files", ignore = true)
    Estate toEntity(EstateDTO estateDTO);
}
