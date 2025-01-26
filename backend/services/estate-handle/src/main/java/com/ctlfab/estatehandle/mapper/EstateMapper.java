package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.model.Estate;
import org.mapstruct.Mapper;

@Mapper(
        uses = {LocationMapper.class, AddonsMapper.class, FileMapper.class},
        componentModel = "spring"
)
public interface EstateMapper {
    EstateDTO toDto(Estate estate);
    Estate toEntity(EstateDTO estateDTO);
}
