package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.PoiDTO;
import com.ctlfab.estatehandle.entities.Poi;
import org.mapstruct.Mapper;

@Mapper(
        uses = LocationMapper.class,
        componentModel = "spring"
)
public interface PoiMapper {
    PoiDTO toDTO(Poi poi);
    Poi toEntity(PoiDTO poiDTO);
}
